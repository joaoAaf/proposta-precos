# Sistema de Cadastro de Propostas de Preço

Este projeto é um sistema web para cadastro, envio e comparação de propostas de preço. Ele permite que requisitantes criem propostas modelo, enviem links para empresas preencherem os dados e preços, e armazenem as propostas recebidas para consulta e comparação.

## Funcionalidades

- **Criação de Proposta Modelo:**  
  O requisitante cadastra um modelo contendo a descrição e as quantidades dos materiais desejados.

- **Criação de Link para Empresas:**  
  O sistema gera um link para que empresas fornecedoras preencham seus dados e informem os preços dos materiais solicitados.

- **Recebimento e Armazenamento de Propostas:**  
  As propostas preenchidas pelas empresas são retornadas ao solicitante e armazenadas no banco de dados.

- **Comparação e Consulta de Propostas:**  
  O requisitante pode comparar diferentes propostas recebidas e consultar propostas anteriores.

## Arquitetura e Design

Esta seção detalha as escolhas arquiteturais e de design implementadas na API de Propostas de Preço (`proposta-precos-api`).

### Estrutura Multi-módulos

O projeto adota uma arquitetura multi-módulos para separar as responsabilidades e promover um baixo acoplamento entre as diferentes partes do sistema. A estrutura é dividida da seguinte forma:

- **`common-domain`:** Este módulo contem as entidades de domínio principais da aplicação (ex: `Proposta`, `GerenciadorProposta`, `Material`). Seu propósito é centralizar o modelo de negócio e ser compartilhado entre os outros módulos, garantindo consistência.

- **`external-api`:** Este módulo é responsável por encapsular a comunicação com APIs externas. Como visto em `RelatoriosFeignClient`, ele utiliza o Spring Cloud OpenFeign para criar clientes declarativos para outros microsserviços, como a API de Relatórios. Isso isola a lógica de integração e suas dependências do núcleo da aplicação.

- **`main-app`:** É o módulo principal da aplicação. Ele contém a classe de inicialização do Spring Boot e é responsável por:
    - Expor a API REST para os clientes.
    - Implementar os casos de uso e a lógica de negócio nos `Services`.
    - Orquestrar o fluxo de dados entre o cliente, o domínio e os serviços externos.
    - Definir os Data Transfer Objects (DTOs) para entrada e saída de dados.

### Uso de DTOs (Data Transfer Objects)

A aplicação faz uso extensivo do padrão DTO para a comunicação através de sua API, separando a representação de dados da camada de API das entidades de domínio.

- **DTOs de Entrada (Request):** Modelam os dados esperados do cliente para uma operação específica (ex: `PropostaCadastroRequest`). Os DTOs de entrada foram moldados para garantir que apenas as informações solicitadas pelo sistema sejam aceitas e possibilitam a validação dos dados de entrada na camada de Controller usando anotações do Jakarta Bean Validation (`@Valid`, `@NotNull`, `@Size`, etc.). Isso garante que apenas dados válidos cheguem à camada de serviço, simplificando a lógica de negócio e melhorando a segurança.

- **DTOs de Saída (Response):** Modelam os dados que serão enviados de volta ao cliente (ex: `PropostaCadastroResponse`). Os DTOs de saída foram projetadas para atender às necessidades do cliente, e não para espelhar a estrutura interna das entidades de domínio. Isso evita a exposição de detalhes de implementação do banco de dados e permite a construção de respostas enriquecidas, agregando dados de múltiplas entidades em um único objeto, como pode ser visto em `PropostaCadastroResponse` que combina dados de `Proposta`, `Requisitante`, `Fornecedor`, etc.

### Relação entre Entidades de Domínio e DTOs

O fluxo de dados e o mapeamento entre entidades e DTOs são orquestrados na camada de serviço para garantir que a lógica de negócio permaneça desacoplada da representação de dados da API.

- **Mapeamento:** O mapeamento é realizado de forma explícita, nos construtores dos DTOs de resposta ou em métodos de serviço.
  - **Entrada (Request DTO -> Entidade):** Em `GerenciadorPropostaService.cadastrarProposta`, os dados do `PropostaCadastroRequest` são extraídos para criar e popular as entidades de domínio (`Proposta`, `Fornecedor`, etc.) antes da persistência.
  - **Saída (Entidade -> Response DTO):** O `PropostaCadastroResponse` possui um construtor que aceita a entidade `Proposta` e a transforma na estrutura de dados desejada para o cliente, inicializando outros DTOs aninhados (`RequisitanteResponse`, `FornecedorResponse`) no processo.

- **Orquestração de Dados:** Os serviços atuam como orquestradores. Um bom exemplo é o `RelatorioService`:
  1. Ele recebe IDs e busca as entidades `Proposta` no banco de dados.
  2. Mapeia as entidades para um DTO específico (`PropostaRelatorioApi`) para a chamada de uma API externa.
  3. Invoca a API externa através do `RelatoriosFeignClient`.
  4. Recebe a resposta da API externa (outro DTO).
  5. Constrói o DTO de resposta final (`ComparacaoPropostasResponse`) para o cliente, combinando as informações obtidas do banco de dados e da API externa.

## Segurança

Esta seção detalha a estratégia de segurança implementada na API, cobrindo autenticação, autorização, princípios de design e validação.

### Configuração Essencial e Autenticação HTTP Basic

A segurança da API é fundamentada pelo Spring Security. Foi estabelecido um mecanismo de autenticação robusto utilizando o padrão **HTTP Basic**, que garante que todas as requisições a endpoints protegidos sejam validadas.

- **Usuários em Memória:** Para o ambiente de desenvolvimento e demonstração, foram definidos usuários em memória com diferentes níveis de acesso (`ADMIN` e `USER`) na classe `SecurityConfig.java`.
- **Codificação de Senhas:** As senhas são tratadas de forma segura utilizando `BCryptPasswordEncoder`, que é o padrão recomendado pelo Spring Security para codificar as senhas antes de armazená-las.

### Implementação de Autorização Granular

Após a autenticação, o acesso aos recursos é controlado de forma granular, segregando permissões entre as roles `ADMIN` e `USER`.

- **Autorização via URL Matching:** A configuração principal de autorização está centralizada em `SecurityConfig.java`, utilizando `requestMatchers` para definir regras com base em padrões de URL e métodos HTTP.
  - **Endpoints Públicos:** Endpoints como o console do H2 (`/h2/**`) e as rotas para o fornecedor preencher a proposta (`GET /api/gerenciador-proposta/*/proposta/modelo`, `PUT /api/gerenciador-proposta/*/proposta/cadastrar`) são públicos para permitir a funcionalidade principal do sistema.
  - **Acesso de ADMIN:** Operações críticas e administrativas, como gerar tokens (`POST /api/gerenciador-proposta/gerar-token`), invalidá-los (`PATCH /api/gerenciador-proposta/*/invalidar`) e limpar gerenciadores (`DELETE /api/gerenciador-proposta/limpar`), são restritas exclusivamente à role `ADMIN`.
  - **Acesso de USER e ADMIN:** Endpoints de consulta (`GET /api/gerenciador-proposta/**`) são acessíveis tanto por `ADMIN` quanto por `USER`.

- **Autorização a Nível de Método:** Em controladores como `PropostaController`, a anotação `@PreAuthorize` é utilizada para aplicar regras de segurança diretamente nos métodos, oferecendo uma camada adicional e mais específica de controle de acesso.

### Princípios de Segurança

A implementação segue princípios fundamentais de segurança para garantir um design robusto.

- **Princípio do Menor Privilégio:** As roles foram definidas para conceder apenas as permissões estritamente necessárias. A role `USER` tem acesso limitado principalmente à leitura de dados, enquanto a `ADMIN` possui permissões mais amplas para gerenciamento, garantindo que os usuários não possam realizar ações além de suas responsabilidades.
- **Segurança por Padrão:** A regra `anyRequest().authenticated()` no `SecurityConfig.java` garante que qualquer endpoint não configurado explicitamente seja bloqueado por padrão, exigindo autenticação. Isso previne exposições acidentais de novos endpoints.
- **Centralização da Lógica de Segurança:** Ao invés de verificações manuais nos controladores, a lógica de autorização foi centralizada na configuração do Spring Security (`SecurityConfig.java`) e através de anotações declarativas (`@PreAuthorize`). Isso torna as regras de segurança mais claras, fáceis de gerenciar e menos propensas a erros.

## Como Executar

**Pré-requisitos**:
   - Java 21 ou superior;
   - Maven 3.9.x;
   - Postman (ou similar) para testar os endpoints da aplicação.

**Executando a Aplicação**:
- API Propostas de Preço:
   1. No terminal entre na pasta `raiz` do projeto e em seguida navegue até a pasta `joaoanderson-apis/proposta-precos-api/main-app`;
   2. Execute o comando abaixo para executar a aplicação:
   ```sh
   mvn spring-boot:run
   ```
   3. A aplicação será iniciada em `http://localhost:8080` por padrão.
- API de Relatórios:
   1. No terminal entre na pasta `raiz` do projeto e em seguida navegue até a pasta `joaoanderson-apis/relatorios-api`;
   2. Execute o comando abaixo para executar a aplicação:
   ```sh
   mvn spring-boot:run
   ```
   3. A aplicação será iniciada em `http://localhost:8081` por padrão.

## Tecnologias Utilizadas
* Spring Boot 3.5.x
* Java 21
* Maven
* Banco de Dados H2 (em memória)

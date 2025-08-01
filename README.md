# Sistema de Cadastro de Propostas de Preço

Este projeto é um sistema web para cadastro, envio e comparação de propostas de preço. Ele permite que requisitantes criem modelos de propostas, enviem links para empresas preencherem os dados e preços, e armazenem as propostas recebidas para consulta e comparação.

## Funcionalidades

- **Criação de Modelo de Proposta:**  
  O requisitante cadastra um modelo contendo a descrição e as quantidades dos materiais desejados.

- **Criação de Link para Empresas:**  
  O sistema gera um link para que empresas fornecedoras preencham seus dados e informem os preços dos materiais solicitados.

- **Recebimento e Armazenamento de Propostas:**  
  As propostas preenchidas pelas empresas são retornadas ao solicitante e armazenadas no banco de dados.

- **Comparação e Consulta de Propostas:**  
  O requisitante pode comparar diferentes propostas recebidas e consultar propostas anteriores.

## Como Executar

**Pré-requisitos**:
   - Java 21+ ou superior;
   - Maven 3.9+;
   - Postman (ou similar) para testar os endpoints da aplicação.

**Executando a Aplicação**:
   1. No terminal entre na pasta `raiz` do projeto e em seguida na pasta `joaoandersonapi`;
   2. Execute o comando abaixo para executar a aplicação:
   ```sh
   mvn spring-boot:run
   ```
   3. A aplicação será iniciada em `http://localhost:8080` por padrão.

## Dependências e Tecnologias Utilizadas
* Spring Boot 3.5.x
* Java 21
* Maven

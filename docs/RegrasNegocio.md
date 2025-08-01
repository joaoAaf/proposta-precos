## Especificidades

- **Requisitante**: Instituição que necessita de propostas de preços para realização de uma aquisição ou estudo dos preços de mercado;
- **Fornecedor**: Empresa que fornecerá a proposta para instituição requisitante;

## Regras de Negócio

### 1. Cadastro de Propostas

- O requisitante deve poder cadastrar um modelo de proposta, que dará origem a uma proposta.
- Um modelo de proposta deve ter os seguintes atributos:
	* Dados da instituição requisitante da proposta: nome, CNPJ, endereço, emails, telefones, setor e responsável;
	* Os materiais cadastrados pelo requisitante: descrição, unidade, quantidade, preço e se foi adquirido ou não;
	* Observações gerais sobre a proposta;
	* A quantidade de Fornecedores que o modelo está disponível.
- A partir de um modelo de proposta o requisitante deve ser capaz de gerar um link para cada Fornecedor, que possibilitará cadastrar uma única proposta.
- Após o cadastro da proposta ou passados 5 dias uteis o link supracitado deve ser invalidado.
- O requisitante deve poder invalidar o link a qualquer tempo.
- Uma proposta deve ter os seguintes atributos:
	* Dados da instituição requisitante da proposta: nome, CNPJ, endereço, emails, telefones e responsável;
	* Dados da empresa Fornecedor da proposta: nome, CNPJ, endereço e telefone;
	* Os materiais cadastrados pelo requisitante: descrição, unidade, quantidade, preço e se foi adquirido ou não;
	* Porcentagem de desconto a ser aplicado no somatório dos preços totais dos materiais;
	* Endereço de entrega dos materiais;
	* Observações gerais sobre a proposta;
	* Um documento em pdf ou imagem, contendo a proposta preenchida e assinada pela Fornecedor.
- Com exceção da porcentagem de desconto, o preenchimento de todos os dados da proposta devem ser obrigatórios.
- O próprio requisitante também deve ser capaz de cadastrar uma proposta, no caso de preços coletados em sites especializados na internet, em tabelas de preços como as da SINAPI ou devido impossibilidade da Fornecedor preencher a proposta.

### 2. Calculo Preço Global da Proposta

- O sistema deve calcular o **preço global** de cada proposta.
- O calculo do preço global consiste no somatório dos preços totais dos materiais da proposta, considerando a porcentagem de desconto, se houver.

### 4. Gerenciar Dados da Proposta

- O sistema deve permitir que o requisitante corrija os dados da proposta, caso necessário.
- O sistema deve permitir que o requisitante remova materiais da proposta, caso os preços sejam discrepantes ou zerados.

### 5. Exclusão da Proposta

- O sistema deve permitir a exclusão de uma proposta.

### 6. Comparação de Propostas

- O sistema deve permitir que o requisitante selecione duas ou mais propostas para calculo da **média** de seus preços globais.
- No caso de três ou mais propostas e o desvio padrão dos preços globais for superior a 25%, o sistema deve utilizar a **mediana** no lugar da média.
- O sistema deve permitir a verificar se o preço global de uma proposta qualquer, selecionada pelo requisitante, **não excede em 20%** da média ou mediana de duas ou mais propostas também selecionadas por ele.
- O sistema deve permitir a criação de relatórios em pdf para os casos supracitados.

### 7. Registro e Auditoria

- Todas as ações, análises e decisões devem ser registradas no sistema para fins de auditoria e rastreabilidade.
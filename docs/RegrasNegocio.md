## Especificidades

- **Requisitante**: Instituição que necessita de propostas de preços para realização de uma aquisição ou estudo dos preços de mercado;
- **Fornecedor**: Empresa que fornecerá a proposta para instituição requisitante;

## Regras de Negócio

### 1. Cadastro de Propostas

- O requisitante deve poder cadastrar um modelo de proposta, que dará origem a uma proposta.
- Um modelo de proposta deve ter os seguintes atributos:
	* Dados da instituição requisitante da proposta: nome, CNPJ, endereço, email, telefone, setor e  o nome do responsável;
	* Os materiais cadastrados pelo requisitante: descrição, unidade, quantidade, preço e se foi adquirido ou não;
	* Observações do requisitante sobre a proposta.
- A partir de um modelo de proposta o requisitante deve ser capaz de gerar um link para cada Fornecedor, que possibilitará cadastrar uma única proposta.
- Após o cadastro da proposta ou passados 5 dias uteis o link supracitado deve ser invalidado.
- O requisitante deve poder invalidar o link a qualquer tempo.
- Uma proposta deve ter os seguintes atributos:
	* Dados da instituição requisitante da proposta: nome, CNPJ, endereço, email, telefone, setor e  o nome do responsável;
	* Dados da empresa Fornecedor da proposta: nome, CNPJ, endereço, email e telefone;
	* Os materiais cadastrados pelo requisitante: descrição, unidade, quantidade, preço e se foi adquirido ou não;
	* Porcentagem de desconto a ser aplicado no somatório dos preços totais dos materiais;
	* Endereço de entrega dos materiais;
	* Observações do requisitante sobre a proposta;
	* Observações do Fornecedor sobre a proposta;
	* Um documento em pdf ou imagem, contendo a proposta preenchida e assinada pela Fornecedor.
- Com exceção da porcentagem de desconto, o preenchimento de todos os dados da proposta devem ser obrigatórios.
- O próprio requisitante também deve ser capaz de cadastrar uma proposta, no caso de preços coletados em sites especializados na internet, em tabelas de preços como as da SINAPI ou devido impossibilidade da Fornecedor preencher a proposta.

### 2. Calculo Preço Global da Proposta

- O sistema deve calcular o **preço global** de cada proposta.
- O calculo do preço global consiste no somatório dos preços totais dos materiais da proposta, considerando a porcentagem de desconto, se houver.

### 4. Gerenciar Proposta

- O sistema deve permitir que o requisitante visualize as propostas cadastradas.
- O sistema deve permitir que o requisitante corrija os dados da proposta, caso necessário.
- O sistema deve permitir que o requisitante remova materiais da proposta, caso os preços sejam discrepantes ou zerados.
- O sistema deve permitir a exclusão ou invalidação de uma proposta.

### 5. Comparação de Propostas

- O sistema deve emitir um relatório comparando os preços globais de duas ou mais propostas selecionadas pelo requisitante. Este relatório deve conter:
	* O calculo da **média** dos preços globais;
	* O calculo da **mediana** dos preços globais, para o caso de duas propostas a média e a mediana serão iguais;
	* Identificação da(s) proposta(s) com menor preço global; 
	* O calculo do **desvio padrão percentual** dos preços globais;

### 6. Comparação de Proposta com preço de mercado

- O sistema deve emitir um relatório comparando o preço global de uma proposta selecionada pelo requisitante com o preço de mercado. Este relatório deve conter:
	* O calculo do preço de mercado, representado pela **média** dos preços globais de duas ou mais propostas, selecionadas pelo requisitante, ou pela **mediana** quando o **desvio padrão percentual** dos preços globais for superior a **25%** e houverem mais de duas propostas;
	* A diferença percentual entre o preço global da proposta e o preço de mercado;
- O sistema não deve emitir este relatório se **desvio padrão percentual** for superior a **25%** e houverem menos de três propostas;

### 7. Registro e Auditoria

- Todas as ações, análises e decisões devem ser registradas no sistema para fins de auditoria e rastreabilidade.
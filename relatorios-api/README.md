# API de Relatórios para Propostas de Preço

## Funcionalidade Escolhida

Optou-se por implementar a funcionalidade de **Comparação entre Propostas**, conforme detalhado nas regras de negócio ([/docs/RegrasNegocio.md](/docs/RegrasNegocio.md)). Essa escolha se deu pela relevância para o processo decisório do requisitante, permitindo comparar preços globais de diferentes propostas recebidas e gerar relatórios com média, mediana, desvio padrão percentual, identificação da(s) proposta(s) mais vantajosa(s) e recomendações baseadas na análise dos resultados. A funcionalidade se integra ao projeto por meio de endpoints REST que recebem as propostas selecionadas e retornam o relatório com os dados solicitados.

## Principais Classes do Modelo

- [`Proposta`](/joaoandersonrelatoriosapi/src/main/java/br/edu/infnet/joaoandersonrelatoriosapi/model/domain/Proposta.java): representa uma proposta selecionada, incluindo o id da proposta, materiais, desconto e preço global.
- [`Material`](/joaoandersonrelatoriosapi/src/main/java/br/edu/infnet/joaoandersonrelatoriosapi/model/domain/Material.java): representa os itens da proposta, com quantidade, preço unitário e preço total.
- [`Relatorio`](/joaoandersonrelatoriosapi/src/main/java/br/edu/infnet/joaoandersonrelatoriosapi/model/domain/Relatorio.java): encapsula os dados básicos do relatório e os métodos de análise, como média, mediana, desvio padrão e identificação da(s) proposta(s) mais vantajosa(s).
- [`RelatorioComparacaoPropostas`](/joaoandersonrelatoriosapi/src/main/java/br/edu/infnet/joaoandersonrelatoriosapi/model/domain/RelatorioComparacaoPropostas.java): especialização de `Relatorio` para a funcionalidade de comparação entre propostas, implementando a lógica específica para gerar o relatório conforme as regras de negócio.
- [`RelatorioService`](/joaoandersonrelatoriosapi/src/main/java/br/edu/infnet/joaoandersonrelatoriosapi/model/service/RelatorioService.java): serviço que gerencia a criação do relatório de comparação de propostas.

## Amostra dos Cenários de Teste Unitário

1. **DisplayName:** "Deve retornar erro quando a lista de propostas for nula"
   - **Descrição:** Verifica o comportamento dos métodos que manipulam uma lista de propostas quando esta é nula. Ciclo Red: É criado um teste que simula uma proposta nula e espera um erro `IllegalArgumentException`, mas falha, pois o método `verificarPropostas` ainda não está implementado. Ciclo Green: O método `verificarPropostas` é implementado e passa a lançar a exceção esperada para uma lista nula, fazendo com que o teste passe.
   - **Implementação:** [`RelatorioTests`](/joaoandersonrelatoriosapi/src/test/java/br/edu/infnet/joaoandersonrelatoriosapi/RelatorioTests.java)

2. **DisplayName:** "Deve retornar erro quando houver menos propostas que o esperado"
   - **Descrição:** Verifica o comportamento dos métodos que manipulam uma lista de propostas quando esta contém menos propostas do que o esperado. Ciclo Red: É criado um teste que simula uma lista com menos propostas e espera um erro `IllegalArgumentException` com uma mensagem específica, mas falha, pois o método `verificarPropostas` ainda não supre essa necessidade. Ciclo Green: O método `verificarPropostas` é refatorado e passa a lançar a exceção esperada com a mensagem específica para uma lista com menos propostas, fazendo com que o teste passe.
   - **Implementação:** [`RelatorioTests`](/joaoandersonrelatoriosapi/src/test/java/br/edu/infnet/joaoandersonrelatoriosapi/RelatorioTests.java)

3. **DisplayName:** "Deve gerar relatório de comparação de propostas quando houver duas propostas"
   - **Descrição:** Verifica se o relatório de comparação é gerado corretamente quando duas propostas são fornecidas. Ciclo Red: Um teste é criado com duas propostas e espera-se um relatório com as informações corretas, mas falha, pois a lógica de geração de relatórios ainda não está implementada. Ciclo Green: A lógica de geração de relatórios é implementada nas classes `RelatorioComparacaoPropostas` e `RelatorioService`, fazendo com que o teste passe.
   - **Implementação:** [`RelatorioServiceTests`](/joaoandersonrelatoriosapi/src/test/java/br/edu/infnet/joaoandersonrelatoriosapi/RelatorioServiceTests.java)

4. **DisplayName:** "Deve gerar relatório de comparação de propostas quando houver mais de duas propostas"
   - **Descrição:** Verifica se o relatório de comparação é gerado corretamente quando três propostas são fornecidas. Ciclo Red: Um teste é criado com três propostas e espera-se um relatório com as informações corretas, mas falha, pois a lógica de geração de relatórios ainda não está implementada. Ciclo Green: A lógica de geração de relatórios é implementada nas classes `RelatorioComparacaoPropostas` e `RelatorioService`, fazendo com que o teste passe.
   - **Implementação:** [`RelatorioServiceTests`](/joaoandersonrelatoriosapi/src/test/java/br/edu/infnet/joaoandersonrelatoriosapi/RelatorioServiceTests.java)
package br.edu.infnet.joaoandersonrelatoriosapi.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.joaoandersonrelatoriosapi.model.domain.Proposta;
import br.edu.infnet.joaoandersonrelatoriosapi.model.domain.ComparacaoPropostas;

@Service
public class RelatorioService {

    public ComparacaoPropostas gerarRelatorioComparacaoPropostas(List<Proposta> propostas) {
        return new ComparacaoPropostas(propostas);
    }

    public String montarRequisicaoIA(ComparacaoPropostas relatorio) {
        return String.format(
            "Com base nos seguintes dados de um relatório de comparação de propostas de preços:\n\n" +
            "Média dos preços globais: %s\n" +
            "Mediana dos preços globais: %s\n" +
            "Desvio padrão percentual: %s\n" +
            "Menor preço global: %s\n" +
            "Quantidade de propostas coletadas: %s\n\n" +
            "Crie um texto de conclusão para o relatório. A conclusão deve analisar os dados e seguir estritamente as seguintes regras:\n" +
            "- Se os preços forem homogêneos (desvio padrão percentual menor ou igual a 25%%), " +
            "recomende a utilização da média como preço de referência.\n" +
            "- Se os preços forem heterogêneos (desvio padrão percentual maior que 25%%), " +
            "recomende a utilização da mediana como preço de referência.\n" +
            "- Se o desvio padrão percentual for maior que 50%%, além da recomendação sobre o preço de referência, " +
            "sugira também a coleta de novas propostas para uma análise mais robusta.\n\n" +
            "Sua resposta deve conter apenas o texto de conclusão, sem títulos ou qualquer texto adicional.",
            relatorio.getMedia(), relatorio.getMediana(), relatorio.getDesvioPadraoPercentual(),
            relatorio.getMenorPreco(), relatorio.getPropostas().size()
        );
    }

}

package br.edu.infnet.joaoandersonrelatoriosapi.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.edu.infnet.joaoandersonrelatoriosapi.clients.GeminiFeignClient;
import br.edu.infnet.joaoandersonrelatoriosapi.model.domain.ComparacaoPropostas;
import br.edu.infnet.joaoandersonrelatoriosapi.model.domain.Proposta;
import br.edu.infnet.joaoandersonrelatoriosapi.model.dto.gemini.Content;
import br.edu.infnet.joaoandersonrelatoriosapi.model.dto.gemini.GeminiRequest;
import br.edu.infnet.joaoandersonrelatoriosapi.model.dto.gemini.Part;
import br.edu.infnet.joaoandersonrelatoriosapi.use_cases.RelatorioUseCases;

@Service
public class RelatorioService implements RelatorioUseCases {

    private final GeminiFeignClient geminiClient;
    private final String geminiApiKey;

    public RelatorioService(GeminiFeignClient geminiClient, @Value("${gemini.api.key}") String geminiApiKey) {
        this.geminiClient = geminiClient;
        this.geminiApiKey = geminiApiKey;
    }

    @Override
    public ComparacaoPropostas gerarRelatorioComparacaoPropostas(List<Proposta> propostas) {
        var relatorio = new ComparacaoPropostas(propostas);
        var prompt = this.montarPromptIA(relatorio);
        var part = new Part(prompt);
        var content = new Content(List.of(part));
        var geminiRequest = new GeminiRequest(List.of(content));        
        var conclusao = this.geminiClient.consultarGemini(geminiRequest, this.geminiApiKey).extractText();
        relatorio.setConclusao(conclusao);
        return relatorio;
    }

    private String montarPromptIA(ComparacaoPropostas relatorio) {
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

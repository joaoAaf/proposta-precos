package br.com.apisemaperreio.proposta_precos.model.dto.relatorio;

import java.time.LocalDate;
import java.util.List;

public class ComparacaoPropostasApi extends ComparacaoPropostas {

    private List<PropostaRelatorioApi> propostas;
    private List<PropostaRelatorioApi> propostasVantajosas;

    public ComparacaoPropostasApi(LocalDate dataEmissao, Double media, Double mediana, String conclusao,
            List<PropostaRelatorioApi> propostas, List<PropostaRelatorioApi> propostasVantajosas) {
        super(dataEmissao, media, mediana, conclusao);
        this.propostas = propostas;
        this.propostasVantajosas = propostasVantajosas;
    }

    public List<PropostaRelatorioApi> getPropostas() {
        return propostas;
    }

    public List<PropostaRelatorioApi> getPropostasVantajosas() {
        return propostasVantajosas;
    }

}

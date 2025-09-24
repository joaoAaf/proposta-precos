package br.edu.infnet.joaoandersonapi.dtos.comparacao_propostas;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public class ComparacaoPropostasApi extends ComparacaoPropostas {

    
    @NotEmpty
    private List<PropostaRelatorioApi> propostas;
    
    @NotEmpty
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

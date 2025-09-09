package br.edu.infnet.joaoandersonrelatoriosapi.model.domain;

import java.math.BigDecimal;
import java.util.List;

public class RelatorioComparacaoPropostas {

    private BigDecimal media;
    private BigDecimal mediana;
    private BigDecimal desvioPadraoPercentual;
    private List<Proposta> propostasVantajosas;
    private String conclusao;
    
    public BigDecimal getMedia() {
        return media;
    }
    public BigDecimal getMediana() {
        return mediana;
    }
    public BigDecimal getDesvioPadraoPercentual() {
        return desvioPadraoPercentual;
    }
    public List<Proposta> getPropostasVantajosas() {
        return propostasVantajosas;
    }
    public String getConclusao() {
        return conclusao;
    }

}

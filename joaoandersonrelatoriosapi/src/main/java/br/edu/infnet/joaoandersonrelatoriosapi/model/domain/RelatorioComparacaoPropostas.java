package br.edu.infnet.joaoandersonrelatoriosapi.model.domain;

import java.math.BigDecimal;
import java.util.List;

public class RelatorioComparacaoPropostas extends Relatorio {

    private BigDecimal media;
    private BigDecimal mediana;
    private BigDecimal desvioPadraoPercentual;
    private List<Proposta> propostasVantajosas;
    private String conclusao;
    
    public RelatorioComparacaoPropostas(List<Proposta> propostas) {
        super(propostas);
        this.media = super.calcularMedia();
        this.mediana = propostas.size() > 2 ? super.calcularMediana() : super.calcularMedia();
        this.desvioPadraoPercentual = super.calcularDesvioPadraoPercentual();
        this.propostasVantajosas = super.calcularMenorPreco();
    }

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

    public void setConclusao(String conclusao) {
        this.conclusao = conclusao;
    }

}

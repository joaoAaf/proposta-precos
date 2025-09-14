package br.edu.infnet.joaoandersonrelatoriosapi.model.domain;

import java.math.BigDecimal;
import java.util.List;

public class ComparacaoPropostas extends Relatorio {

    private BigDecimal media;
    private BigDecimal mediana;
    private BigDecimal desvioPadraoPercentual;
    private List<Proposta> propostasVantajosas;
    private String conclusao;
    
    public ComparacaoPropostas(List<Proposta> propostas) {
        super(propostas);
        this.media = super.calcularMedia();
        this.mediana = propostas.size() > 2 ? super.calcularMediana() : super.calcularMedia();
        this.desvioPadraoPercentual = super.calcularDesvioPadraoPercentual();
        this.propostasVantajosas = super.obterPropostasVantajosas();
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

    public BigDecimal getMenorPreco() {
        return propostasVantajosas.get(0).precoGlobal();
    }

    public String getConclusao() {
        return conclusao;
    }

    public void setConclusao(String conclusao) {
        this.conclusao = conclusao;
    }

}

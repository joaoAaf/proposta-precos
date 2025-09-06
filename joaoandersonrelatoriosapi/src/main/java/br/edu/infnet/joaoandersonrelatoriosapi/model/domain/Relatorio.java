package br.edu.infnet.joaoandersonrelatoriosapi.model.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Relatorio {

    private LocalDate dataEmissao = LocalDate.now();
    private List<Proposta> propostas;

    public Relatorio(List<Proposta> propostas) {
        this.propostas = propostas;
    }

    public void verificarPropostas(int minPropostas) {
        throw new UnsupportedOperationException("Método não implementado");
    }

    public BigDecimal calcularMedia() {
        throw new UnsupportedOperationException("Método não implementado");
    }

    public BigDecimal calcularMediana() {
        throw new UnsupportedOperationException("Método não implementado");
    }

    public BigDecimal calcularDesvioPadrao() {
        throw new UnsupportedOperationException("Método não implementado");
    }

    public List<Proposta> calcularMenorPreco() {
        throw new UnsupportedOperationException("Método não implementado");
    }

    public BigDecimal calcularPorcentagemPrecoMercado(Proposta proposta) {
        throw new UnsupportedOperationException("Método não implementado");
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public List<Proposta> getPropostas() {
        return propostas;
    }

    @Override
    public String toString() {
        return "Relatorio [dataEmissao=" + dataEmissao + ", propostas=" + propostas + ", media=" + calcularMedia()
                + ", mediana=" + calcularMediana() + ", desvioPadrao=" + calcularDesvioPadrao() + ", menorPreco="
                + calcularMenorPreco() + "]";
    }

}

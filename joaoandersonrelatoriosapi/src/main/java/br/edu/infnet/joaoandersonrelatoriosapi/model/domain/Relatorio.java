package br.edu.infnet.joaoandersonrelatoriosapi.model.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record Relatorio(LocalDate dataEmissao, List<Proposta> propostas) {

    public Relatorio(List<Proposta> propostas) {
        this(LocalDate.now(), propostas);
    }

    public void verificarPropostas(int minPropostas) {
        if (this.propostas == null || this.propostas.size() < minPropostas) {
            throw new IllegalArgumentException(
                    "É necessário ter pelo menos " + minPropostas + " propostas para realizar a operação.");
        }
    }

    public BigDecimal calcularMedia() {
        this.verificarPropostas(2);
        var soma = this.propostas.stream()
                .map(proposta -> proposta.precoGlobal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return soma.divide(BigDecimal.valueOf(this.propostas.size()), 2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal calcularMediana() {
        this.verificarPropostas(3);
        var precos = this.propostas.stream()
                .map(Proposta::precoGlobal)
                .sorted()
                .toList();
        if (precos.size() % 2 == 1)
            return precos.get(precos.size() / 2).setScale(2, RoundingMode.HALF_EVEN);
        return precos.get(precos.size() / 2 - 1)
                .add(precos.get(precos.size() / 2))
                .divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal calcularDesvioPadraoPercentual() {
        this.verificarPropostas(2);
        var media = this.calcularMedia();
        var somaQuadrados = this.propostas.stream()
                .map(Proposta::precoGlobal)
                .map(preco -> preco.subtract(media).pow(2))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var variancia = somaQuadrados.divide(BigDecimal.valueOf(this.propostas.size()), RoundingMode.HALF_EVEN);
        var desvioPadrao = BigDecimal.valueOf(Math.sqrt(variancia.doubleValue()));
        var desvioPadraoPercentual = desvioPadrao.divide(media, 4, RoundingMode.HALF_EVEN)
                .multiply(BigDecimal.valueOf(100));
        return desvioPadraoPercentual.setScale(2, RoundingMode.HALF_EVEN);
    }

    public List<Proposta> calcularMenorPreco() {
        this.verificarPropostas(2);
        var propostasVantajosas = new ArrayList<Proposta>();
        var menorPreco = BigDecimal.ZERO;
        for (var proposta : this.propostas) {
            if (menorPreco.equals(BigDecimal.ZERO) || proposta.precoGlobal().compareTo(menorPreco) < 0) {
                menorPreco = proposta.precoGlobal();
                propostasVantajosas.clear();
                propostasVantajosas.add(proposta);
            } else if (proposta.precoGlobal().compareTo(menorPreco) == 0)
                propostasVantajosas.add(proposta);
        }
        return propostasVantajosas;
    }

    public BigDecimal calcularPorcentagemPrecoMercado(Proposta proposta) {
        this.verificarPropostas(2);
        return proposta.precoGlobal()
                .divide(this.calculaPrecoMercado(), 4, RoundingMode.HALF_EVEN)
                .subtract(BigDecimal.ONE)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal calculaPrecoMercado() {
        var desvioPadraoPercentual = this.calcularDesvioPadraoPercentual();
        if (desvioPadraoPercentual.compareTo(BigDecimal.valueOf(25)) <= 0)
            return this.calcularMedia();
        else if (this.propostas.size() > 2)
            return this.calcularMediana();
        throw new IllegalArgumentException(
                "O desvio padrão percentual dos preços de mercado está em " + desvioPadraoPercentual
                        + "%, o maximo permitido é 25%. Adicione uma nova proposta para compor o preço de mercado.");
    }

}

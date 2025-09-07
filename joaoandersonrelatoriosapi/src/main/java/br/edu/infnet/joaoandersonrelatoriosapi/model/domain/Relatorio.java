package br.edu.infnet.joaoandersonrelatoriosapi.model.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Relatorio {

    private LocalDate dataEmissao = LocalDate.now();
    private List<Proposta> propostas;

    public Relatorio(List<Proposta> propostas) {
        this.propostas = propostas;
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
                .map(Proposta::calcularPrecoGlobal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return soma.divide(BigDecimal.valueOf(this.propostas.size()), 2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal calcularMediana() {
        this.verificarPropostas(3);
        var precos = this.propostas.stream()
                .map(Proposta::calcularPrecoGlobal)
                .sorted()
                .toList();
        if (precos.size() % 2 == 1)
            return precos.get(precos.size() / 2);
        return precos.get(precos.size() / 2 - 1)
                .add(precos.get(precos.size() / 2))
                .divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal calcularDesvioPadraoPercentual() {
        this.verificarPropostas(2);
        var media = this.calcularMedia();
        var somaQuadrados = this.propostas.stream()
                .map(Proposta::calcularPrecoGlobal)
                .map(preco -> preco.subtract(media).pow(2))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var variancia = somaQuadrados.divide(BigDecimal.valueOf(this.propostas.size()));
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
            var precoGlobal = proposta.calcularPrecoGlobal();
            if (menorPreco.equals(BigDecimal.ZERO) || precoGlobal.compareTo(menorPreco) < 0) {
                menorPreco = precoGlobal;
                propostasVantajosas.clear();
                propostasVantajosas.add(proposta);
            } else if (precoGlobal.compareTo(menorPreco) == 0)
                propostasVantajosas.add(proposta);
        }
        return propostasVantajosas;
    }

    public BigDecimal calcularPorcentagemPrecoMercado(Proposta proposta) {
        this.verificarPropostas(2);
        var media = this.calcularMedia();
        return proposta.calcularPrecoGlobal()
                .divide(media, 4, RoundingMode.HALF_EVEN)
                .subtract(BigDecimal.ONE)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public List<Proposta> getPropostas() {
        return propostas;
    }
    
}

package br.edu.infnet.joaoandersonrelatoriosapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.infnet.joaoandersonrelatoriosapi.model.domain.Material;
import br.edu.infnet.joaoandersonrelatoriosapi.model.domain.Proposta;
import br.edu.infnet.joaoandersonrelatoriosapi.model.domain.Relatorio;

public class RelatorioTests {

    private Proposta proposta1;
    private Proposta proposta2;
    private Proposta proposta3;
    private Proposta proposta4;

    @BeforeEach
    void setUp() {
        var material1 = new Material(1, 55.0, 12.0, 660.0);
        var material2 = new Material(2, 2.20, 104.6, 230.12);

        var material3 = new Material(1, 55.0, 10.0, 550.0);
        var material4 = new Material(2, 2.20, 100.5, 220.1);

        var material5 = new Material(1, 55.0, 8.0, 440.0);
        var material6 = new Material(2, 2.20, 98.5, 216.7);

        var material7 = new Material(1, 55.0, 20.0, 1100.0);
        var material8 = new Material(2, 2.20, 120.0, 264.0);

        this.proposta1 = new Proposta(Long.valueOf(1), Arrays.asList(material1, material2), 0.0,
                BigDecimal.valueOf(890.12));
        this.proposta2 = new Proposta(Long.valueOf(2), Arrays.asList(material3, material4), 0.0,
                BigDecimal.valueOf(771.1));
        this.proposta3 = new Proposta(Long.valueOf(3), Arrays.asList(material5, material6), 0.0,
                BigDecimal.valueOf(656.7));
        this.proposta4 = new Proposta(Long.valueOf(3), Arrays.asList(material7, material8), 0.0,
                BigDecimal.valueOf(1364.0));
    }

    @Test
    @DisplayName("Deve retornar erro quando a lista de propostas for nula")
    void deveRetornarErro_quandoPropostasForNula() {
        // Dados
        var minPropostas = 2;
        var relatorio = new Relatorio(null);
        // Quando e Então
        assertThrows(IllegalArgumentException.class, () -> relatorio.verificarPropostas(minPropostas));
    }

    @Test
    @DisplayName("Deve retornar erro quando houver menos propostas que o esperado")
    void deveRetornarErro_quandoHouverMenosPropostasQueOEsperado() {
        // Dados
        var minPropostas = 2;
        var relatorio = new Relatorio(Arrays.asList(proposta1));
        var erroEsperado = new IllegalArgumentException(
                "É necessário ter pelo menos " + minPropostas + " propostas para realizar a operação.");
        // Quando e Então
        var erro = assertThrows(IllegalArgumentException.class, () -> relatorio.verificarPropostas(minPropostas));
        assertEquals(erroEsperado.getMessage(), erro.getMessage());
    }

    @Test
    @DisplayName("Deve calcular a média corretamente quando houver duas ou mais propostas")
    void deveCalcularMedia_quandoHouverDuasOuMaisPropostas() {
        // Dados
        var relatorio = new Relatorio(Arrays.asList(proposta1, proposta2));
        var resultadoEsperado = BigDecimal.valueOf(830.61).setScale(2, RoundingMode.HALF_EVEN);
        // Quando
        var media = relatorio.calcularMedia();
        // Então
        assertEquals(resultadoEsperado, media);
    }

    @Test
    @DisplayName("Deve calcular a mediana corretamente quando houver três ou mais propostas")
    void deveCalcularMediana_quandoHouverTresOuMaisPropostas() {
        // Dados
        var relatorio1 = new Relatorio(Arrays.asList(proposta1, proposta2, proposta3));
        var relatorio2 = new Relatorio(Arrays.asList(proposta1, proposta2, proposta3, proposta4));
        var resultadoEsperado1 = BigDecimal.valueOf(771.1).setScale(2, RoundingMode.HALF_EVEN);
        var resultadoEsperado2 = BigDecimal.valueOf(830.61).setScale(2, RoundingMode.HALF_EVEN);
        // Quando
        var mediana1 = relatorio1.calcularMediana();
        var mediana2 = relatorio2.calcularMediana();
        // Então
        assertEquals(resultadoEsperado1, mediana1);
        assertEquals(resultadoEsperado2, mediana2);
    }

    @Test
    @DisplayName("Deve calcular o desvio padrão corretamente quando houver duas ou mais propostas")
    void deveCalcularDesvioPadrao_quandoHouverDuasOuMaisPropostas() {
        // Dados
        var relatorio = new Relatorio(Arrays.asList(proposta1, proposta2));
        var resultadoEsperado = BigDecimal.valueOf(7.16).setScale(2, RoundingMode.HALF_EVEN);
        // Quando
        var desvioPadrao = relatorio.calcularDesvioPadraoPercentual();
        // Então
        assertEquals(resultadoEsperado, desvioPadrao);
    }

    @Test
    @DisplayName("Deve calcular o menor preço corretamente quando houver duas ou mais propostas")
    void deveCalcularMenorPreco_quandoHouverDuasOuMaisPropostas() {
        // Dados
        var relatorio = new Relatorio(Arrays.asList(proposta1, proposta2));
        var resultadoEsperado = Arrays.asList(proposta2);
        // Quando
        var propostasVantajosas = relatorio.calcularMenorPreco();
        // Então
        assertEquals(resultadoEsperado, propostasVantajosas);
    }

    @Test
    @DisplayName("Deve calcular o menor preço corretamente quando houver muitas propostas com o mesmo menor preço")
    void deveCalcularMenorPreco_quandoHouverMuitasPropostasComMesmoMenorPreco() {
        // Dados
        var relatorio = new Relatorio(Arrays.asList(proposta1, proposta2, proposta2));
        var resultadoEsperado = Arrays.asList(proposta2, proposta2);
        // Quando
        var propostasVantajosas = relatorio.calcularMenorPreco();
        // Então
        assertEquals(resultadoEsperado, propostasVantajosas);
    }

    @Test
    @DisplayName("Deve calcular a porcentagem do preço de mercado corretamente quando houver duas propostas")
    void deveCalcularPorcentagemPrecoMercado_quandoHouverDuasPropostas() {
        // Dados
        var relatorio = new Relatorio(Arrays.asList(proposta2, proposta3));
        var resultadoEsperado = BigDecimal.valueOf(24.68).setScale(2, RoundingMode.HALF_EVEN);
        // Quando
        var porcentagem = relatorio.calcularPorcentagemPrecoMercado(proposta1);
        // Então
        assertEquals(resultadoEsperado, porcentagem);
    }

    @Test
    @DisplayName("Deve calcular a porcentagem do preço de mercado corretamente quando houver três ou mais propostas")
    void deveCalcularPorcentagemPrecoMercado_quandoHouverTresOuMaisPropostas() {
        // Dados
        var relatorio = new Relatorio(Arrays.asList(proposta2, proposta3, proposta4));
        var resultadoEsperado = BigDecimal.valueOf(15.44).setScale(2, RoundingMode.HALF_EVEN);
        // Quando
        var porcentagem = relatorio.calcularPorcentagemPrecoMercado(proposta1);
        // Então
        assertEquals(resultadoEsperado, porcentagem);
    }

    @Test
    @DisplayName("Deve retornar erro ao calcular a porcentagem do preço de mercado quando houver duas propostas e o desvio padrão for maior que 25%")
    void deveRetornarErroAoCalcularPorcentagemPrecoMercado_quandoHouverDuasPropostasDesvioPadraoMaiorQue25() {
        // Dados
        var relatorio = new Relatorio(Arrays.asList(proposta3, proposta4));
        var desvioPadraoPercentual  = BigDecimal.valueOf(35).setScale(2, RoundingMode.HALF_EVEN);
        var erroEsperado = new IllegalArgumentException(
                "O desvio padrão percentual dos preços de mercado está em " + desvioPadraoPercentual
                        + "%, o maximo permitido é 25%. Adicione uma nova proposta para compor o preço de mercado.");
        // Quando e Então
        var erro = assertThrows(IllegalArgumentException.class,
                () -> relatorio.calcularPorcentagemPrecoMercado(proposta1));
        assertEquals(erroEsperado.getMessage(), erro.getMessage());
    }

}

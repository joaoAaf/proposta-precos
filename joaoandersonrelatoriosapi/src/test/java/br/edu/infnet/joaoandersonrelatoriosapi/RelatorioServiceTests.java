package br.edu.infnet.joaoandersonrelatoriosapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.infnet.joaoandersonrelatoriosapi.model.domain.Material;
import br.edu.infnet.joaoandersonrelatoriosapi.model.domain.Proposta;
import br.edu.infnet.joaoandersonrelatoriosapi.use_cases.RelatorioUseCases;

@SpringBootTest
public class RelatorioServiceTests {

    @Autowired
    private RelatorioUseCases relatorioUseCases;

    private Proposta proposta1;
    private Proposta proposta2;
    private Proposta proposta3;

    @BeforeEach
    void setUp() {
        var material1 = new Material(1, 55.0, 12.0, 660.0);
        var material2 = new Material(2, 2.20, 104.6, 230.12);

        var material3 = new Material(1, 55.0, 10.0, 550.0);
        var material4 = new Material(2, 2.20, 100.5, 220.1);

        var material5 = new Material(1, 55.0, 8.0, 440.0);
        var material6 = new Material(2, 2.20, 98.5, 216.7);

        this.proposta1 = new Proposta(Long.valueOf(1), Arrays.asList(material1, material2), 0.0,
                BigDecimal.valueOf(890.12));
        this.proposta2 = new Proposta(Long.valueOf(2), Arrays.asList(material3, material4), 0.0,
                BigDecimal.valueOf(771.1));
        this.proposta3 = new Proposta(Long.valueOf(3), Arrays.asList(material5, material6), 0.0,
                BigDecimal.valueOf(656.7));
    }

    @DisplayName("Deve gerar relatório de comparação de propostas quando houver duas propostas")
    @Test
    public void deveGerarRelatorioComparacaoPropostas_quandoHouverDuasPropostas() {
        // Dados
        var propostas = Arrays.asList(proposta1, proposta2);
        var mediaMediana = BigDecimal.valueOf(830.61).setScale(2, RoundingMode.HALF_EVEN);
        var desvioPadraoPercentual = BigDecimal.valueOf(7.16).setScale(2, RoundingMode.HALF_EVEN);
        var propostasVantajosas = Arrays.asList(proposta2);
        // Quando
        var relatorioComparacao = relatorioUseCases.gerarRelatorioComparacaoPropostas(propostas);
        // Então
        assertEquals(mediaMediana, relatorioComparacao.getMedia());
        assertEquals(mediaMediana, relatorioComparacao.getMediana());
        assertEquals(desvioPadraoPercentual, relatorioComparacao.getDesvioPadraoPercentual());
        assertEquals(propostasVantajosas, relatorioComparacao.getPropostasVantajosas());
        assertNotNull(relatorioComparacao.getConclusao());
    }

    @DisplayName("Deve gerar relatório de comparação de propostas quando houver mais de duas propostas")
    @Test
    public void deveGerarRelatorioComparacaoPropostas_quandoHouverMaisDeDuasPropostas() {
        // Dados
        var propostas = Arrays.asList(proposta1, proposta2, proposta3);
        var media = BigDecimal.valueOf(772.64).setScale(2, RoundingMode.HALF_EVEN);
        var mediana = BigDecimal.valueOf(771.1).setScale(2, RoundingMode.HALF_EVEN);
        var desvioPadraoPercentual = BigDecimal.valueOf(12.33).setScale(2, RoundingMode.HALF_EVEN);
        var propostasVantajosas = Arrays.asList(proposta3);
        // Quando
        var relatorioComparacao = relatorioUseCases.gerarRelatorioComparacaoPropostas(propostas);
        // Então
        assertEquals(media, relatorioComparacao.getMedia());
        assertEquals(mediana, relatorioComparacao.getMediana());
        assertEquals(desvioPadraoPercentual, relatorioComparacao.getDesvioPadraoPercentual());
        assertEquals(propostasVantajosas, relatorioComparacao.getPropostasVantajosas());
        assertNotNull(relatorioComparacao.getConclusao());
    }

}

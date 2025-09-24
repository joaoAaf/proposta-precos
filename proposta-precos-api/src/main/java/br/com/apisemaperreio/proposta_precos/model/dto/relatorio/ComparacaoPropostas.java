package br.com.apisemaperreio.proposta_precos.model.dto.relatorio;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ComparacaoPropostas {

    @NotNull
    private LocalDate dataEmissao;

    @NotNull
    @Positive
    private Double media;

    @NotNull
    @Positive
    private Double mediana;

    @NotBlank
    private String conclusao;

    public ComparacaoPropostas(LocalDate dataEmissao, Double media, Double mediana, String conclusao) {
        this.dataEmissao = dataEmissao;
        this.media = media;
        this.mediana = mediana;
        this.conclusao = conclusao;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public Double getMedia() {
        return media;
    }

    public Double getMediana() {
        return mediana;
    }

    public String getConclusao() {
        return conclusao;
    }

}

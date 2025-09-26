package br.com.apisemaperreio.proposta_precos.model.dto.relatorio;

import java.time.LocalDate;

public class ComparacaoPropostas {

    private LocalDate dataEmissao;
    private Double media;
    private Double mediana;
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

package br.com.apisemaperreio.proposta_precos.model.dto.relatorio;

import java.time.LocalDate;
import java.util.List;

import br.com.apisemaperreio.proposta_precos.model.domain.Proposta;
import br.com.apisemaperreio.proposta_precos.model.dto.ComparacaoPropostasApi;

public class ComparacaoPropostasResponse {

    private LocalDate dataEmissao;
    private Double media;
    private Double mediana;
    private String conclusao;
    private List<PropostaRelatorioResponse> propostas;
    private List<PropostaRelatorioResponse> propostasVantajosas;

    public ComparacaoPropostasResponse(ComparacaoPropostasApi request, List<Proposta> propostas) {
        this.dataEmissao = request.dataEmissao();
        this.media = request.media();
        this.mediana = request.mediana();
        this.conclusao = request.conclusao();
        this.propostas = propostas.stream().map(PropostaRelatorioResponse::new).toList();
        this.propostasVantajosas = request.propostasVantajosas().stream()
                .map(pv -> this.propostas.stream()
                        .filter(p -> p.id().equals(pv.id()))
                        .findFirst()
                        .orElseThrow())
                .toList();
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

    public List<PropostaRelatorioResponse> getPropostas() {
        return propostas;
    }

    public List<PropostaRelatorioResponse> getPropostasVantajosas() {
        return propostasVantajosas;
    }

}

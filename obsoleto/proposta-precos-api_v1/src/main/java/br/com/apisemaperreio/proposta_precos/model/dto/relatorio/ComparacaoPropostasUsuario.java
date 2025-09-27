package br.com.apisemaperreio.proposta_precos.model.dto.relatorio;

import java.util.List;

import br.com.apisemaperreio.proposta_precos.model.domain.Proposta;
import jakarta.validation.constraints.NotEmpty;

public class ComparacaoPropostasUsuario extends ComparacaoPropostas {

    @NotEmpty
    private List<PropostaRelatorioUsuario> propostas;

    @NotEmpty
    private List<PropostaRelatorioUsuario> propostasVantajosas;

    public ComparacaoPropostasUsuario(ComparacaoPropostasApi request, List<Proposta> propostas) {
        super(request.getDataEmissao(), request.getMedia(), request.getMediana(), request.getConclusao());
        this.propostas = propostas.stream().map(PropostaRelatorioUsuario::new).toList();
        this.propostasVantajosas = request.getPropostasVantajosas().stream()
                .map(pv -> this.propostas.stream()
                        .filter(p -> p.id().equals(pv.id()))
                        .findFirst()
                        .orElseThrow())
                .toList();
    }

    public List<PropostaRelatorioUsuario> getPropostas() {
        return propostas;
    }

    public List<PropostaRelatorioUsuario> getPropostasVantajosas() {
        return propostasVantajosas;
    }

}

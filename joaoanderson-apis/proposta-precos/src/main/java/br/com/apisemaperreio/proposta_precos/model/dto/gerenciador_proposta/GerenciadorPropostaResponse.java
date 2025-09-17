package br.com.apisemaperreio.proposta_precos.model.dto.gerenciador_proposta;

import java.time.LocalDateTime;

import br.com.apisemaperreio.proposta_precos.model.domain.GerenciadorProposta;
import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaResponse;

public record GerenciadorPropostaResponse(
        String token,
        PropostaResponse proposta,
        LocalDateTime dataCriacao,
        LocalDateTime dataExpiracao,
        boolean valido) {

    public GerenciadorPropostaResponse(GerenciadorProposta gerenciadorProposta) {
        this(gerenciadorProposta.getToken(),
                new PropostaResponse(gerenciadorProposta.getProposta()),
                gerenciadorProposta.getDataCriacao(),
                gerenciadorProposta.getDataExpiracao(),
                gerenciadorProposta.isValido());
    }

}

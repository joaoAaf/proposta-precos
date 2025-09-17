package br.com.apisemaperreio.proposta_precos.model.dto.gerenciador_proposta;

import java.time.LocalDateTime;

import br.com.apisemaperreio.proposta_precos.model.domain.GerenciadorProposta;

public record GerenciadorPropostaResponse(
        String token,
        Long idProposta,
        LocalDateTime dataCriacao,
        LocalDateTime dataExpiracao,
        boolean valido) {

    public GerenciadorPropostaResponse(GerenciadorProposta gerenciadorProposta) {
        this(gerenciadorProposta.getToken(),
                gerenciadorProposta.getProposta().getId(),
                gerenciadorProposta.getDataCriacao(),
                gerenciadorProposta.getDataExpiracao(),
                gerenciadorProposta.isValido());
    }

}

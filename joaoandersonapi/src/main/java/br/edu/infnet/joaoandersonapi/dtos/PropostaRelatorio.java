package br.edu.infnet.joaoandersonapi.dtos;

import br.edu.infnet.joaoandersonapi.model.domain.Proposta;

public record PropostaRelatorio(Long id, Double precoGlobal) {

        public PropostaRelatorio(Proposta proposta) {
                this(proposta.getId(), proposta.calcularPrecoGlobal().doubleValue());
        }

}
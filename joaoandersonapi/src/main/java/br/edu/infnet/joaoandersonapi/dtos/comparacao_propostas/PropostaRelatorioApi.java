package br.edu.infnet.joaoandersonapi.dtos.comparacao_propostas;

import br.edu.infnet.joaoandersonapi.model.domain.Proposta;

public record PropostaRelatorioApi(Long id, Double precoGlobal) {

        public PropostaRelatorioApi(Proposta proposta) {
                this(proposta.getId(), proposta.calcularPrecoGlobal().doubleValue());
        }

}
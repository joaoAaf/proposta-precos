package br.edu.infnet.proposta_precos.dtos.comparacao_propostas;

import br.edu.infnet.proposta_precos.model.domain.Proposta;

public record PropostaRelatorioApi(Long id, Double precoGlobal) {

        public PropostaRelatorioApi(Proposta proposta) {
                this(proposta.getId(), proposta.calcularPrecoGlobal().doubleValue());
        }

}
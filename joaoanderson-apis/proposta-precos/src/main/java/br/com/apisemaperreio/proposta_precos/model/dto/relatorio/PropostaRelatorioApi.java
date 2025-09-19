package br.com.apisemaperreio.proposta_precos.model.dto.relatorio;

import br.com.apisemaperreio.proposta_precos.model.domain.Proposta;

public record PropostaRelatorioApi(Long id, Double precoGlobal) {

        public PropostaRelatorioApi(Proposta proposta) {
                this(proposta.getId(), proposta.calcularPrecoGlobal().doubleValue());
        }

}
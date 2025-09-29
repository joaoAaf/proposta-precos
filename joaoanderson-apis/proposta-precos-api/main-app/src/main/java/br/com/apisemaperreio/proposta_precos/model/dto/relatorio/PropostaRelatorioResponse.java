package br.com.apisemaperreio.proposta_precos.model.dto.relatorio;

import br.com.apisemaperreio.proposta_precos.model.domain.Proposta;

public record PropostaRelatorioResponse(Long id, FornecedorRelatorioResponse fornecedor, Double precoGlobal) {

    public PropostaRelatorioResponse(Proposta proposta) {
        this(proposta.getId(), new FornecedorRelatorioResponse(proposta.getFornecedor()),
                proposta.calcularPrecoGlobal().doubleValue());
    }

}

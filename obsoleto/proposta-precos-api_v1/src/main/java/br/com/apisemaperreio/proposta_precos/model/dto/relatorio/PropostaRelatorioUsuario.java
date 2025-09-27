package br.com.apisemaperreio.proposta_precos.model.dto.relatorio;

import br.com.apisemaperreio.proposta_precos.model.domain.Proposta;

public record PropostaRelatorioUsuario(Long id, FornecedorRelatorio fornecedor, Double precoGlobal) {

    public PropostaRelatorioUsuario(Proposta proposta) {
        this(proposta.getId(), new FornecedorRelatorio(proposta.getFornecedor()),
                proposta.calcularPrecoGlobal().doubleValue());
    }

}

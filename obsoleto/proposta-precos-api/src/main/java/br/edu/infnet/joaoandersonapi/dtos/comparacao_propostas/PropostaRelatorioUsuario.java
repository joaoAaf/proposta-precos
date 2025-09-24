package br.edu.infnet.joaoandersonapi.dtos.comparacao_propostas;

import br.edu.infnet.joaoandersonapi.model.domain.Proposta;

public record PropostaRelatorioUsuario(Long id, FornecedorRelatorio fornecedor, Double precoGlobal) {

    public PropostaRelatorioUsuario(Proposta proposta) {
        this(proposta.getId(), new FornecedorRelatorio(proposta.getFornecedor()),
                proposta.calcularPrecoGlobal().doubleValue());
    }

}

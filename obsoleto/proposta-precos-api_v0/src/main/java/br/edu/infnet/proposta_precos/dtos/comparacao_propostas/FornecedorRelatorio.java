package br.edu.infnet.proposta_precos.dtos.comparacao_propostas;

import br.edu.infnet.proposta_precos.model.domain.Fornecedor;

public record FornecedorRelatorio(String cnpj, String nome, String telefone) {

    public FornecedorRelatorio(Fornecedor fornecedor) {
        this(fornecedor.getInstituicao().getCnpj(), fornecedor.getInstituicao().getNome(), fornecedor.getTelefone());
    }

}

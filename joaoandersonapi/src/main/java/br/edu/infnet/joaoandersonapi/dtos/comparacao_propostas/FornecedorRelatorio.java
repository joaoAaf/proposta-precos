package br.edu.infnet.joaoandersonapi.dtos.comparacao_propostas;

import br.edu.infnet.joaoandersonapi.model.domain.Fornecedor;

public record FornecedorRelatorio(String cnpj, String nome, String telefone) {

    public FornecedorRelatorio(Fornecedor fornecedor) {
        this(fornecedor.getInstituicao().getCnpj(), fornecedor.getInstituicao().getNome(), fornecedor.getTelefone());
    }

}

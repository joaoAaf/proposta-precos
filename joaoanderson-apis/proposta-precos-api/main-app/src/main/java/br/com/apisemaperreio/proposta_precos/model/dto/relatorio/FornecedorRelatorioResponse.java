package br.com.apisemaperreio.proposta_precos.model.dto.relatorio;

import br.com.apisemaperreio.proposta_precos.model.domain.Fornecedor;

public record FornecedorRelatorioResponse(String cnpj, String nome, String telefone) {

    public FornecedorRelatorioResponse(Fornecedor fornecedor) {
        this(fornecedor.getInstituicao().getCnpj(), fornecedor.getInstituicao().getNome(), fornecedor.getTelefone());
    }

}

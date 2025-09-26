package br.com.apisemaperreio.proposta_precos.model.dto.fornecedor;

import br.com.apisemaperreio.proposta_precos.model.domain.Fornecedor;
import br.com.apisemaperreio.proposta_precos.model.dto.instituicao.InstituicaoResponse;

public record FornecedorResponse(
                Long id,
                InstituicaoResponse instituicao,
                String email,
                String telefone,
                String responsavel) {

        public FornecedorResponse(Fornecedor fornecedor) {
                this(
                                fornecedor.getId(),
                                new InstituicaoResponse(fornecedor.getInstituicao()),
                                fornecedor.getEmail(),
                                fornecedor.getTelefone(),
                                fornecedor.getNome());
        }

}

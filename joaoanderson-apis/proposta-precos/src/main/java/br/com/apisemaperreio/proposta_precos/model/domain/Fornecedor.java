package br.com.apisemaperreio.proposta_precos.model.domain;

import br.com.apisemaperreio.proposta_precos.model.dto.fornecedor.FornecedorRequest;
import jakarta.persistence.Entity;

@Entity
public class Fornecedor extends Responsavel {

    public Fornecedor(FornecedorRequest fornecedorRequest) {
        super(new Instituicao(fornecedorRequest.instituicao()), fornecedorRequest.email(), fornecedorRequest.telefone(),
                fornecedorRequest.responsavel());
    }

    public Fornecedor() {
        super();
    }

}

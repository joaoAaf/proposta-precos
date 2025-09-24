package br.com.apisemaperreio.proposta_precos.model.domain;

import jakarta.persistence.Entity;

@Entity
public class Fornecedor extends Responsavel {

    public Fornecedor(Instituicao instituicao, String email, String telefone, String nome) {
        super(instituicao, email, telefone, nome);
    }

    public Fornecedor() {
        super();
    }

}

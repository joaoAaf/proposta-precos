package br.edu.infnet.joaoandersonapi.model.domain;

import jakarta.persistence.Entity;

@Entity
public class Fornecedor extends Responsavel {

    public Fornecedor(Instituicao instituicao, String email, String telefone, String responsavel) {
        super(instituicao, email, telefone, responsavel);
    }

}

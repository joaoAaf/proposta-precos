package br.com.apisemaperreio.proposta_precos.model.dto.instituicao;

import br.com.apisemaperreio.proposta_precos.model.domain.Instituicao;
import br.com.apisemaperreio.proposta_precos.model.dto.endereco.EnderecoResponse;

public record InstituicaoResponse(String cnpj, String nome, EnderecoResponse endereco) {

    public InstituicaoResponse(Instituicao instituicao) {
        this(
                instituicao.getCnpj(),
                instituicao.getNome(),
                new EnderecoResponse(instituicao.getEndereco()));
    }

}

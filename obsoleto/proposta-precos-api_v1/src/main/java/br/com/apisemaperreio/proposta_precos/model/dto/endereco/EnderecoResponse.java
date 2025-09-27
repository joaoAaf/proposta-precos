package br.com.apisemaperreio.proposta_precos.model.dto.endereco;

import br.com.apisemaperreio.proposta_precos.model.domain.Endereco;

public record EnderecoResponse(
        Long id,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String uf,
        String cep) {

    public EnderecoResponse(Endereco endereco) {
        this(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getUf(),
                endereco.getCep());
    }

}

package br.com.apisemaperreio.proposta_precos.model.dto.requisitante;

import br.com.apisemaperreio.proposta_precos.model.domain.Requisitante;
import br.com.apisemaperreio.proposta_precos.model.dto.instituicao.InstituicaoResponse;

public record RequisitanteResponse(
        Long id,
        InstituicaoResponse instituicao,
        String email,
        String telefone,
        String responsavel,
        String setor) {

    public RequisitanteResponse(Requisitante requisitante) {
        this(
                requisitante.getId(),
                new InstituicaoResponse(requisitante.getInstituicao()),
                requisitante.getEmail(),
                requisitante.getTelefone(),
                requisitante.getNome(),
                requisitante.getSetor());
    }

}

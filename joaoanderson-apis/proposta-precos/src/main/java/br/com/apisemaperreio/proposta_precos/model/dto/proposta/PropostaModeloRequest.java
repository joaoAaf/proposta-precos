package br.com.apisemaperreio.proposta_precos.model.dto.proposta;

import java.util.List;

import br.com.apisemaperreio.proposta_precos.model.dto.material.MaterialRequest;
import br.com.apisemaperreio.proposta_precos.model.dto.requisitante.RequisitanteRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PropostaModeloRequest(
        @Valid
        @NotNull(message = "O requisitante deve ser informado")
        RequisitanteRequest requisitante,

        @Valid
        @NotEmpty(message = "A lista de materiais não pode ser nula ou vazia")
        List<MaterialRequest> materiais,

        @Size(max = 255, message = "As observações devem ter no máximo 255 caracteres")
        String observacoesRequisitante) {

}

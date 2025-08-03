package br.edu.infnet.joaoandersonapi.model.dtos;

import java.util.List;

public record ModeloPropostaDto(RequisitanteDto requisitante, List<MaterialDto> materiais, String observacoes) {

}

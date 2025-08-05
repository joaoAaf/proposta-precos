package br.edu.infnet.joaoandersonapi.model.dtos;

import java.util.List;

public record ModeloPropostaGet(Long id, RequisitanteDto requisitante, List<MaterialDto> materiais, String observacoes) {

}

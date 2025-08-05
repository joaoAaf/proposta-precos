package br.edu.infnet.joaoandersonapi.model.dtos;

import java.util.List;

public record ModeloPropostaPost(RequisitanteDto requisitante, List<MaterialDto> materiais, String observacoes) {

}

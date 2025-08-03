package br.edu.infnet.joaoandersonapi.model.dtos;

public record RequisitanteDto(DadosBasicosDto dadosBasicos, ContatoDto contato, String responsavel, String setor) {

}

package br.edu.infnet.joaoandersonrelatoriosapi.model.domain;

public record Material(Integer numeroItem, String descricao, String unidade, Double quantidade, Double preco,
        Double precoTotal) {

}

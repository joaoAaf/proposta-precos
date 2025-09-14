package br.edu.infnet.joaoandersonapi.dtos;

import br.edu.infnet.joaoandersonapi.model.domain.Material;

public record MaterialRelatorio(Integer numeroItem, Double quantidade, Double precoUnitario, Double precoTotal) {

        public MaterialRelatorio(Material material) {
                this(material.getNumeroItem(), material.getQuantidade().doubleValue(), material.getPreco().doubleValue(),
                                material.calcularPrecoTotal().doubleValue());
        }

}

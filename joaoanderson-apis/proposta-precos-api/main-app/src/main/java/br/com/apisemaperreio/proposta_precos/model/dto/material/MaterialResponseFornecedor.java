package br.com.apisemaperreio.proposta_precos.model.dto.material;

import java.math.BigDecimal;

import br.com.apisemaperreio.proposta_precos.model.domain.Material;

public record MaterialResponseFornecedor(
                Long id,
                Integer numeroItem,
                String descricao,
                String unidade,
                BigDecimal quantidade) {

        public MaterialResponseFornecedor(Material material) {
                this(material.getId(), material.getNumeroItem(), material.getDescricao(),
                                material.getUnidade(), material.getQuantidade());

        }

}

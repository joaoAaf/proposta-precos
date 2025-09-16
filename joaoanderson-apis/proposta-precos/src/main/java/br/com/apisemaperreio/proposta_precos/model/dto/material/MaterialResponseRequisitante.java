package br.com.apisemaperreio.proposta_precos.model.dto.material;

import java.math.BigDecimal;

import br.com.apisemaperreio.proposta_precos.model.domain.Material;

public record MaterialResponseRequisitante(
                Long id,
                Integer numeroItem,
                String descricao,
                String unidade,
                BigDecimal quantidade,
                BigDecimal preco,
                boolean adquirido) {

        public MaterialResponseRequisitante(Material material) {
                this(material.getId(), material.getNumeroItem(), material.getDescricao(),
                                material.getUnidade(), material.getQuantidade(),
                                material.getPreco(), material.isAdquirido());
        }

}

package br.edu.infnet.joaoandersonapi.model.use_cases;

import java.math.BigDecimal;

import br.edu.infnet.joaoandersonapi.model.domain.Material;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.AuxUseCases;

public interface MaterialUseCases extends AuxUseCases<Material, Long, Long> {

    BigDecimal atualizarPreco(Long idMaterial, BigDecimal preco);

    BigDecimal atualizarQuantidade(Long idMaterial, BigDecimal quantidade);

    BigDecimal calcularPrecoTotal(Long idMaterial);

}

package br.edu.infnet.joaoandersonapi.model.use_cases;

import java.math.BigDecimal;

import br.edu.infnet.joaoandersonapi.model.domain.Material;

public interface MaterialUseCases extends ComumUseCases<Material, Long> {

    Material vincularModeloProposta(Long idModeloProposta, Material material);

    BigDecimal atualizarPreco(Long idMaterial, BigDecimal preco);

    BigDecimal atualizarQuantidade(Long idMaterial, BigDecimal quantidade);

    BigDecimal calcularPrecoTotal(Long idMaterial);

}

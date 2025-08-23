package br.edu.infnet.joaoandersonapi.model.use_cases;

import java.math.BigDecimal;
import java.util.List;

import br.edu.infnet.joaoandersonapi.model.domain.Material;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.AuxUseCases;

public interface MaterialUseCases extends AuxUseCases<Material, Long, Long> {

    void atualizar(List<Material> materiais);
    
    BigDecimal calcularPrecoTotal(Long idMaterial);

    void marcarAdquirido(Long idMaterial);

}

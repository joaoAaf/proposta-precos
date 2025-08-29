package br.edu.infnet.joaoandersonapi.model.use_cases;

import java.math.BigDecimal;

import br.edu.infnet.joaoandersonapi.model.domain.Material;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.AtualizarUseCase;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.CadastrarT2UseCase;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.ListarUseCase;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.ObterPorUseCase;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.RemoverUseCase;

public interface MaterialUseCases extends CadastrarT2UseCase<Material, Long>, ListarUseCase<Material>,
        ObterPorUseCase<Material, Long>, AtualizarUseCase<Material, Long>, RemoverUseCase<Long> {

    BigDecimal calcularPrecoTotal(Material material);

    void marcarAdquirido(Long idMaterial);

}

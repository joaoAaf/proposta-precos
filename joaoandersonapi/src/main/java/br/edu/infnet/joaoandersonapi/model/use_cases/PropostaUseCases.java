package br.edu.infnet.joaoandersonapi.model.use_cases;

import java.math.BigDecimal;

import br.edu.infnet.joaoandersonapi.model.domain.Proposta;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.CadastrarT1UseCase;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.ListarUseCase;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.ObterPorUseCase;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.RemoverUseCase;

public interface PropostaUseCases extends CadastrarT1UseCase<Proposta>, ListarUseCase<Proposta>,
        ObterPorUseCase<Proposta, Long>, RemoverUseCase<Long> {

    BigDecimal calcularPrecoGlobal(Proposta proposta);

}

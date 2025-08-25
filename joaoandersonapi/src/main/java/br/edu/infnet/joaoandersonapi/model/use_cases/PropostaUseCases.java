package br.edu.infnet.joaoandersonapi.model.use_cases;

import java.math.BigDecimal;

import br.edu.infnet.joaoandersonapi.model.domain.Proposta;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.UseCases;

public interface PropostaUseCases extends UseCases<Proposta, Long> {

    BigDecimal calcularPrecoGlobal(Proposta proposta);

}

package br.edu.infnet.joaoandersonapi.model.use_cases;

import br.edu.infnet.joaoandersonapi.model.domain.ModeloProposta;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.AtualizarUseCase;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.CadastrarT1UseCase;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.ListarUseCase;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.ObterPorUseCase;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.RemoverUseCase;

public interface ModeloPropostaUseCases extends CadastrarT1UseCase<ModeloProposta>, ListarUseCase<ModeloProposta>,
        ObterPorUseCase<ModeloProposta, Long>, AtualizarUseCase<ModeloProposta, Long>, RemoverUseCase<Long> {

}

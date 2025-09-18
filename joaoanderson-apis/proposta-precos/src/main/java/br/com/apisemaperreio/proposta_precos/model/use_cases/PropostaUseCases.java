package br.com.apisemaperreio.proposta_precos.model.use_cases;

import java.math.BigDecimal;

import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaCadastroResponse;
import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaCalculoRequest;
import br.com.apisemaperreio.proposta_precos.model.use_cases.common.ListarUseCase;
import br.com.apisemaperreio.proposta_precos.model.use_cases.common.ObterPorUseCase;
import br.com.apisemaperreio.proposta_precos.model.use_cases.common.RemoverUseCase;

public interface PropostaUseCases
        extends ListarUseCase<PropostaCadastroResponse>, ObterPorUseCase<PropostaCadastroResponse, Long>, RemoverUseCase<Long> {

    BigDecimal calcularPrecoGlobal(PropostaCalculoRequest propostaPreco);

    // List<Proposta> obterPorIds(List<Long> ids);

}

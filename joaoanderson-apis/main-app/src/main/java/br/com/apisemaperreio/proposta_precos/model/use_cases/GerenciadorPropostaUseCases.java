package br.com.apisemaperreio.proposta_precos.model.use_cases;

import br.com.apisemaperreio.proposta_precos.model.dto.gerenciador_proposta.GerenciadorPropostaResponse;
import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaModeloRequest;
import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaModeloResponse;
import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaCadastroRequest;
import br.com.apisemaperreio.proposta_precos.model.use_cases.common.ListarUseCase;
import br.com.apisemaperreio.proposta_precos.model.use_cases.common.ObterPorUseCase;

public interface GerenciadorPropostaUseCases
        extends ObterPorUseCase<GerenciadorPropostaResponse, String>, ListarUseCase<GerenciadorPropostaResponse> {

    String gerarToken(PropostaModeloRequest propostaModelo);

    PropostaModeloResponse obterPropostaModelo(String token);

    void cadastrarProposta(String token, PropostaCadastroRequest propostaRequest);

    void invalidarToken(String token);

    void removerInvalidosOuExpirados();
}

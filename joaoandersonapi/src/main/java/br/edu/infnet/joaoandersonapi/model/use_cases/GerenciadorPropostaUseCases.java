package br.edu.infnet.joaoandersonapi.model.use_cases;

import br.edu.infnet.joaoandersonapi.model.domain.GerenciadorProposta;
import br.edu.infnet.joaoandersonapi.model.domain.Proposta;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.ListarUseCase;
import br.edu.infnet.joaoandersonapi.model.use_cases.common.ObterPorUseCase;

public interface GerenciadorPropostaUseCases extends ObterPorUseCase<GerenciadorProposta, String>, ListarUseCase<GerenciadorProposta> {

    String gerarToken(Long modeloPropostaId);

    Proposta criarProposta(String token);

    void cadastrarProposta(String token, Proposta proposta);
    
    void invalidarToken(String token);

    void invalidarToken(GerenciadorProposta gerenciadorProposta);

    void removerInvalidosOuExpirados();

}

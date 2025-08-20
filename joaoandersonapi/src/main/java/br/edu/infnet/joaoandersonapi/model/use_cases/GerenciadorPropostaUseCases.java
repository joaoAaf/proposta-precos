package br.edu.infnet.joaoandersonapi.model.use_cases;

import java.util.List;

import br.edu.infnet.joaoandersonapi.model.domain.GerenciadorProposta;
import br.edu.infnet.joaoandersonapi.model.domain.Proposta;

public interface GerenciadorPropostaUseCases {

    String gerarToken(Long modeloPropostaId);

    GerenciadorProposta obterPor(String token);

    List<GerenciadorProposta> listar();

    Proposta criarProposta(String token);

    void cadastrarProposta(String token, Proposta proposta);
    
    void invalidarToken(String token);

    void removerInvalidosOuExpirados();

}

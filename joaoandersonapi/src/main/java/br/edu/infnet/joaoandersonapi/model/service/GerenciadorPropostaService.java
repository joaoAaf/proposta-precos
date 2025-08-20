package br.edu.infnet.joaoandersonapi.model.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.edu.infnet.joaoandersonapi.model.domain.GerenciadorProposta;
import br.edu.infnet.joaoandersonapi.model.domain.Proposta;
import br.edu.infnet.joaoandersonapi.model.repository.GerenciadorPropostaRepository;
import br.edu.infnet.joaoandersonapi.model.use_cases.GerenciadorPropostaUseCases;
import br.edu.infnet.joaoandersonapi.model.use_cases.ModeloPropostaUseCases;
import br.edu.infnet.joaoandersonapi.model.use_cases.PropostaUseCases;

@Service
public class GerenciadorPropostaService implements GerenciadorPropostaUseCases {

    private ModeloPropostaUseCases modeloPropostaUseCases;
    private PropostaUseCases propostaUseCases;
    private GerenciadorPropostaRepository gerenciadorPropostaRepository;

    public GerenciadorPropostaService(ModeloPropostaUseCases modeloPropostaUseCases, PropostaUseCases propostaUseCases,
            GerenciadorPropostaRepository gerenciadorPropostaRepository) {
        this.modeloPropostaUseCases = modeloPropostaUseCases;
        this.propostaUseCases = propostaUseCases;
        this.gerenciadorPropostaRepository = gerenciadorPropostaRepository;
    }

    private void validarParametros(Long id) {
        if (id == null || id < 1)
            throw new IllegalArgumentException("O Id não pode ser nulo ou menor que 1");
    }

    private void validarParametros(String token) {
        if (token == null || token.trim().isEmpty())
            throw new IllegalArgumentException("O token não pode ser nulo ou vazio");
    }

    @Override
    public String gerarToken(Long modeloPropostaId) {
        validarParametros(modeloPropostaId);
        var modeloProposta = modeloPropostaUseCases.obterPor(modeloPropostaId);
        var gerenciadorPropostaUseCases = new GerenciadorProposta(modeloProposta);
        return gerenciadorPropostaRepository.save(gerenciadorPropostaUseCases).getToken();
    }

    @Override
    public GerenciadorProposta obterPor(String token) {
        validarParametros(token);
        return gerenciadorPropostaRepository.findById(token)
                .orElseThrow(() -> new NoSuchElementException("Token não encontrado"));
    }

    @Override
    public List<GerenciadorProposta> listar() {
        return gerenciadorPropostaRepository.findAll();
    }

    @Override
    public Proposta criarProposta(String token) {
        var gerenciadorProposta = this.obterPor(token);
        return gerenciadorProposta.criarProposta(token);
    }

    @Override
    public void cadastrarProposta(String token, Proposta proposta) {
        var gerenciadorProposta = this.obterPor(token);
        gerenciadorProposta.validarProposta(token, proposta);
        propostaUseCases.cadastrar(proposta);
        gerenciadorProposta.invalidarToken();
    }


    @Override
    public void invalidarToken(String token) {
        var gerenciadorProposta = this.obterPor(token);
        gerenciadorProposta.invalidarToken();
    }

    @Override
    public void removerInvalidosOuExpirados() {
        gerenciadorPropostaRepository.deleteInvalidosOuExpirados();
    }

}

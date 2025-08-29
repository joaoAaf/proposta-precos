package br.edu.infnet.joaoandersonapi.model.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.infnet.joaoandersonapi.model.domain.GerenciadorProposta;
import br.edu.infnet.joaoandersonapi.model.domain.Proposta;
import br.edu.infnet.joaoandersonapi.model.repository.GerenciadorPropostaRepository;
import br.edu.infnet.joaoandersonapi.model.use_cases.GerenciadorPropostaUseCases;
import br.edu.infnet.joaoandersonapi.model.use_cases.ModeloPropostaUseCases;
import br.edu.infnet.joaoandersonapi.model.use_cases.PropostaUseCases;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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

    private void validarParametros(@NotNull(message = "Proposta não pode ser nula") @Valid Proposta proposta) {
    }

    private void validarParametros(@NotNull(message = "Gerenciador de proposta não pode ser nulo") @Valid GerenciadorProposta gerenciadorProposta) {
    }

    private void validarParametros(
            @NotNull(message = "ID do modelo de proposta não pode ser nulo")
            @Positive(message = "ID do modelo de proposta deve ser maior que zero") Long id) {
    }

    private void validarParametros(
            @NotBlank(message = "Token deve ser informado") String token) {
    }

    @Transactional
    @Override
    public String gerarToken(Long modeloPropostaId) {
        this.validarParametros(modeloPropostaId);
        var modeloProposta = modeloPropostaUseCases.obterPor(modeloPropostaId);
        var gerenciadorPropostaUseCases = new GerenciadorProposta(modeloProposta);
        return gerenciadorPropostaRepository.save(gerenciadorPropostaUseCases).getToken();
    }

    @Transactional(readOnly = true)
    @Override
    public GerenciadorProposta obterPor(String token) {
        this.validarParametros(token);
        return gerenciadorPropostaRepository.findById(token)
                .orElseThrow(() -> new NoSuchElementException("Token não encontrado"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<GerenciadorProposta> listar() {
        return gerenciadorPropostaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Proposta criarProposta(String token) {
        var gerenciadorProposta = this.obterPor(token);
        return gerenciadorProposta.criarProposta(token);
    }

    @Transactional
    @Override
    public void cadastrarProposta(String token, Proposta proposta) {
        this.validarParametros(proposta);
        var gerenciadorProposta = this.obterPor(token);
        gerenciadorProposta.validarProposta(token, proposta);
        propostaUseCases.cadastrar(proposta);
        this.invalidarToken(gerenciadorProposta);
    }

    @Transactional
    @Override
    public void invalidarToken(String token) {
        var gerenciadorProposta = this.obterPor(token);
        gerenciadorProposta.invalidarToken();
        gerenciadorPropostaRepository.save(gerenciadorProposta);
    }

    @Override
    public void invalidarToken(GerenciadorProposta gerenciadorProposta) {
        this.validarParametros(gerenciadorProposta);
        gerenciadorProposta.invalidarToken();
        gerenciadorPropostaRepository.save(gerenciadorProposta);
    }

    @Transactional
    @Override
    public void removerInvalidosOuExpirados() {
        gerenciadorPropostaRepository.deleteInvalidosOuExpirados();
    }

}

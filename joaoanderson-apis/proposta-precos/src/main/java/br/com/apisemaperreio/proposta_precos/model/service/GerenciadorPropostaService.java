package br.com.apisemaperreio.proposta_precos.model.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apisemaperreio.proposta_precos.model.domain.GerenciadorProposta;
import br.com.apisemaperreio.proposta_precos.model.dto.gerenciador_proposta.GerenciadorPropostaResponse;
import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaModeloRequest;
import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaModeloResponse;
import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaCadastroRequest;
import br.com.apisemaperreio.proposta_precos.model.repository.GerenciadorPropostaRepository;
import br.com.apisemaperreio.proposta_precos.model.use_cases.GerenciadorPropostaUseCases;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Service
public class GerenciadorPropostaService implements GerenciadorPropostaUseCases {

    private final GerenciadorPropostaRepository gerenciadorPropostaRepository;

    public GerenciadorPropostaService(GerenciadorPropostaRepository gerenciadorPropostaRepository) {
        this.gerenciadorPropostaRepository = gerenciadorPropostaRepository;
    }

    private void validarParametros(
            @NotBlank(message = "Token deve ser informado") String token) {
    }

    private void validarParametros(
            @NotNull(message = "Modelo de proposta não pode ser nulo") @Valid PropostaModeloRequest propostaModelo) {
    }

    private void validarParametros(
            @NotNull(message = "Modelo de proposta não pode ser nulo") @Valid PropostaCadastroRequest propostaRequest) {
    }

    @Transactional(readOnly = true)
    @Override
    public GerenciadorPropostaResponse obterPor(String token) {
        this.validarParametros(token);
        var gerenciadorProposta = gerenciadorPropostaRepository.findById(token)
                .orElseThrow(() -> new NoSuchElementException("Token não encontrado."));
        return new GerenciadorPropostaResponse(gerenciadorProposta);
    }

    @Transactional(readOnly = true)
    @Override
    public List<GerenciadorPropostaResponse> listar() {
        var gerenciadores = gerenciadorPropostaRepository.findAll();
        return gerenciadores.stream().map(GerenciadorPropostaResponse::new).toList();
    }

    @Transactional
    @Override
    public String gerarToken(PropostaModeloRequest propostaModelo) {
        this.validarParametros(propostaModelo);
        var gerenciadorProposta = new GerenciadorProposta(propostaModelo);
        return gerenciadorPropostaRepository.save(gerenciadorProposta).getToken();
    }

    @Transactional(readOnly = true)
    @Override
    public PropostaModeloResponse obterPropostaModelo(String token) {
        this.validarParametros(token);
        var gerenciadorProposta = gerenciadorPropostaRepository.findById(token)
                .orElseThrow(() -> new NoSuchElementException("Token não encontrado."));
        return new PropostaModeloResponse(gerenciadorProposta.getProposta());
    }

    @Transactional
    @Override
    public void cadastrarProposta(String token, PropostaCadastroRequest propostaRequest) {
        this.validarParametros(token);
        this.validarParametros(propostaRequest);
        var gerenciadorProposta = gerenciadorPropostaRepository.findById(token)
                .orElseThrow(() -> new NoSuchElementException("Token não encontrado."));
        gerenciadorProposta.prepararProposta(token, propostaRequest);
        gerenciadorPropostaRepository.save(gerenciadorProposta);
    }

    @Transactional
    @Override
    public void invalidarToken(String token) {
        this.validarParametros(token);
        var gerenciadorProposta = gerenciadorPropostaRepository.findById(token)
                .orElseThrow(() -> new NoSuchElementException("Token não encontrado."));
        gerenciadorProposta.invalidarToken();
        gerenciadorPropostaRepository.save(gerenciadorProposta);
    }

    @Transactional
    @Override
    public void removerInvalidosOuExpirados() {
        var removiveis = gerenciadorPropostaRepository.obterInvalidosOuExpirados();
        removiveis.forEach(GerenciadorProposta::desvincularProposta);
        gerenciadorPropostaRepository.deleteAllInBatch(removiveis);
    }

}

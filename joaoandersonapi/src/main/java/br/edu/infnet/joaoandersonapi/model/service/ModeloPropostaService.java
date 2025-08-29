package br.edu.infnet.joaoandersonapi.model.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.infnet.joaoandersonapi.model.domain.ModeloProposta;
import br.edu.infnet.joaoandersonapi.model.repository.ModeloPropostaRepository;
import br.edu.infnet.joaoandersonapi.model.use_cases.ModeloPropostaUseCases;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class ModeloPropostaService implements ModeloPropostaUseCases {

    private final ModeloPropostaRepository modeloPropostaRepository;

    public ModeloPropostaService(ModeloPropostaRepository modeloPropostaRepository) {
        this.modeloPropostaRepository = modeloPropostaRepository;
    }

    private void validarParametros(@NotNull(message = "Modelo de Proposta não pode ser nulo") @Valid ModeloProposta modeloProposta) {
    }

    private void validarParametros(
            @NotNull(message = "ID do modelo de proposta não pode ser nulo")
            @Positive(message = "ID do modelo de proposta deve ser maior que zero") Long id) {
    }

    private void validarId(Long id) {
        if (id != null)
            throw new IllegalArgumentException("O Id da Proposta não pode estar preenchido");
    }

    @Transactional
    @Override
    public ModeloProposta cadastrar(ModeloProposta modeloProposta) {
        this.validarParametros(modeloProposta);
        this.validarId(modeloProposta.getId());
        return modeloPropostaRepository.save(modeloProposta);
    }

    @Transactional(readOnly = true)
    @Override
    public ModeloProposta obterPor(Long id) {
        this.validarParametros(id);
        var modeloPropostaOpt = modeloPropostaRepository.findById(id);
        return modeloPropostaOpt
                .orElseThrow(() -> new NoSuchElementException("Não existe modelo de proposta com o ID " + id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ModeloProposta> listar() {
        return modeloPropostaRepository.findAll();
    }

    @Transactional
    @Override
    public ModeloProposta atualizar(ModeloProposta modeloPropostaNovo, Long id) {
        this.validarParametros(modeloPropostaNovo);
        this.validarId(modeloPropostaNovo.getId());
        var modeloPropostaAntigo = this.obterPor(id);
        modeloPropostaNovo.setId(modeloPropostaAntigo.getId());
        if (modeloPropostaAntigo.equals(modeloPropostaNovo))
            throw new IllegalArgumentException("Não é possível atualizar o modelo de proposta com os mesmos dados.");
        return modeloPropostaRepository.save(modeloPropostaNovo);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        modeloPropostaRepository.delete(this.obterPor(id));
    }

}

package br.edu.infnet.joaoandersonapi.model.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.edu.infnet.joaoandersonapi.model.domain.ModeloProposta;
import br.edu.infnet.joaoandersonapi.model.repository.ModeloPropostaRepository;
import br.edu.infnet.joaoandersonapi.model.use_cases.ModeloPropostaUseCases;

@Service
public class ModeloPropostaService implements ModeloPropostaUseCases {

    private final ModeloPropostaRepository modeloPropostaRepository;

    public ModeloPropostaService(ModeloPropostaRepository modeloPropostaRepository) {
        this.modeloPropostaRepository = modeloPropostaRepository;
    }

    private void validarParametros(ModeloProposta modeloProposta) {
        if (modeloProposta == null)
            throw new IllegalArgumentException("O Modelo de proposta não pode ser nulo");
        if (modeloProposta.getId() != null)
            throw new IllegalArgumentException("O Id do Modelo de proposta não pode estar preenchido");
    }

    private void validarParametros(Long id) {
        if (id == null || id < 1)
            throw new IllegalArgumentException("O Id não pode ser nulo ou menor que 1");
    }

    @Override
    public ModeloProposta cadastrar(ModeloProposta modeloProposta) {
        validarParametros(modeloProposta);
        var novoModeloProposta = modeloPropostaRepository.save(modeloProposta);
        return novoModeloProposta;
    }

    @Override
    public ModeloProposta obterPor(Long id) {
        validarParametros(id);
        var modeloPropostaOpt = modeloPropostaRepository.findById(id);
        return modeloPropostaOpt
                .orElseThrow(() -> new NoSuchElementException("Não existe modelo de proposta com o ID " + id));
    }

    @Override
    public List<ModeloProposta> listar() {
        return modeloPropostaRepository.findAll();
    }

    @Override
    public ModeloProposta atualizar(ModeloProposta modeloPropostaNovo, Long id) {
        validarParametros(modeloPropostaNovo);
        validarParametros(id);
        var modeloPropostaAntigo = this.obterPor(id);
        modeloPropostaNovo.setId(modeloPropostaAntigo.getId());
        if (modeloPropostaAntigo.equals(modeloPropostaNovo))
            throw new IllegalArgumentException("Não é possível atualizar o modelo de proposta com os mesmos dados.");
        return modeloPropostaRepository.save(modeloPropostaNovo);
    }

    @Override
    public void remover(Long id) {
        modeloPropostaRepository.delete(this.obterPor(id));
    }

}

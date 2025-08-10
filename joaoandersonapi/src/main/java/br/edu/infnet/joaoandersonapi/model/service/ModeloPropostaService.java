package br.edu.infnet.joaoandersonapi.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.edu.infnet.joaoandersonapi.model.domain.ModeloProposta;
import br.edu.infnet.joaoandersonapi.model.use_cases.ModeloPropostaUseCases;

@Service
public class ModeloPropostaService implements ModeloPropostaUseCases {

    private final Map<Long, ModeloProposta> modeloPropostaRepository = new ConcurrentHashMap<>();
    private final AtomicLong proximoId = new AtomicLong(1L);

    @Override
    public Long cadastrar(ModeloProposta modeloProposta) {
        modeloProposta.setId(proximoId.getAndIncrement());
        modeloPropostaRepository.put(modeloProposta.getId(), modeloProposta);
        return modeloProposta.getId();
    }

    @Override
    public ModeloProposta obterPor(Long id) {
        var modeloPropostaOpt = Optional.ofNullable(modeloPropostaRepository.get(id));
        return modeloPropostaOpt
                .orElseThrow(() -> new NoSuchElementException("Não existe modelo de proposta com o ID " + id));
    }

    @Override
    public List<ModeloProposta> listar() {
        return new ArrayList<>(modeloPropostaRepository.values());
    }

    @Override
    public void atualizar(ModeloProposta modeloPropostaNovo, Long id) {
        var modeloPropostaAntigo = this.obterPor(id);
        modeloPropostaNovo.setId(modeloPropostaAntigo.getId());
        if (modeloPropostaAntigo.equals(modeloPropostaNovo))
            throw new IllegalArgumentException("Não é possível atualizar o modelo de proposta com os mesmos dados.");
        modeloPropostaRepository.put(id, modeloPropostaNovo);
    }

    @Override
    public void remover(Long id) {
        this.obterPor(id);
        modeloPropostaRepository.remove(id);
    }

}

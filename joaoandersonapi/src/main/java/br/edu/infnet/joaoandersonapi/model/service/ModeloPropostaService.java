package br.edu.infnet.joaoandersonapi.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.edu.infnet.joaoandersonapi.model.domain.ModeloProposta;
import br.edu.infnet.joaoandersonapi.model.dtos.ModeloPropostaGet;
import br.edu.infnet.joaoandersonapi.model.dtos.ModeloPropostaPost;
import br.edu.infnet.joaoandersonapi.model.use_cases.ModeloPropostaUseCases;
import br.edu.infnet.joaoandersonapi.utils.mappers.ModeloPropostaMapper;

@Service
public class ModeloPropostaService implements ModeloPropostaUseCases {

    private final Map<Long, ModeloProposta> modeloPropostaRepository = new ConcurrentHashMap<>();
    private final AtomicLong proximoId = new AtomicLong(1L);

    private final ModeloPropostaMapper modeloPropostaMapper;

    public ModeloPropostaService(ModeloPropostaMapper modeloPropostaMapper) {
        this.modeloPropostaMapper = modeloPropostaMapper;
    }

    @Override
    public Long cadastrar(ModeloPropostaPost modeloPropostaPost) {
        var modeloProposta = modeloPropostaMapper.toModeloProposta(modeloPropostaPost);
        modeloProposta.setId(proximoId.getAndIncrement());
        modeloPropostaRepository.put(modeloProposta.getId(), modeloProposta);
        return modeloProposta.getId();
    }

    @Override
    public ModeloPropostaGet obterPor(Long id) {
        if (modeloPropostaRepository.containsKey(id))
            return modeloPropostaMapper.toModeloPropostaGet(modeloPropostaRepository.get(id));
        throw new NoSuchElementException("Não existe modelo de proposta com o ID " + id);
    }

    @Override
    public List<ModeloPropostaGet> listar() {
        return modeloPropostaMapper.toModelosPropostaGets(new ArrayList<>(modeloPropostaRepository.values()));
    }

    @Override
    public void atualizar(ModeloPropostaPost modeloPropostaPost, Long id) {
        var modeloPropostaAntigo = this.obterPor(id);
        var modeloPropostaNovo = modeloPropostaMapper.toModeloPropostaGet(modeloPropostaPost, modeloPropostaAntigo.id());
        if (modeloPropostaAntigo.equals(modeloPropostaNovo))
            throw new IllegalArgumentException("Não é possível atualizar o modelo de proposta com os mesmos dados.");
        modeloPropostaRepository.put(id, modeloPropostaMapper.toModeloProposta(modeloPropostaNovo));
    }

    @Override
    public void remover(Long id) {
        this.obterPor(id);
        modeloPropostaRepository.remove(id);
    }

}

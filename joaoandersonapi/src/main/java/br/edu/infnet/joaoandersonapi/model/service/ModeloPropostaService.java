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
import br.edu.infnet.joaoandersonapi.model.dtos.ModeloPropostaDto;
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
    public Long cadastrar(ModeloPropostaDto modeloPropostaDto) {
        var modeloProposta = modeloPropostaMapper.toModeloProposta(modeloPropostaDto);
        modeloProposta.setId(proximoId.getAndIncrement());
        modeloPropostaRepository.put(modeloProposta.getId(), modeloProposta);
        return modeloProposta.getId();
    }

    @Override
    public ModeloPropostaDto obterPor(Long id) {
        var modeloPropostaOpt = Optional.ofNullable(modeloPropostaRepository.get(id));
        if (modeloPropostaOpt.isPresent()) {
            return modeloPropostaMapper.toModeloPropostaDto(modeloPropostaOpt.get());
        }
        throw new NoSuchElementException("Não existe modelo de proposta com o ID " + id);
    }

    @Override
    public List<ModeloPropostaDto> listar() {
        return modeloPropostaMapper.toModelosPropostaDtos(new ArrayList<>(modeloPropostaRepository.values()));
    }

    @Override
    public void atualizar(ModeloPropostaDto modeloPropostaNovo, Long id) {
        var modeloPropostaAntigo = this.obterPor(id);
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

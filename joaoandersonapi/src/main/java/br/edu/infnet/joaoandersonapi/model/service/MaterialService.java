package br.edu.infnet.joaoandersonapi.model.service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.edu.infnet.joaoandersonapi.model.domain.Material;
import br.edu.infnet.joaoandersonapi.model.repository.MaterialRepository;
import br.edu.infnet.joaoandersonapi.model.use_cases.MaterialUseCases;
import br.edu.infnet.joaoandersonapi.model.use_cases.ModeloPropostaUseCases;

@Service
public class MaterialService implements MaterialUseCases {

    private final MaterialRepository materialRepository;
    private final ModeloPropostaUseCases modeloPropostaUseCases;

    public MaterialService(ModeloPropostaUseCases modeloPropostaUseCases, MaterialRepository materialRepository) {
        this.modeloPropostaUseCases = modeloPropostaUseCases;
        this.materialRepository = materialRepository;
    }

    private void validarParametros(Material material) {
        if (material == null)
            throw new IllegalArgumentException("O Material não pode ser nulo");
        if (material.getId() != null)
            throw new IllegalArgumentException("O Id do Material não pode estar preenchido");
    }

    private void validarParametros(Long id) {
        if (id == null || id < 1)
            throw new IllegalArgumentException("O Id não pode ser nulo ou menor que 1");
    }

    private Material vincularModeloProposta(Material material, Long idModeloProposta) {
        validarParametros(material);
        validarParametros(idModeloProposta);
        var modeloProposta = modeloPropostaUseCases.obterPor(idModeloProposta);
        var numeroItem = materialRepository.countByModeloPropostaId(idModeloProposta) + 1;
        material.setNumeroItem(numeroItem);
        material.setModeloProposta(modeloProposta);
        return material;
    }

    @Override
    public Material cadastrar(Material material, Long idModeloProposta) {
        vincularModeloProposta(material, idModeloProposta);
        return materialRepository.save(material);
    }

    @Override
    public Material obterPor(Long id) {
        validarParametros(id);
        return materialRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Material não encontrado"));
    }

    @Override
    public Material atualizar(Material materialAtualizado, Long idMaterial) {
        validarParametros(materialAtualizado);
        var material = obterPor(idMaterial);
        material.setDescricao(materialAtualizado.getDescricao());
        material.setUnidade(materialAtualizado.getUnidade());
        material.setQuantidade(materialAtualizado.getQuantidade());
        material.setPreco(materialAtualizado.getPreco());
        material.setAdquirido(materialAtualizado.isAdquirido());
        return materialRepository.save(material);
    }

    @Override
    public void remover(Long id) {
        materialRepository.delete(obterPor(id));
    }

    @Override
    public BigDecimal calcularPrecoTotal(Long idMaterial) {
        var material = obterPor(idMaterial);
        return material.calcularPrecoTotal();
    }

    @Override
    public void marcarAdquirido(Long idMaterial) {
        var material = obterPor(idMaterial);
        material.setAdquirido(true);
        materialRepository.save(material);
    }

}

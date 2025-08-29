package br.edu.infnet.joaoandersonapi.model.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.infnet.joaoandersonapi.model.domain.Material;
import br.edu.infnet.joaoandersonapi.model.repository.MaterialRepository;
import br.edu.infnet.joaoandersonapi.model.use_cases.MaterialUseCases;
import br.edu.infnet.joaoandersonapi.model.use_cases.ModeloPropostaUseCases;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class MaterialService implements MaterialUseCases {

    private final MaterialRepository materialRepository;
    private final ModeloPropostaUseCases modeloPropostaUseCases;

    public MaterialService(ModeloPropostaUseCases modeloPropostaUseCases, MaterialRepository materialRepository) {
        this.modeloPropostaUseCases = modeloPropostaUseCases;
        this.materialRepository = materialRepository;
    }

    private void validarParametros(@NotNull(message = "Material não pode ser nulo") @Valid Material material) {
    }

    private void validarParametros(
            @NotNull(message = "ID do Material não pode ser nulo")
            @Positive(message = "ID do Material deve ser maior que zero") Long id) {
    }

    private void validarId(Long id) {
        if (id != null)
            throw new IllegalArgumentException("O Id da Proposta não pode estar preenchido");
    }

    @Transactional
    @Override
    public Material cadastrar(Material material, Long idModeloProposta) {
        this.validarParametros(material);
        this.validarId(material.getId());
        var modeloProposta = modeloPropostaUseCases.obterPor(idModeloProposta);
        var numeroItem = materialRepository.countByModeloPropostaId(idModeloProposta) + 1;
        material.setNumeroItem(numeroItem);
        material.setModeloProposta(modeloProposta);
        return materialRepository.save(material);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Material> listar() {
        return materialRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Material obterPor(Long id) {
        this.validarParametros(id);
        return materialRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Material não encontrado"));
    }

    @Transactional
    @Override
    public Material atualizar(Material materialAtualizado, Long idMaterial) {
        this.validarParametros(materialAtualizado);
        this.validarId(materialAtualizado.getId());
        var material = this.obterPor(idMaterial);
        material.setDescricao(materialAtualizado.getDescricao());
        material.setUnidade(materialAtualizado.getUnidade());
        material.setQuantidade(materialAtualizado.getQuantidade());
        return materialRepository.save(material);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        materialRepository.delete(this.obterPor(id));
    }

    @Override
    public BigDecimal calcularPrecoTotal(Material material) {
        validarParametros(material);
        return material.calcularPrecoTotal();
    }

    @Override
    public void marcarAdquirido(Long idMaterial) {
        var material = this.obterPor(idMaterial);
        material.setAdquirido(true);
        materialRepository.save(material);
    }

}

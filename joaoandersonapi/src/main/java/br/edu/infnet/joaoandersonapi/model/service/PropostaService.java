package br.edu.infnet.joaoandersonapi.model.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.infnet.joaoandersonapi.model.domain.Proposta;
import br.edu.infnet.joaoandersonapi.model.repository.PropostaRepository;
import br.edu.infnet.joaoandersonapi.model.use_cases.PropostaUseCases;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class PropostaService implements PropostaUseCases {

    private final PropostaRepository propostaRepository;

    public PropostaService(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    private void validarParametros(@NotNull(message = "Proposta não pode ser nula") @Valid Proposta proposta) {
    }

    private void validarParametros(
            @NotNull(message = "ID da proposta não pode ser nulo")
            @Positive(message = "ID da proposta deve ser maior que zero") Long id) {
    }

    private void validarParametros(List<Long> ids) {
        if (ids == null || ids.isEmpty())
            throw new IllegalArgumentException("Lista de IDs não pode ser nula ou vazia");
        for (Long id : ids)
            this.validarParametros(id);
    }

    private void validarId(Long id) {
        if (id != null)
            throw new IllegalArgumentException("O Id da Proposta não pode estar preenchido");
    }

    @Override
    public Proposta cadastrar(Proposta proposta) {
        this.validarParametros(proposta);
        this.validarId(proposta.getId());
        proposta.setDataCriacao();
        proposta.setMateriais();
        return propostaRepository.save(proposta);
    }

    @Transactional(readOnly = true)
    @Override
    public Proposta obterPor(Long id) {
        this.validarParametros(id);
        var propostaOpt = propostaRepository.findById(id);
        return propostaOpt
                .orElseThrow(() -> new NoSuchElementException("Não existe proposta com o ID " + id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Proposta> listar() {
        return propostaRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Proposta> obterPorIds(List<Long> ids) {
        this.validarParametros(ids);
        var propostas = propostaRepository.findByIdIn(ids);
        if (propostas.size() != ids.size()) {
            ids.removeAll(propostas.stream().map(Proposta::getId).toList());
            throw new NoSuchElementException("Nenhuma proposta encontrada para os IDs informados: " + ids);
        }
        return propostas;
    }

    @Transactional
    @Override
    public void remover(Long id) {
        this.validarParametros(id);
        propostaRepository.delete(this.obterPor(id));
    }

    @Override
    public BigDecimal calcularPrecoGlobal(Proposta proposta) {
        this.validarParametros(proposta);
        return proposta.calcularPrecoGlobal();
    }

}

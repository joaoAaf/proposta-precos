package br.edu.infnet.joaoandersonapi.model.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import br.edu.infnet.joaoandersonapi.model.domain.Proposta;
import br.edu.infnet.joaoandersonapi.model.repository.PropostaRepository;
import br.edu.infnet.joaoandersonapi.model.use_cases.PropostaUseCases;

@Service
public class PropostaService implements PropostaUseCases {

    private final PropostaRepository propostaRepository;
    
    public PropostaService(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    private void validarParametros(Proposta proposta) {
        if (proposta == null)
            throw new IllegalArgumentException("A proposta não pode ser nula");
        if (proposta.getId() != null)
            throw new IllegalArgumentException("O Id da proposta não pode estar preenchido");
    }

    private void validarParametros(Long id) {
        if (id == null || id < 1)
            throw new IllegalArgumentException("O Id não pode ser nulo ou menor que 1");
    }

    @Override
    public Proposta cadastrar(Proposta proposta) {
        validarParametros(proposta);
        proposta.setDataCriacao();
        return propostaRepository.save(proposta);
    }

    @Override
    public Proposta obterPor(Long id) {
        validarParametros(id);
        var propostaOpt = propostaRepository.findById(id);
        return propostaOpt
                .orElseThrow(() -> new NoSuchElementException("Não existe proposta com o ID " + id));
    }

    @Override
    public List<Proposta> listar() {
        return propostaRepository.findAll();
    }

    @Override
    public Proposta atualizar(Proposta proposta, Long id) {
        validarParametros(proposta);
        validarParametros(id);
        var propostaAntiga = this.obterPor(id);
        proposta.setId(propostaAntiga.getId());
        if (propostaAntiga.equals(proposta))
            throw new IllegalArgumentException("Não é possível atualizar a proposta com os mesmos dados.");
        return propostaRepository.save(proposta);
    }

    @Override
    public void remover(Long id) {
        propostaRepository.delete(this.obterPor(id));
    }

    @Override
    public BigDecimal calcularPrecoGlobal(Proposta proposta) {
        return proposta.calcularPrecoGlobal(proposta);
    }

}

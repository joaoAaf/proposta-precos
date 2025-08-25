package br.edu.infnet.joaoandersonapi.model.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    private void validarParametros(Long id) {
        if (id == null || id < 1)
            throw new IllegalArgumentException("O Id não pode ser nulo ou menor que 1");
    }

    @Override
    public Proposta cadastrar(Proposta proposta) {
        proposta.setDataCriacao();
        proposta.setMateriais();
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
    public Proposta atualizar(Proposta novaProposta, Long id) {
        //TODO: Remover metodo desnecessario
        return null;
    }

    @Override
    public void remover(Long id) {
        propostaRepository.delete(this.obterPor(id));
    }

    @Override
    public BigDecimal calcularPrecoGlobal(Proposta proposta) {
        Optional.ofNullable(proposta).orElseThrow(() -> new IllegalArgumentException("A proposta não pode ser nula"));
        return proposta.calcularPrecoGlobal();
    }

}

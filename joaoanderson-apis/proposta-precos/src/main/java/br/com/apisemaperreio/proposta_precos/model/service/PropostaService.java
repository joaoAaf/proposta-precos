package br.com.apisemaperreio.proposta_precos.model.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.apisemaperreio.proposta_precos.model.domain.Proposta;
import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaCadastroResponse;
import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaCalculoRequest;
import br.com.apisemaperreio.proposta_precos.model.repository.PropostaRepository;
import br.com.apisemaperreio.proposta_precos.model.use_cases.PropostaUseCases;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
public class PropostaService implements PropostaUseCases {

    private final PropostaRepository propostaRepository;

    public PropostaService(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    private void validarParametros(@NotNull(message = "Proposta não pode ser nula") @Valid PropostaCalculoRequest proposta) {
    }

    private void validarParametros(
            @NotNull(message = "ID da proposta não pode ser nulo") @Positive(message = "ID da proposta deve ser maior que zero") Long id) {
    }

    // private void validarParametros(List<Long> ids) {
    // if (ids == null || ids.isEmpty())
    // throw new IllegalArgumentException("Lista de IDs não pode ser nula ou
    // vazia");
    // for (Long id : ids)
    // this.validarParametros(id);
    // }

    @Transactional(readOnly = true)
    @Override
    public PropostaCadastroResponse obterPor(Long id) {
        this.validarParametros(id);
        var proposta = propostaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Não existe proposta com o ID " + id));
        return new PropostaCadastroResponse(proposta);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PropostaCadastroResponse> listar() {
        return propostaRepository.findAll().stream().map(PropostaCadastroResponse::new).toList();
    }

    // @Transactional(readOnly = true)
    // @Override
    // public List<Proposta> obterPorIds(List<Long> ids) {
    // this.validarParametros(ids);
    // var propostas = propostaRepository.findByIdIn(ids);
    // if (propostas.size() != ids.size()) {
    // ids.removeAll(propostas.stream().map(Proposta::getId).toList());
    // throw new NoSuchElementException("Nenhuma proposta encontrada para os IDs
    // informados: " + ids);
    // }
    // return propostas;
    // }

    @Transactional
    @Override
    public void remover(Long id) {
        this.validarParametros(id);
        var proposta = propostaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Não existe proposta com o ID " + id));
        propostaRepository.delete(proposta);
    }

    @Override
    public BigDecimal calcularPrecoGlobal(PropostaCalculoRequest propostaCalculo) {
        this.validarParametros(propostaCalculo);
        var proposta = new Proposta(propostaCalculo);
        return proposta.calcularPrecoGlobal();
    }

}

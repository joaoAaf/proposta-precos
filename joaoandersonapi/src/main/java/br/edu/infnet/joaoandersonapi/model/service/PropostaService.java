package br.edu.infnet.joaoandersonapi.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.joaoandersonapi.model.domain.Proposta;
import br.edu.infnet.joaoandersonapi.model.use_cases.PropostaUseCases;

@Service
public class PropostaService implements PropostaUseCases {

    @Override
    public Proposta cadastrar(Proposta t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cadastrar'");
    }

    @Override
    public Proposta obterPor(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obterPor'");
    }

    @Override
    public List<Proposta> listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    @Override
    public Proposta atualizar(Proposta t, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void remover(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }

}

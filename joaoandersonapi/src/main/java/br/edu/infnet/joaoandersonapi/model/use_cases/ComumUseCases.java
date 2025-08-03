package br.edu.infnet.joaoandersonapi.model.use_cases;

import java.util.List;

public interface ComumUseCases<T, ID> {

    Long cadastrar(T t);

    T obterPor(ID id);

    List<T> listar();

    void atualizar(T t, ID id);

    void remover(ID id);
    
}

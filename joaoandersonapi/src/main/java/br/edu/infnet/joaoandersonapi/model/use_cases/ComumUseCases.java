package br.edu.infnet.joaoandersonapi.model.use_cases;

import java.util.List;

public interface ComumUseCases<T, ID> {

    T cadastrar(T t);

    T obterPor(ID id);

    List<T> listar();

    T atualizar(T t, ID id);

    void remover(ID id);
    
}

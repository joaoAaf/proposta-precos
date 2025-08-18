package br.edu.infnet.joaoandersonapi.model.use_cases.common;

import java.util.List;

public interface UseCases<T, ID> {

    T cadastrar(T t);

    T obterPor(ID id);

    List<T> listar();

    T atualizar(T t, ID id);

    void remover(ID id);
    
}

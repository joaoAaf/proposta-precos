package br.edu.infnet.joaoandersonapi.model.use_cases.common;

public interface AuxUseCases<T, ID1, ID2> {

    T cadastrar(T t, ID2 id2);

    T obterPor(ID1 id1);

    T atualizar(T t, ID1 id);

    void remover(ID1 id);
    
}

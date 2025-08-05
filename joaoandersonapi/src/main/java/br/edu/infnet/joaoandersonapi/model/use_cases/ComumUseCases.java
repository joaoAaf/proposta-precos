package br.edu.infnet.joaoandersonapi.model.use_cases;

import java.util.List;

public interface ComumUseCases<T1, T2, ID> {

    Long cadastrar(T2 t);

    T1 obterPor(ID id);

    List<T1> listar();

    void atualizar(T2 t, ID id);

    void remover(ID id);
    
}

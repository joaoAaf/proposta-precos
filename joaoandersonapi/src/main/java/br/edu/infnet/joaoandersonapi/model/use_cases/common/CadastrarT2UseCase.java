package br.edu.infnet.joaoandersonapi.model.use_cases.common;

public interface CadastrarT2UseCase<T, ID> {

    T cadastrar(T t, ID id);

}

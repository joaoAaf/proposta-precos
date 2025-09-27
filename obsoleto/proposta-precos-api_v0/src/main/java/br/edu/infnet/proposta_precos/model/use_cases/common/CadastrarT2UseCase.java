package br.edu.infnet.proposta_precos.model.use_cases.common;

public interface CadastrarT2UseCase<T, ID> {

    T cadastrar(T t, ID id);

}

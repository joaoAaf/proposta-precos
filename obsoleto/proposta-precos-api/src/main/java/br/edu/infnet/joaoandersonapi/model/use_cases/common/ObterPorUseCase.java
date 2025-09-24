package br.edu.infnet.joaoandersonapi.model.use_cases.common;

public interface ObterPorUseCase<T, ID> {

    T obterPor(ID id);

}

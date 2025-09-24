package br.edu.infnet.joaoandersonapi.model.use_cases.common;

public interface AtualizarUseCase<T, ID> {

    T atualizar(T t, ID id);

}

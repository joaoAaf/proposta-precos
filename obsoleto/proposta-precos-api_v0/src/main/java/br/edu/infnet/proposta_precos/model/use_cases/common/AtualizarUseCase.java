package br.edu.infnet.proposta_precos.model.use_cases.common;

public interface AtualizarUseCase<T, ID> {

    T atualizar(T t, ID id);

}

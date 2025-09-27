package br.edu.infnet.proposta_precos.model.use_cases.common;

public interface ObterPorUseCase<T, ID> {

    T obterPor(ID id);

}

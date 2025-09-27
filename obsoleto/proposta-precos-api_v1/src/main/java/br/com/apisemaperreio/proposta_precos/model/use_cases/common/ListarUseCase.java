package br.com.apisemaperreio.proposta_precos.model.use_cases.common;

import java.util.List;

public interface ListarUseCase <T> {

    List<T> listar();

}

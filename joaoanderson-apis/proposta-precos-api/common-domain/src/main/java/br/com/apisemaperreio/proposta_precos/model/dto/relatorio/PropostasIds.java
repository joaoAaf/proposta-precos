package br.com.apisemaperreio.proposta_precos.model.dto.relatorio;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PropostasIds(
        @NotEmpty(message = "Lista de IDs não pode ser nula ou vazia")
        List<
            @NotNull(message = "ID da proposta não pode ser nulo")
            @Positive(message = "ID da proposta deve ser maior que zero")
        Long> ids) {

}

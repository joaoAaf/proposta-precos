package br.com.apisemaperreio.proposta_precos.model.dto.material;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MaterialPrecoRequest(
                @NotNull(message = "O ID do material deve ser informado")
                @Positive(message = "O ID do material deve ser maior que zero.")
                Long id,

                @NotNull(message = "O preço deve ser informado")
                @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero.")
                BigDecimal preco) {

}

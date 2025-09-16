package br.com.apisemaperreio.proposta_precos.model.dto.material;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MaterialRequest(
        @NotBlank(message = "A descrição deve ser informada")
        @Size(min = 3, max = 255, message = "A descrição deve ter entre 3 e 255 caracteres")
        String descricao,

        @NotBlank(message = "A unidade deve ser informada")
        @Size(min = 1, max = 8, message = "A unidade deve ter entre 2 e 8 caracteres")
        String unidade,

        @NotNull(message = "A quantidade deve ser informada")
        @DecimalMin(value = "0.01", message = "A quantidade deve ser maior que zero.")
        BigDecimal quantidade) {

}

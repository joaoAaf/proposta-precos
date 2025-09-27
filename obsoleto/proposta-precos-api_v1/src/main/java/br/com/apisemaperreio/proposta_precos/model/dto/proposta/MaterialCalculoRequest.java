package br.com.apisemaperreio.proposta_precos.model.dto.proposta;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record MaterialCalculoRequest(
        @NotNull(message = "A quantidade do material deve ser preenchida")
        @DecimalMin(value = "0.01", message = "A quantidade do material deve ser maior que zero")
        BigDecimal quantidade,
        
        @NotNull(message = "O preço do material deve ser preenchido")
        @DecimalMin(value = "0.01", message = "O preço do material deve ser maior que zero")
        BigDecimal preco) {

}

package br.com.apisemaperreio.proposta_precos.model.dto.proposta;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PropostaCalculoRequest(
    @NotEmpty(message = "A lista de preços dos materiais não pode ser nula ou vazia.")
    List<@Valid MaterialCalculoRequest> materiais,
    
    @NotNull(message = "O desconto deve ser informado")
    @DecimalMin(value = "0.00", message = "O desconto deve ser maior ou igual a zero.")
    BigDecimal desconto) {

}

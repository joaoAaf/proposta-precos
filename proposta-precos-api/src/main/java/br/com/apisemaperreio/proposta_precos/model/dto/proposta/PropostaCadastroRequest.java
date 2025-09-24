package br.com.apisemaperreio.proposta_precos.model.dto.proposta;

import java.math.BigDecimal;
import java.util.List;

import br.com.apisemaperreio.proposta_precos.model.dto.fornecedor.FornecedorRequest;
import br.com.apisemaperreio.proposta_precos.model.dto.material.MaterialPrecoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PropostaCadastroRequest(
        @Valid
        @NotNull(message = "O fornecedor deve ser informado")
        FornecedorRequest fornecedor,

        @NotEmpty(message = "A lista de materiais não pode ser nula ou vazia")
        List<@Valid MaterialPrecoRequest> materiais,
        
        @NotNull(message = "O desconto deve ser informado")
        @DecimalMin(value = "0.00", message = "O desconto deve ser maior ou igual a zero.")
        @DecimalMax(value = "100.00", message = "O desconto deve ser menor ou igual a 100.")
        BigDecimal desconto,
        
        @Size(max = 255, message = "As observações do fornecedor não podem exceder 255 caracteres.")
        String observacoesFornecedor) {

}

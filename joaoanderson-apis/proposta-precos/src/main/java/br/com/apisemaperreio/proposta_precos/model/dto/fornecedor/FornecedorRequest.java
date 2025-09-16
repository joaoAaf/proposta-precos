package br.com.apisemaperreio.proposta_precos.model.dto.fornecedor;

import br.com.apisemaperreio.proposta_precos.model.dto.instituicao.InstituicaoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record FornecedorRequest(
        @Valid
        @NotNull(message = "A instituição deve ser informada")
        InstituicaoRequest instituicao,

        @NotBlank(message = "O email deve ser informado")
        @Email(message = "O email deve ser válido")
        String email,

        @NotBlank(message = "O telefone deve ser informado")
        @Pattern(regexp = "^(\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4})$", message = "O telefone deve estar no formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX")
        String telefone,

        @NotBlank(message = "O nome deve ser informado")
        @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
        String responsavel) {

}

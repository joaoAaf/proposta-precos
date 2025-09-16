package br.com.apisemaperreio.proposta_precos.model.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoRequest(
        @NotBlank(message = "O logradouro deve ser informado")
        @Size(min = 3, max = 255, message = "O logradouro deve ter entre 3 e 255 caracteres")
        String logradouro,

        @NotBlank(message = "O número deve ser informado")
        @Size(min = 1, max = 20, message = "O número deve ter entre 1 e 20 caracteres")
        String numero,

        @NotBlank(message = "O bairro deve ser informado")
        @Size(min = 3, max = 50, message = "O bairro deve ter entre 3 e 50 caracteres")
        String bairro,

        @NotBlank(message = "A cidade deve ser informada")
        @Size(min = 3, max = 50, message = "A cidade deve ter entre 3 e 50 caracteres")
        String cidade,

        @NotBlank(message = "A UF deve ser informada")
        @Size(min = 2, max = 2, message = "A UF deve ter 2 caracteres")
        String uf,

        @NotBlank(message = "O CEP deve ser informado")
        @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "CEP inválido. Use o formato XXXXX-XXX.")
        String cep) {

}

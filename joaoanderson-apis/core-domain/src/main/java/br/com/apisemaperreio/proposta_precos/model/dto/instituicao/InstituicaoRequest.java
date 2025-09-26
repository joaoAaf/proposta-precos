package br.com.apisemaperreio.proposta_precos.model.dto.instituicao;

import br.com.apisemaperreio.proposta_precos.model.dto.endereco.EnderecoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record InstituicaoRequest(
        @NotBlank(message = "O CNPJ deve ser informado")
        @Pattern(regexp = "^(?:\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}|\\d{14})$", message = "CNPJ inválido. Use o formato XX.XXX.XXX/XXXX-XX")
        String cnpj,

        @NotBlank(message = "O nome deve ser informado")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,

        @Valid
        @NotNull(message = "O endereço deve ser informado")
        EnderecoRequest endereco) {

}

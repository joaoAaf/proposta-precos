package br.edu.infnet.joaoandersonapi.model.domain;

import java.time.LocalDate;
import java.util.List;

import br.edu.infnet.joaoandersonapi.dtos.PropostaRelatorio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RelatorioComparacaoPropostas(
    @NotNull LocalDate dataEmissao,
    @NotEmpty @Valid List<PropostaRelatorio> propostas,
    @NotNull @Positive Double media,
    @NotNull @Positive Double mediana,
    @NotNull @Positive List<PropostaRelatorio> propostasVantajosas,
    @NotBlank String conclusao) {

}

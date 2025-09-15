package br.edu.infnet.joaoandersonrelatoriosapi.model.domain;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record Proposta(
        @NotNull @Positive Long id,
        @NotNull @Positive BigDecimal precoGlobal) {

}
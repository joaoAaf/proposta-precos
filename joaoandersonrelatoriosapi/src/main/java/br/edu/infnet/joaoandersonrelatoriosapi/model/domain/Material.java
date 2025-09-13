package br.edu.infnet.joaoandersonrelatoriosapi.model.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record Material(
        @NotNull @Positive Integer numeroItem,
        @NotNull @Positive Double quantidade,
        @NotNull @Positive Double precoUnitario,
        @NotNull @Positive Double precoTotal) {

}

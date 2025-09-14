package br.edu.infnet.joaoandersonrelatoriosapi.model.domain;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record Proposta(
        @NotNull @Positive Long id,
        @NotEmpty @Valid List<Material> materiais,
        @NotNull @Positive Double desconto,
        @NotNull @Positive BigDecimal precoGlobal) {

}
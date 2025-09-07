package br.edu.infnet.joaoandersonrelatoriosapi.model.domain;

import java.math.BigDecimal;
import java.util.List;

public record Proposta(Long id, List<Material> materiais, Double desconto, BigDecimal precoGlobal) {

}
package br.edu.infnet.joaoandersonapi.model.dtos;

import java.math.BigDecimal;

public record MaterialDto(Integer numeroItem, String descricao, String unidade, BigDecimal quantidade, BigDecimal preco, boolean adquirido) {

}

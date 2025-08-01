package br.edu.infnet.joaoandersonapi.model.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Proposta {

    private Long id;
    private Requisitante requisitante;
    private Fornecedor fornecedor;
    private List<Material> materiais = new ArrayList<>();
    private BigDecimal desconto;
    private Endereco enderecoEntrega;
    private String observacoesRequisitante;
    private String observacoesFornecedor;

    public Proposta(Requisitante requisitante, Fornecedor fornecedor, List<Material> materiais, BigDecimal desconto,
            Endereco enderecoEntrega) {
        this.requisitante = requisitante;
        this.fornecedor = fornecedor;
        this.materiais = materiais;
        this.desconto = desconto;
        this.enderecoEntrega = enderecoEntrega;
    }

    public Proposta(ModeloProposta modeloProposta) {
        this.requisitante = modeloProposta.getRequisitante();
        this.materiais.addAll(modeloProposta.getMateriais());
        this.observacoesRequisitante = Optional.ofNullable(modeloProposta.getObservacoes()).orElse("");
        this.enderecoEntrega = modeloProposta.getRequisitante().getDadosBasicos().getEndereco();
        this.desconto = BigDecimal.ZERO;
    }

    public BigDecimal calcularPrecoGlobal() {
        BigDecimal precoGlobal = materiais.stream()
                .map(Material::calcularPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (desconto == null || desconto.compareTo(BigDecimal.ZERO) <= 0
                || desconto.compareTo(BigDecimal.valueOf(100)) > 0)
            return precoGlobal;
        var valorDesconto = precoGlobal.multiply(desconto.divide(BigDecimal.valueOf(100)));
        return precoGlobal.subtract(valorDesconto);
    }

    public Long getId() {
        return id;
    }

    public Requisitante getRequisitante() {
        return requisitante;
    }

    public void setRequisitante(Requisitante requisitante) {
        this.requisitante = requisitante;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Material> getMateriais() {
        return materiais;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public String getObservacoesRequisitante() {
        return observacoesRequisitante;
    }

    public void setObservacoesRequisitante(String observacoesRequisitante) {
        this.observacoesRequisitante = observacoesRequisitante;
    }

    public String getObservacoesFornecedor() {
        return observacoesFornecedor;
    }

    public void setObservacoesFornecedor(String observacoesFornecedor) {
        this.observacoesFornecedor = observacoesFornecedor;
    }

    @Override
    public String toString() {
        return "Proposta [id=" + id + ", requisitante=" + requisitante + ", fornecedor=" + fornecedor + "]";
    }

}

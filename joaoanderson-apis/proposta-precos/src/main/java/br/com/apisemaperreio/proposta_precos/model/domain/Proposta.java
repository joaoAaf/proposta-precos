package br.com.apisemaperreio.proposta_precos.model.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaModeloRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataCriacao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requisitante_id")
    private Requisitante requisitante;

    @ManyToOne(cascade = CascadeType.ALL)
    private Fornecedor fornecedor;

    @OneToMany(mappedBy = "proposta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Material> materiais = new ArrayList<>();

    @Column(nullable = false, columnDefinition = "decimal(5,2) default 0.00")
    private BigDecimal desconto;

    private Endereco enderecoEntrega;
    private String observacoesRequisitante;
    private String observacoesFornecedor;

    public Proposta(PropostaModeloRequest propostaModelo) {
        this.requisitante = new Requisitante(propostaModelo.requisitante());
        this.materiais = propostaModelo.materiais().stream().map(Material::new).collect(Collectors.toList());
        this.observacoesRequisitante = Optional.ofNullable(propostaModelo.observacoesRequisitante()).orElse(null);
        this.enderecoEntrega = this.requisitante.getInstituicao().getEndereco();
    }

    public Proposta() {
    }

    public BigDecimal calcularPrecoGlobal() {
        if (materiais == null || materiais.isEmpty())
            return BigDecimal.ZERO;
        var precoGlobal = materiais.stream()
                .map(Material::calcularPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (desconto == null || desconto.compareTo(BigDecimal.ZERO) <= 0
                || desconto.compareTo(BigDecimal.valueOf(100)) > 0)
            return precoGlobal;
        var valorDesconto = precoGlobal.multiply(desconto.divide(BigDecimal.valueOf(100)));
        return precoGlobal.subtract(valorDesconto);
    }

    // public void validarMaterialProposta(Material material) {
    //     if (material == null)
    //         throw new IllegalArgumentException("O Material não pode ser nulo");
    //     if (material.getPreco() == null || material.getPreco().compareTo(BigDecimal.ZERO) <= 0)
    //         throw new IllegalArgumentException("O preço do Material deve ser maior que zero");
    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao() {
        this.dataCriacao = LocalDate.now();
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
        result = prime * result + ((requisitante == null) ? 0 : requisitante.hashCode());
        result = prime * result + ((fornecedor == null) ? 0 : fornecedor.hashCode());
        result = prime * result + ((materiais == null) ? 0 : materiais.hashCode());
        result = prime * result + ((desconto == null) ? 0 : desconto.hashCode());
        result = prime * result + ((enderecoEntrega == null) ? 0 : enderecoEntrega.hashCode());
        result = prime * result + ((observacoesRequisitante == null) ? 0 : observacoesRequisitante.hashCode());
        result = prime * result + ((observacoesFornecedor == null) ? 0 : observacoesFornecedor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Proposta other = (Proposta) obj;
        if (dataCriacao == null) {
            if (other.dataCriacao != null)
                return false;
        } else if (!dataCriacao.equals(other.dataCriacao))
            return false;
        if (requisitante == null) {
            if (other.requisitante != null)
                return false;
        } else if (!requisitante.equals(other.requisitante))
            return false;
        if (fornecedor == null) {
            if (other.fornecedor != null)
                return false;
        } else if (!fornecedor.equals(other.fornecedor))
            return false;
        if (materiais == null || materiais.isEmpty()) {
            if (other.materiais != null && !other.materiais.isEmpty())
                return false;
        } else {
            if (materiais.size() != other.materiais.size())
                return false;
            for (Material material : materiais) {
                if (!other.getMateriais().stream().anyMatch(m -> m.equals(material))) {
                    return false;
                }
            }
        }
        if (desconto == null) {
            if (other.desconto != null)
                return false;
        } else if (!desconto.equals(other.desconto))
            return false;
        if (enderecoEntrega == null) {
            if (other.enderecoEntrega != null)
                return false;
        } else if (!enderecoEntrega.equals(other.enderecoEntrega))
            return false;
        if (observacoesRequisitante == null) {
            if (other.observacoesRequisitante != null)
                return false;
        } else if (!observacoesRequisitante.equals(other.observacoesRequisitante))
            return false;
        if (observacoesFornecedor == null) {
            if (other.observacoesFornecedor != null)
                return false;
        } else if (!observacoesFornecedor.equals(other.observacoesFornecedor))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Proposta [id=" + id + ", dataCriacao=" + dataCriacao + ", requisitante=" + requisitante
                + ", fornecedor=" + fornecedor + "]";
    }

}

package br.edu.infnet.joaoandersonapi.model.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numeroItem;
    private String descricao;
    private String unidade;
    private BigDecimal quantidade;
    private BigDecimal preco;
    private boolean adquirido;

    @ManyToOne
    @JoinColumn(name = "modelo_proposta_id")
    @JsonIgnore
    private ModeloProposta modeloProposta;

    @ManyToOne
    @JoinColumn(name = "proposta_id")
    @JsonIgnore
    private Proposta proposta;

    public Material(Integer numeroItem, String descricao, String unidade, BigDecimal quantidade) {
        this.numeroItem = numeroItem;
        this.descricao = descricao;
        this.unidade = unidade;
        this.quantidade = quantidade;
        this.preco = BigDecimal.ZERO;
    }

    public BigDecimal calcularPrecoTotal() {
        return preco.multiply(quantidade);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroItem() {
        return numeroItem;
    }

    public void setNumeroItem(Integer numeroItem) {
        this.numeroItem = numeroItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public boolean isAdquirido() {
        return adquirido;
    }

    public void setAdquirido(boolean adquirido) {
        this.adquirido = adquirido;
    }

    public void setModeloProposta(ModeloProposta modeloProposta) {
        this.modeloProposta = modeloProposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
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
        Material other = (Material) obj;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (unidade == null) {
            if (other.unidade != null)
                return false;
        } else if (!unidade.equals(other.unidade))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Material [numeroItem=" + numeroItem + ", descricao=" + descricao + ", unidade=" + unidade
                + ", quantidade=" + quantidade + ", preco=" + preco + ", adquirido=" + adquirido + "]";
    }

}

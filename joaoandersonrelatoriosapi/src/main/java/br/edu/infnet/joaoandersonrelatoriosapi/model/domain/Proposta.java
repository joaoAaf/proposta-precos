package br.edu.infnet.joaoandersonrelatoriosapi.model.domain;

import java.math.BigDecimal;
import java.util.List;

public class Proposta {

    private Long id;
    private List<Material> materiais;
    private BigDecimal desconto;

    public Proposta(Long id, List<Material> materiais, BigDecimal desconto) {
        this.id = id;
        this.materiais = materiais;
        this.desconto = desconto;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((materiais == null) ? 0 : materiais.hashCode());
        result = prime * result + ((desconto == null) ? 0 : desconto.hashCode());
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
        return true;
    }

    @Override
    public String toString() {
        return "Proposta [id=" + id + "]";
    }

}

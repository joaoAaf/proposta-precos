package br.edu.infnet.joaoandersonapi.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class ModeloProposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "requisitante_id")
    private Requisitante requisitante;

    @OneToMany(mappedBy = "modeloProposta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Material> materiais = new ArrayList<>();

    private String observacoes;

    public ModeloProposta() {
    }

    public ModeloProposta(Requisitante requisitante) {
        this.requisitante = requisitante;
    }

    public ModeloProposta(Proposta proposta) {
        this.requisitante = proposta.getRequisitante();
        this.materiais = proposta.getMateriais().stream().map(Material::new).collect(Collectors.toList());
        this.observacoes = proposta.getObservacoesRequisitante();
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

    public Requisitante getRequisitante() {
        return requisitante;
    }

    public void setRequisitante(Requisitante requisitante) {
        this.requisitante = requisitante;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((requisitante == null) ? 0 : requisitante.hashCode());
        result = prime * result + ((materiais == null) ? 0 : materiais.hashCode());
        for (Material material : materiais) {
            result = prime * result + material.hashCode();
        }
        result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
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
        ModeloProposta other = (ModeloProposta) obj;
        if (requisitante == null) {
            if (other.requisitante != null)
                return false;
        } else if (!requisitante.equals(other.requisitante))
            return false;
        if (materiais == null) {
            if (other.materiais != null)
                return false;
        } else if (!materiais.equals(other.materiais)) {
            if (materiais.size() != other.materiais.size())
                return false;
            for (Material material : materiais) {
                Material otherMaterial = other.getMateriais().stream()
                        .filter(m -> m.getId().equals(material.getId()))
                        .findFirst()
                        .orElse(null);
                if (otherMaterial == null)
                    return false;
                if (!material.equals(otherMaterial))
                    return false;
            }
        }
        if (observacoes == null) {
            if (other.observacoes != null)
                return false;
        } else if (!observacoes.equals(other.observacoes))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ModeloProposta [id=" + id + ", requisitante=" + requisitante + "]";
    }

}

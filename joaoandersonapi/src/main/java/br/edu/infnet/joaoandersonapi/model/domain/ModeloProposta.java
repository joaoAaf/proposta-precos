package br.edu.infnet.joaoandersonapi.model.domain;

import java.util.ArrayList;
import java.util.List;

public class ModeloProposta {

    private Long id;
    private Requisitante requisitante;
    private List<Material> materiais = new ArrayList<>();
    private String observacoes;

    public ModeloProposta(Requisitante requisitante, List<Material> materiais) {
        this.requisitante = requisitante;
        this.materiais = materiais;
    }

    public Long getId() {
        return id;
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((requisitante == null) ? 0 : requisitante.hashCode());
        result = prime * result + ((materiais == null) ? 0 : materiais.hashCode());
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (requisitante == null) {
            if (other.requisitante != null)
                return false;
        } else if (!requisitante.equals(other.requisitante))
            return false;
        if (materiais == null) {
            if (other.materiais != null)
                return false;
        } else if (!materiais.equals(other.materiais))
            return false;
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

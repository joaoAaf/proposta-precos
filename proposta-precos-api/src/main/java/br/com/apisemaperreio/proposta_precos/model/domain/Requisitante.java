package br.com.apisemaperreio.proposta_precos.model.domain;

import jakarta.persistence.Entity;

@Entity
public class Requisitante extends Responsavel {

    private String setor;

    public Requisitante(Instituicao instituicao, String email, String telefone, String nome, String setor) {
        super(instituicao, email, telefone, nome);
        this.setor = setor;
    }
    
    public Requisitante() {
        super();
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((setor == null) ? 0 : setor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Requisitante other = (Requisitante) obj;
        if (setor == null) {
            if (other.setor != null)
                return false;
        } else if (!setor.equals(other.setor))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Requisitante [toString()=" + super.toString() + ", setor=" + setor + "]";
    }

}

package br.edu.infnet.joaoandersonapi.model.domain;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Requisitante extends Responsavel {

    @NotBlank(message = "O setor deve ser informado")
    @Size(min = 2, max = 50, message = "O setor deve ter entre 2 e 50 caracteres")
    private String setor;

    public Requisitante(Instituicao instituicao, String email, String telefone, String responsavel, String setor) {
        super(instituicao, email, telefone, responsavel);
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

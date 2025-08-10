package br.edu.infnet.joaoandersonapi.model.domain;

public class Contato {

    private String email1;
    private String email2;
    private String telefone1;
    private String telefone2;

    public Contato() {
    }

    public Contato(String email1, String telefone1) {
        this.email1 = email1;
        this.telefone1 = telefone1;
    }

    public Contato(String email1, String email2, String telefone1, String telefone2) {
        this.email1 = email1;
        this.email2 = email2;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email1 == null) ? 0 : email1.hashCode());
        result = prime * result + ((email2 == null) ? 0 : email2.hashCode());
        result = prime * result + ((telefone1 == null) ? 0 : telefone1.hashCode());
        result = prime * result + ((telefone2 == null) ? 0 : telefone2.hashCode());
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
        Contato other = (Contato) obj;
        if (email1 == null) {
            if (other.email1 != null)
                return false;
        } else if (!email1.equals(other.email1))
            return false;
        if (email2 == null) {
            if (other.email2 != null)
                return false;
        } else if (!email2.equals(other.email2))
            return false;
        if (telefone1 == null) {
            if (other.telefone1 != null)
                return false;
        } else if (!telefone1.equals(other.telefone1))
            return false;
        if (telefone2 == null) {
            if (other.telefone2 != null)
                return false;
        } else if (!telefone2.equals(other.telefone2))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Contato [email1=" + email1 + ", telefone1=" + telefone1 + "]";
    }

}

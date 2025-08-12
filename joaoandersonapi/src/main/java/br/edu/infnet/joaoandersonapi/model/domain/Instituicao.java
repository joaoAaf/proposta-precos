package br.edu.infnet.joaoandersonapi.model.domain;

public abstract class Instituicao {

    private Long id;
    private DadosBasicos dadosBasicos;
    private String email;
    private String telefone;
    private String responsavel;

    public Instituicao(DadosBasicos dadosBasicos, String email, String telefone, String responsavel) {
        this.dadosBasicos = dadosBasicos;
        this.email = email;
        this.telefone = telefone;
        this.responsavel = responsavel;
    }

    public Long getId() {
        return id;
    }

    public DadosBasicos getDadosBasicos() {
        return dadosBasicos;
    }

    public void setDadosBasicos(DadosBasicos dadosBasicos) {
        this.dadosBasicos = dadosBasicos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((dadosBasicos == null) ? 0 : dadosBasicos.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
        result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
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
        Instituicao other = (Instituicao) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (dadosBasicos == null) {
            if (other.dadosBasicos != null)
                return false;
        } else if (!dadosBasicos.equals(other.dadosBasicos))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (telefone == null) {
            if (other.telefone != null)
                return false;
        } else if (!telefone.equals(other.telefone))
            return false;
        if (responsavel == null) {
            if (other.responsavel != null)
                return false;
        } else if (!responsavel.equals(other.responsavel))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Instituicao [id=" + id + ", dadosBasicos=" + dadosBasicos + ", email=" + email + ", telefone="
                + telefone + ", responsavel=" + responsavel + "]";
    }

}

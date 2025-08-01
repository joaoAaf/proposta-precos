package br.edu.infnet.joaoandersonapi.model.domain;

public class Requisitante {

    private DadosBasicos dadosBasicos;
    private Contato contato;
    private String responsavel;
    private String setor;

    public Requisitante(DadosBasicos dadosBasicos, Contato contato, String responsavel, String setor) {
        this.dadosBasicos = dadosBasicos;
        this.contato = contato;
        this.responsavel = responsavel;
        this.setor = setor;
    }

    public DadosBasicos getDadosBasicos() {
        return dadosBasicos;
    }

    public void setDadosBasicos(DadosBasicos dadosBasicos) {
        this.dadosBasicos = dadosBasicos;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
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
        int result = 1;
        result = prime * result + ((dadosBasicos == null) ? 0 : dadosBasicos.hashCode());
        result = prime * result + ((contato == null) ? 0 : contato.hashCode());
        result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
        result = prime * result + ((setor == null) ? 0 : setor.hashCode());
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
        Requisitante other = (Requisitante) obj;
        if (dadosBasicos == null) {
            if (other.dadosBasicos != null)
                return false;
        } else if (!dadosBasicos.equals(other.dadosBasicos))
            return false;
        if (contato == null) {
            if (other.contato != null)
                return false;
        } else if (!contato.equals(other.contato))
            return false;
        if (responsavel == null) {
            if (other.responsavel != null)
                return false;
        } else if (!responsavel.equals(other.responsavel))
            return false;
        if (setor == null) {
            if (other.setor != null)
                return false;
        } else if (!setor.equals(other.setor))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Requisitante [dadosBasicos=" + dadosBasicos + ", contato=" + contato + ", responsavel=" + responsavel
                + ", setor=" + setor + "]";
    }

}

package br.edu.infnet.joaoandersonapi.model.domain;

import br.edu.infnet.joaoandersonapi.model.domain.exceptions.ErroFormatacaoException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Instituicao {

    @Id
    @NotBlank(message = "O CNPJ deve ser informado")
    @Pattern(regexp = "^(?:\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}|\\d{14})$", message = "CNPJ inválido. Use o formato XX.XXX.XXX/XXXX-XX")
    private String cnpj;

    @NotBlank(message = "O nome deve ser informado")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    @Valid
    @NotNull(message = "O endereço deve ser informado")
    private Endereco endereco;

    public Instituicao(String cnpj, String nome, Endereco endereco) {
        this.cnpj = this.desformatarCnpj(cnpj);
        this.nome = nome;
        this.endereco = endereco;
    }

    public Instituicao() {
    }

    public String desformatarCnpj(String cnpj) {
        return cnpj.replaceAll("[^\\d]", "");
    }

    public String formatarCnpj(String cnpj) {
        if (cnpj.length() != 14)
            throw new ErroFormatacaoException("Problema ao formatar o CNPJ");
        return cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/"
                + cnpj.substring(8, 12) + "-" + cnpj.substring(12);
    }

    public String getCnpj() {
        return this.formatarCnpj(cnpj);
    }

    public void setCnpj(String cnpj) {
        this.cnpj = this.desformatarCnpj(cnpj);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
        result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
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
        if (cnpj == null) {
            if (other.cnpj != null)
                return false;
        } else if (!cnpj.equals(other.cnpj))
            return false;
        if (endereco == null) {
            if (other.endereco != null)
                return false;
        } else if (!endereco.equals(other.endereco))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Instituicao [cnpj=" + this.getCnpj() + ", nome=" + nome + ", endereco=" + endereco + "]";
    }

}

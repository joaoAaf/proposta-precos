package br.edu.infnet.joaoandersonapi.model.domain;

import br.edu.infnet.joaoandersonapi.model.domain.exceptions.ErroFormatacaoException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1, message = "O Id deve ser maior que 0")
    private Long id;

    @NotBlank(message = "O logradouro deve ser informado")
    @Size(min = 3, max = 255, message = "O logradouro deve ter entre 3 e 255 caracteres")
    private String logradouro;

    @NotBlank(message = "O número deve ser informado")
    @Size(min = 1, max = 20, message = "O número deve ter entre 1 e 20 caracteres")
    private String numero;

    @NotBlank(message = "O bairro deve ser informado")
    @Size(min = 3, max = 50, message = "O bairro deve ter entre 3 e 50 caracteres")
    private String bairro;

    @NotBlank(message = "A cidade deve ser informada")
    @Size(min = 3, max = 50, message = "A cidade deve ter entre 3 e 50 caracteres")
    private String cidade;

    @NotBlank(message = "A UF deve ser informada")
    @Size(min = 2, max = 2, message = "A UF deve ter 2 caracteres")
    private String uf;

    @NotBlank(message = "O CEP deve ser informado")
    @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "CEP inválido. Use o formato XXXXX-XXX.")
    private String cep;

    public Endereco(String logradouro, String numero, String bairro, String cidade, String uf, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = this.desformatarCep(cep);
    }

    public String desformatarCep(String cep) {
        return cep.replaceAll("[^\\d]", "");
    }

    public String formatarCep(String cep) {
        if (cep.length() != 8)
            throw new ErroFormatacaoException("Problema ao formatar o CEP");
        return cep.substring(0, 5) + "-" + cep.substring(5);
    }

    public Endereco() {
    }

    public Long getId() {
        return id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return this.formatarCep(cep);
    }

    public void setCep(String cep) {
        this.cep = this.desformatarCep(cep);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
        result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
        result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
        result = prime * result + ((uf == null) ? 0 : uf.hashCode());
        result = prime * result + ((cep == null) ? 0 : cep.hashCode());
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
        Endereco other = (Endereco) obj;
        if (logradouro == null) {
            if (other.logradouro != null)
                return false;
        } else if (!logradouro.equals(other.logradouro))
            return false;
        if (numero == null) {
            if (other.numero != null)
                return false;
        } else if (!numero.equals(other.numero))
            return false;
        if (bairro == null) {
            if (other.bairro != null)
                return false;
        } else if (!bairro.equals(other.bairro))
            return false;
        if (cidade == null) {
            if (other.cidade != null)
                return false;
        } else if (!cidade.equals(other.cidade))
            return false;
        if (uf == null) {
            if (other.uf != null)
                return false;
        } else if (!uf.equals(other.uf))
            return false;
        if (cep == null) {
            if (other.cep != null)
                return false;
        } else if (!cep.equals(other.cep))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Endereco [logradouro=" + logradouro + ", numero=" + numero + ", bairro=" + bairro + ", cidade=" + cidade
                + ", uf=" + uf + ", cep=" + cep + "]";
    }

}

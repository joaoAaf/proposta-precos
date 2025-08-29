package br.edu.infnet.joaoandersonapi.model.domain;

import br.edu.infnet.joaoandersonapi.model.domain.exceptions.ErroFormatacaoException;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public abstract class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1, message = "O ID deve ser maior que 0")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instituicao_id")
    @Valid
    @NotNull(message = "A instituição deve ser informada")
    private Instituicao instituicao;

    @NotBlank(message = "O email deve ser informado")
    @Email(message = "O email deve ser válido")
    private String email;

    @NotBlank(message = "O telefone deve ser informado")
    @Pattern(regexp = "^(\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4})$", message = "O telefone deve estar no formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX")
    private String telefone;

    @NotBlank(message = "O nome deve ser informado")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String nome;

    public Responsavel(Instituicao instituicao, String email, String telefone, String nome) {
        this.instituicao = instituicao;
        this.email = email;
        this.telefone = this.desformatarTelefone(telefone);
        this.nome = nome;
    }

    public Responsavel() {
    }

    public String desformatarTelefone(String telefone) {
        return telefone.replaceAll("[^\\d]", "");
    }

    public String formatarTelefone(String telefone) {
        if (telefone.length() == 10)
            return "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 6) + "-" + telefone.substring(6);
        if (telefone.length() == 11)
            return "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 7) + "-" + telefone.substring(7);
        throw new ErroFormatacaoException("Problema ao formatar o telefone");
    }

    public Long getId() {
        return id;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return this.formatarTelefone(telefone);
    }

    public void setTelefone(String telefone) {
        this.telefone = this.desformatarTelefone(telefone);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((instituicao == null) ? 0 : instituicao.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
        Responsavel other = (Responsavel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (instituicao == null) {
            if (other.instituicao != null)
                return false;
        } else if (!instituicao.equals(other.instituicao))
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
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Responsavel [id=" + id + ", instituicao=" + instituicao + ", email=" + email + ", telefone="
                + this.getTelefone() + ", nome=" + nome + "]";
    }

}

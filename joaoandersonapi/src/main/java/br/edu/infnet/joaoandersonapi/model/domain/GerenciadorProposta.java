package br.edu.infnet.joaoandersonapi.model.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import br.edu.infnet.joaoandersonapi.model.domain.exceptions.PropostaInvalidaException;
import br.edu.infnet.joaoandersonapi.model.domain.exceptions.TokenInvalidoException;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class GerenciadorProposta {

    @Id
    private String token;

    @ManyToOne
    @JoinColumn(name = "modelo_proposta_id")
    private ModeloProposta modeloProposta;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataExpiracao;
    private boolean valido = true;

    public GerenciadorProposta(ModeloProposta modeloProposta) {
        this.token = UUID.randomUUID().toString();
        this.modeloProposta = modeloProposta;
        this.dataCriacao = LocalDateTime.now();
        this.dataExpiracao = calcularDataExpiracao(this.dataCriacao, 5);
    }

    public GerenciadorProposta() {
    }

    private LocalDateTime calcularDataExpiracao(LocalDateTime dataCriacao, int diasUteis) {
        var expiracao = dataCriacao;
        int diasAdicionados = 0;
        while (diasAdicionados < diasUteis) {
            expiracao = expiracao.plusDays(1);
            if (expiracao.getDayOfWeek().getValue() >= 1 && expiracao.getDayOfWeek().getValue() <= 5)
                diasAdicionados++;
        }
        return expiracao;
    }

    public void validarProposta(String token, Proposta proposta) {
        verificarToken(token);
        var modeloProposta = new ModeloProposta(proposta);
        if (!this.modeloProposta.equals(modeloProposta))
            throw new PropostaInvalidaException("Proposta inválida.");
        proposta.getMateriais().forEach(m -> {
            m.setModeloProposta(this.modeloProposta);
            m.setProposta(proposta);
        });
    }

    public Proposta criarProposta(String token) {
        verificarToken(token);
        return new Proposta(this.modeloProposta);
    }

    public void verificarToken(String token) {
        if (!valido || LocalDateTime.now().isAfter(this.dataExpiracao) || !this.token.equals(token))
            throw new TokenInvalidoException("Token inválido ou expirado.");
    }

    public void invalidarToken() {
        this.valido = false;
    }

    public String getToken() {
        return token;
    }

    public ModeloProposta getModeloProposta() {
        return modeloProposta;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataExpiracao() {
        return dataExpiracao;
    }

    public boolean isValido() {
        return valido;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((token == null) ? 0 : token.hashCode());
        result = prime * result + ((modeloProposta == null) ? 0 : modeloProposta.hashCode());
        result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
        result = prime * result + ((dataExpiracao == null) ? 0 : dataExpiracao.hashCode());
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
        GerenciadorProposta other = (GerenciadorProposta) obj;
        if (token == null) {
            if (other.token != null)
                return false;
        } else if (!token.equals(other.token))
            return false;
        if (modeloProposta == null) {
            if (other.modeloProposta != null)
                return false;
        } else if (!modeloProposta.equals(other.modeloProposta))
            return false;
        if (dataCriacao == null) {
            if (other.dataCriacao != null)
                return false;
        } else if (!dataCriacao.equals(other.dataCriacao))
            return false;
        if (dataExpiracao == null) {
            if (other.dataExpiracao != null)
                return false;
        } else if (!dataExpiracao.equals(other.dataExpiracao))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GerenciadorProposta [token=" + token + ", modeloProposta=" + modeloProposta + ", dataCriacao="
                + dataCriacao + ", dataExpiracao=" + dataExpiracao + ", valido=" + valido + "]";
    }

}

package br.com.apisemaperreio.proposta_precos.model.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.apisemaperreio.proposta_precos.model.domain.exceptions.PropostaInvalidaException;
import br.com.apisemaperreio.proposta_precos.model.domain.exceptions.TokenInvalidoException;
import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaModeloRequest;
import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaCadastroRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class GerenciadorProposta {

    @Id
    private String token;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proposta_id")
    private Proposta proposta;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataExpiracao;
    private boolean valido = true;

    public GerenciadorProposta(PropostaModeloRequest propostaModelo) {
        this.token = UUID.randomUUID().toString();
        this.proposta = new Proposta(propostaModelo);
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

    public void validarProposta(PropostaCadastroRequest propostaRequest) {
        if (propostaRequest.materiais().size() != this.proposta.getMateriais().size())
            throw new PropostaInvalidaException("Proposta inválida: número de materiais diferente do esperado.");
        var idsUnicos = propostaRequest.materiais().stream().map(m -> m.id()).distinct().count();
        if (idsUnicos != propostaRequest.materiais().size())
            throw new PropostaInvalidaException("Proposta inválida: IDs de materiais duplicados.");
    }

    public void prepararProposta(String token, PropostaCadastroRequest propostaRequest) {
        this.verificarToken(token);
        this.validarProposta(propostaRequest);
        Map<Long, BigDecimal> precosMateriais = propostaRequest.materiais().stream()
                .collect(Collectors.toMap(m -> m.id(), m -> m.preco()));
        for (var material : this.proposta.getMateriais()) {
            if (!precosMateriais.containsKey(material.getId()))
                throw new PropostaInvalidaException(
                        "Proposta inválida: material com ID " + material.getId() + " não encontrado.");
            material.setPreco(precosMateriais.get(material.getId()));
        }
        this.proposta.setDataCriacao();
        this.proposta.setFornecedor(new Fornecedor(propostaRequest.fornecedor()));
        this.proposta.setDesconto(propostaRequest.desconto());
        this.proposta.setObservacoesFornecedor(propostaRequest.observacoesFornecedor());
        this.valido = false;
    }

    public void verificarToken(String token) {
        if (!valido || LocalDateTime.now().isAfter(this.dataExpiracao) || !this.token.equals(token))
            throw new TokenInvalidoException("Token inválido ou expirado.");
    }

    public void invalidarToken() {
        this.valido = false;
    }

    public void desvincularProposta() {
        this.proposta = null;
    }

    public String getToken() {
        return token;
    }

    public Proposta getProposta() {
        return proposta;
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
        result = prime * result + ((proposta == null) ? 0 : proposta.hashCode());
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
        if (proposta == null) {
            if (other.proposta != null)
                return false;
        } else if (!proposta.equals(other.proposta))
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
        return "GerenciadorProposta [token=" + token + ", modeloProposta=" + proposta + ", dataCriacao="
                + dataCriacao + ", dataExpiracao=" + dataExpiracao + ", valido=" + valido + "]";
    }

}

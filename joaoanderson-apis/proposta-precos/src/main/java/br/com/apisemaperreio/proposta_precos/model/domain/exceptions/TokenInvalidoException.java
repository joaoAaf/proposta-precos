package br.com.apisemaperreio.proposta_precos.model.domain.exceptions;

public class TokenInvalidoException extends RuntimeException {

    public TokenInvalidoException(String message) {
        super(message);
    }

}

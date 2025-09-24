package br.edu.infnet.joaoandersonapi.model.domain.exceptions;

public class TokenInvalidoException extends RuntimeException {

    public TokenInvalidoException(String message) {
        super(message);
    }

}

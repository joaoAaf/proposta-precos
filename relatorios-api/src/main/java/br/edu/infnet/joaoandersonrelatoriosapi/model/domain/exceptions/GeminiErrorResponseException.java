package br.edu.infnet.joaoandersonrelatoriosapi.model.domain.exceptions;

public class GeminiErrorResponseException extends RuntimeException {

    public GeminiErrorResponseException(String message) {
        super(message);
    }

}

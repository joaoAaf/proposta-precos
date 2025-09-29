package br.com.apisemaperreio.relatorios_api.model.domain.exceptions;

public class GeminiErrorResponseException extends RuntimeException {

    public GeminiErrorResponseException(String message) {
        super(message);
    }

}

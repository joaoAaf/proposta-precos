package br.com.apisemaperreio.relatorios_api.model.domain.exceptions;

public class GeminiEmptyApiKeyException extends RuntimeException {

    public GeminiEmptyApiKeyException(String message) {
        super(message);
    }

}

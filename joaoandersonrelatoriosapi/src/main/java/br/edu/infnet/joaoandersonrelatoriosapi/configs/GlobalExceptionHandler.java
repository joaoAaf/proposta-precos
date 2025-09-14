package br.edu.infnet.joaoandersonrelatoriosapi.configs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.infnet.joaoandersonrelatoriosapi.model.domain.DesvioPadraoPermitidoExtrapoladoException;
import br.edu.infnet.joaoandersonrelatoriosapi.model.domain.exceptions.GeminiEmptyApiKeyException;
import br.edu.infnet.joaoandersonrelatoriosapi.model.domain.exceptions.GeminiErrorResponseException;
import feign.FeignException;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(error -> {
            errors.put("Data/Hora", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            errors.put("Status", HttpStatus.BAD_REQUEST.toString());
            errors.put("Mensagem", error.getMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Map<String, String>> handleFeignException(FeignException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Data/Hora", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        error.put("Status", HttpStatus.SERVICE_UNAVAILABLE.toString());
        error.put("Mensagem", "Erro na comunicação com serviço externo");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }

    @ExceptionHandler(GeminiErrorResponseException.class)
    public ResponseEntity<Map<String, String>> handleGeneralExceptions(GeminiErrorResponseException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Data/Hora", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        error.put("Status", HttpStatus.SERVICE_UNAVAILABLE.toString());
        error.put("Mensagem", ex.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }

    @ExceptionHandler(GeminiEmptyApiKeyException.class)
    public ResponseEntity<Map<String, String>> handleGeneralExceptions(GeminiEmptyApiKeyException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Data/Hora", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        error.put("Status", HttpStatus.BAD_REQUEST.toString());
        error.put("Mensagem", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(DesvioPadraoPermitidoExtrapoladoException.class)
    public ResponseEntity<Map<String, String>> handleGeneralExceptions(DesvioPadraoPermitidoExtrapoladoException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Data/Hora", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        error.put("Status", HttpStatus.BAD_REQUEST.toString());
        error.put("Mensagem", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleGeneralExceptions(IllegalArgumentException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Data/Hora", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        error.put("Status", HttpStatus.BAD_REQUEST.toString());
        error.put("Mensagem", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralExceptions(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Data/Hora", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        error.put("Status", HttpStatus.SERVICE_UNAVAILABLE.toString());
        error.put("Mensagem", "Serviço indisponível no momento. Tente novamente mais tarde.");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }

}

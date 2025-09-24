package br.edu.infnet.joaoandersonapi.configs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.edu.infnet.joaoandersonapi.model.domain.exceptions.ErroFormatacaoException;
import br.edu.infnet.joaoandersonapi.model.domain.exceptions.PropostaInvalidaException;
import br.edu.infnet.joaoandersonapi.model.domain.exceptions.TokenInvalidoException;
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

    // @ExceptionHandler(FeignException.BadRequest.class)
    // public ResponseEntity<Map<String, String>>
    // handleFeignException(FeignException.BadRequest ex) {
    // Map<String, String> error = new HashMap<>();
    // var status = HttpStatus.resolve(ex.status());
    // if (status != null)
    // status = HttpStatus.SERVICE_UNAVAILABLE;
    // error.put("Data/Hora",
    // LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd
    // HH:mm:ss")));
    // error.put("Status", status.toString());
    // error.put("Mensagem", ex.getMessage().);
    // return ResponseEntity.status(status).body(error);
    // }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Map<String, String>> handleFeignException(FeignException ex) {
        Map<String, String> error = new HashMap<>();
        var status = HttpStatus.resolve(ex.status());
        var msg = ex.contentUTF8().isBlank() ? ""
                : ex.contentUTF8().split("Mensagem\\\":\\\"")[1].replaceAll("\\\"}", "").trim();
        if (ex.status() == 401 || ex.status() == 403 || ex.status() == 429 || ex.status() >= 500 || status == null) {
            status = HttpStatus.SERVICE_UNAVAILABLE;
            msg = "Erro na comunicação com serviço externo";
        }
        error.put("Data/Hora", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        error.put("Status", status.toString());
        error.put("Mensagem", msg);
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ErroFormatacaoException.class)
    public ResponseEntity<Map<String, String>> handleGeneralExceptions(ErroFormatacaoException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Data/Hora", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        error.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        error.put("Mensagem", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }

    @ExceptionHandler(PropostaInvalidaException.class)
    public ResponseEntity<Map<String, String>> handleGeneralExceptions(PropostaInvalidaException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Data/Hora", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        error.put("Status", HttpStatus.BAD_REQUEST.toString());
        error.put("Mensagem", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(TokenInvalidoException.class)
    public ResponseEntity<Map<String, String>> handleGeneralExceptions(TokenInvalidoException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Data/Hora", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        error.put("Status", HttpStatus.UNAUTHORIZED.toString());
        error.put("Mensagem", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleGeneralExceptions(IllegalArgumentException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Data/Hora", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        error.put("Status", HttpStatus.BAD_REQUEST.toString());
        error.put("Mensagem", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> handleGeneralExceptions(NoSuchElementException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Data/Hora", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        error.put("Status", HttpStatus.NOT_FOUND.toString());
        error.put("Mensagem", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleGeneralExceptions(DataIntegrityViolationException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("Data/Hora", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        error.put("Status", HttpStatus.BAD_REQUEST.toString());
        error.put("Mensagem", ex.getRootCause().getMessage());
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

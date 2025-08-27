package br.edu.infnet.joaoandersonapi.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.joaoandersonapi.model.domain.Proposta;
import br.edu.infnet.joaoandersonapi.model.use_cases.PropostaUseCases;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/proposta")
public class PropostaController {

    private final PropostaUseCases propostaUseCases;

    public PropostaController(PropostaUseCases propostaUseCases) {
        this.propostaUseCases = propostaUseCases;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterPropostaPorId(
            @PathVariable @NotNull(message = "ID da proposta não pode ser nulo")
            @Positive(message = "ID da proposta deve ser maior que zero") Long id) {
        try {
            var proposta = propostaUseCases.obterPor(id);
            return ResponseEntity.ok().body(proposta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao obter proposta");
        }
    }

    @GetMapping
    public ResponseEntity<?> listarPropostas() {
        try {
            var listaPropostas = propostaUseCases.listar();
            if (listaPropostas == null || listaPropostas.isEmpty())
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok().body(listaPropostas);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao listar propostas");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerPropostaPorId(
            @PathVariable @NotNull(message = "ID da proposta não pode ser nulo")
            @Positive(message = "ID da proposta deve ser maior que zero") Long id) {
        try {
            propostaUseCases.remover(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao remover proposta");
        }
    }

    @GetMapping("/preco-global")
    public ResponseEntity<?> calcularPrecoGlobalProposta(@RequestBody @Valid @NotNull(message = "Proposta não pode ser nula") Proposta proposta) {
        try {
            var precoTotal = propostaUseCases.calcularPrecoGlobal(proposta);
            return ResponseEntity.ok().body(precoTotal);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao calcular preço global da proposta");
        }
    }

}

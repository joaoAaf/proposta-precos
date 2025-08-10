package br.edu.infnet.joaoandersonapi.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.joaoandersonapi.model.domain.ModeloProposta;
import br.edu.infnet.joaoandersonapi.model.use_cases.ModeloPropostaUseCases;

@RestController
@RequestMapping("/api/modelo-proposta")
public class ModeloPropostaController {

    private final ModeloPropostaUseCases modeloPropostaUseCases;

    public ModeloPropostaController(ModeloPropostaUseCases modeloPropostaUseCases) {
        this.modeloPropostaUseCases = modeloPropostaUseCases;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarModeloProposta(@RequestBody ModeloProposta modeloProposta) {
        try {
            var idModeloProposta = modeloPropostaUseCases.cadastrar(modeloProposta);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Modelo de proposta cadastrado com sucesso! Id: " + idModeloProposta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao cadastrar modelo de proposta");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterModeloPropostaPorId(@PathVariable Long id) {
        try {
            var modeloProposta = modeloPropostaUseCases.obterPor(id);
            return ResponseEntity.ok().body(modeloProposta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao obter modelo de proposta");
        }
    }

    @GetMapping
    public ResponseEntity<?> listarModelosProposta() {
        try {
            var listaModeloProposta = modeloPropostaUseCases.listar();
            return ResponseEntity.ok().body(listaModeloProposta);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao listar modelos de proposta");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarModeloPropostaPorId(@RequestBody ModeloProposta modeloProposta,
            @PathVariable Long id) {
        try {
            modeloPropostaUseCases.atualizar(modeloProposta, id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao atualizar modelo de proposta");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerModeloPropostaPorId(@PathVariable Long id) {
        try {
            modeloPropostaUseCases.remover(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao remover modelo de proposta");
        }
    }

}

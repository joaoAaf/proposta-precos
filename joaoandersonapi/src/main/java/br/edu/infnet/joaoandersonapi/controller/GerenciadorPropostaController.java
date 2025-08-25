package br.edu.infnet.joaoandersonapi.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.joaoandersonapi.model.domain.Proposta;
import br.edu.infnet.joaoandersonapi.model.domain.exceptions.PropostaInvalidaException;
import br.edu.infnet.joaoandersonapi.model.domain.exceptions.TokenInvalidoException;
import br.edu.infnet.joaoandersonapi.model.use_cases.GerenciadorPropostaUseCases;

@RestController
@RequestMapping("/api/gerenciador-proposta")
public class GerenciadorPropostaController {

    private final GerenciadorPropostaUseCases gerenciadorPropostaUseCases;

    public GerenciadorPropostaController(GerenciadorPropostaUseCases gerenciadorPropostaUseCases) {
        this.gerenciadorPropostaUseCases = gerenciadorPropostaUseCases;
    }

    @PostMapping("/modelo-proposta/{idModeloProposta}/gerar-token")
    public ResponseEntity<?> gerarToken(@PathVariable Long idModeloProposta) {
        try {
            var token = gerenciadorPropostaUseCases.gerarToken(idModeloProposta);
            return ResponseEntity.status(HttpStatus.CREATED).body(token);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao gerar token");
        }
    }

    @GetMapping("/{token}")
    public ResponseEntity<?> obterGerenciadorProposta(@PathVariable String token) {
        try {
            var gerenciadorProposta = gerenciadorPropostaUseCases.obterPor(token);
            return ResponseEntity.ok().body(gerenciadorProposta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao obter gerenciador de proposta");
        }
    }

    @GetMapping
    public ResponseEntity<?> listarGerenciadoresProposta() {
        try {
            var listaGerenciadorProposta = gerenciadorPropostaUseCases.listar();
            if (listaGerenciadorProposta == null || listaGerenciadorProposta.isEmpty())
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok().body(listaGerenciadorProposta);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao listar gerenciadores de proposta");
        }
    }

    @GetMapping("/{token}/proposta/criar")
    public ResponseEntity<?> criarProposta(@PathVariable String token) {
        try {
            var proposta = gerenciadorPropostaUseCases.criarProposta(token);
            return ResponseEntity.ok().body(proposta);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (TokenInvalidoException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao criar proposta");
        }
    }

    @PostMapping("/{token}/proposta/cadastrar")
    public ResponseEntity<?> cadastrarProposta(@PathVariable String token, @RequestBody Proposta proposta) {
        try {
            gerenciadorPropostaUseCases.cadastrarProposta(token, proposta);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (PropostaInvalidaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (TokenInvalidoException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.internalServerError().body("Erro ao cadastrar proposta");
        }
    }

    @PatchMapping("/{token}/invalidar")
    public ResponseEntity<?> invalidarToken(@PathVariable String token) {
        try {
            gerenciadorPropostaUseCases.invalidarToken(token);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao invalidar token");
        }
    }

    @DeleteMapping("/limpar")
    public ResponseEntity<?> limparGerenciadoresProposta() {
        try {
            gerenciadorPropostaUseCases.removerInvalidosOuExpirados();
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body("Erro ao limpar gerenciadores de proposta");
        }
    }

}

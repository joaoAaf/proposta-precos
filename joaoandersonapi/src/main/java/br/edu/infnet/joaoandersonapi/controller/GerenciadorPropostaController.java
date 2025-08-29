package br.edu.infnet.joaoandersonapi.controller;

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
        var token = gerenciadorPropostaUseCases.gerarToken(idModeloProposta);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }

    @GetMapping("/{token}")
    public ResponseEntity<?> obterGerenciadorProposta(@PathVariable String token) {
        var gerenciadorProposta = gerenciadorPropostaUseCases.obterPor(token);
        return ResponseEntity.ok().body(gerenciadorProposta);
    }

    @GetMapping
    public ResponseEntity<?> listarGerenciadoresProposta() {
        var listaGerenciadorProposta = gerenciadorPropostaUseCases.listar();
        if (listaGerenciadorProposta == null || listaGerenciadorProposta.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(listaGerenciadorProposta);
    }

    @GetMapping("/{token}/proposta/criar")
    public ResponseEntity<?> criarProposta(@PathVariable String token) {
        var proposta = gerenciadorPropostaUseCases.criarProposta(token);
        return ResponseEntity.ok().body(proposta);
    }

    @PostMapping("/{token}/proposta/cadastrar")
    public ResponseEntity<?> cadastrarProposta(@PathVariable String token, @RequestBody Proposta proposta) {
        gerenciadorPropostaUseCases.cadastrarProposta(token, proposta);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{token}/invalidar")
    public ResponseEntity<?> invalidarToken(@PathVariable String token) {
        gerenciadorPropostaUseCases.invalidarToken(token);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/limpar")
    public ResponseEntity<?> limparGerenciadoresProposta() {
        gerenciadorPropostaUseCases.removerInvalidosOuExpirados();
        return ResponseEntity.noContent().build();
    }

}

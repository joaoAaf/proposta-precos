package br.com.apisemaperreio.proposta_precos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaModeloRequest;
import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaRequest;
import br.com.apisemaperreio.proposta_precos.model.use_cases.GerenciadorPropostaUseCases;

@RestController
@RequestMapping("/api/gerenciador-proposta")
public class GerenciadorPropostaController {

    private final GerenciadorPropostaUseCases gerenciadorPropostaUseCases;

    public GerenciadorPropostaController(GerenciadorPropostaUseCases gerenciadorPropostaUseCases) {
        this.gerenciadorPropostaUseCases = gerenciadorPropostaUseCases;
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

    @PostMapping("/gerar-token")
    public ResponseEntity<?> gerarToken(@RequestBody PropostaModeloRequest propostaModelo) {
        var token = gerenciadorPropostaUseCases.gerarToken(propostaModelo);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }

    @GetMapping("/{token}/proposta/modelo")
    public ResponseEntity<?> obterPropostaModelo(@PathVariable String token) {
        var proposta = gerenciadorPropostaUseCases.obterPropostaModelo(token);
        return ResponseEntity.ok().body(proposta);
    }

    @PutMapping("/{token}/proposta/cadastrar")
    public ResponseEntity<?> cadastrarProposta(@PathVariable String token, @RequestBody PropostaRequest propostaRequest) {
        gerenciadorPropostaUseCases.cadastrarProposta(token, propostaRequest);
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

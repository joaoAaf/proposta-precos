package br.com.apisemaperreio.proposta_precos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apisemaperreio.proposta_precos.model.dto.proposta.PropostaCalculoRequest;
import br.com.apisemaperreio.proposta_precos.model.use_cases.PropostaUseCases;

@RestController
@RequestMapping("/api/proposta")
public class PropostaController {

    private final PropostaUseCases propostaUseCases;

    public PropostaController(PropostaUseCases propostaUseCases) {
        this.propostaUseCases = propostaUseCases;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterPropostaPorId(@PathVariable Long id) {
        var proposta = propostaUseCases.obterPor(id);
        return ResponseEntity.ok().body(proposta);
    }

    @GetMapping
    public ResponseEntity<?> listarPropostas() {
        var listaPropostas = propostaUseCases.listar();
        if (listaPropostas == null || listaPropostas.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(listaPropostas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerPropostaPorId(@PathVariable Long id) {
        propostaUseCases.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/preco-global")
    public ResponseEntity<?> calcularPrecoGlobalProposta(@RequestBody PropostaCalculoRequest proposta) {
        var precoTotal = propostaUseCases.calcularPrecoGlobal(proposta);
        return ResponseEntity.ok().body(precoTotal);
    }

}

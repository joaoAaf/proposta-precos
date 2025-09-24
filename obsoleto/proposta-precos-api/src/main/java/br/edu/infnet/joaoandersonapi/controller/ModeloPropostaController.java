package br.edu.infnet.joaoandersonapi.controller;

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
        var modeloPropostaCadastrado = modeloPropostaUseCases.cadastrar(modeloProposta);
        return ResponseEntity.status(HttpStatus.CREATED).body(modeloPropostaCadastrado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterModeloPropostaPorId(@PathVariable Long id) {
        var modeloProposta = modeloPropostaUseCases.obterPor(id);
        return ResponseEntity.ok().body(modeloProposta);
    }

    @GetMapping
    public ResponseEntity<?> listarModelosProposta() {
        var listaModeloProposta = modeloPropostaUseCases.listar();
        if (listaModeloProposta == null || listaModeloProposta.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(listaModeloProposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarModeloPropostaPorId(@RequestBody ModeloProposta modeloProposta,
            @PathVariable Long id) {
        var modeloPropostaAtualizado = modeloPropostaUseCases.atualizar(modeloProposta, id);
        return ResponseEntity.ok().body(modeloPropostaAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerModeloPropostaPorId(@PathVariable Long id) {
        modeloPropostaUseCases.remover(id);
        return ResponseEntity.noContent().build();
    }

}

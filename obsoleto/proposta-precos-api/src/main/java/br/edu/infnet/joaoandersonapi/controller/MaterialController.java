package br.edu.infnet.joaoandersonapi.controller;

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

import br.edu.infnet.joaoandersonapi.model.domain.Material;
import br.edu.infnet.joaoandersonapi.model.use_cases.MaterialUseCases;

@RestController
@RequestMapping("/api/material")
public class MaterialController {

    private final MaterialUseCases materialUseCases;

    public MaterialController(MaterialUseCases materialUseCases) {
        this.materialUseCases = materialUseCases;
    }

    @PostMapping("/modelo-proposta/{id}")
    public ResponseEntity<?> cadastrarMaterialModeloProposta(@RequestBody Material material, @PathVariable Long id) {
        var materialCadastrado = materialUseCases.cadastrar(material, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(materialCadastrado);
    }

    @GetMapping
    public ResponseEntity<?> listarMateriais() {
        var materiais = materialUseCases.listar();
        if (materiais == null || materiais.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(materiais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obterMaterialPorId(@PathVariable Long id) {
        var material = materialUseCases.obterPor(id);
        return ResponseEntity.ok().body(material);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarMaterialPorId(@RequestBody Material material, @PathVariable Long id) {
        var materialAtualizado = materialUseCases.atualizar(material, id);
        return ResponseEntity.ok().body(materialAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerMaterialPorId(@PathVariable Long id) {
        materialUseCases.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/preco-total")
    public ResponseEntity<?> calcularPrecoTotalMaterial(@RequestBody Material material) {
        var precoTotal = materialUseCases.calcularPrecoTotal(material);
        return ResponseEntity.ok().body(precoTotal);
    }

    @PatchMapping("/{id}/adquirido")
    public ResponseEntity<?> marcarMaterialAdquirido(@PathVariable Long id) {
        materialUseCases.marcarAdquirido(id);
        return ResponseEntity.noContent().build();
    }

}

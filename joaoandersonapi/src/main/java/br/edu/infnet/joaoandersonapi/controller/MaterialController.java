package br.edu.infnet.joaoandersonapi.controller;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

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
import org.springframework.web.bind.annotation.RequestParam;
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
        try {
            var materialCadastrado = materialUseCases.cadastrar(material, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(materialCadastrado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao cadastrar material");
        }
    }

    @GetMapping("/modelo-proposta/{id}")
    public ResponseEntity<?> listarMateriaisModeloProposta(@PathVariable Long id) {
        try {
            var listaMateriais = materialUseCases.listar(id);
            if (listaMateriais == null || listaMateriais.isEmpty())
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok().body(listaMateriais);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao listar materiais de proposta");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarMaterialPorId(@RequestBody Material material,
            @PathVariable Long id) {
        try {
            var materialAtualizado = materialUseCases.atualizar(material, id);
            return ResponseEntity.ok().body(materialAtualizado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao atualizar material");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerMaterialPorId(@PathVariable Long id) {
        try {
            materialUseCases.remover(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao remover material");
        }
    }

    @PatchMapping("/{id}/preco")
    public ResponseEntity<?> atualizarPrecoMaterial(@PathVariable Long id, @RequestParam BigDecimal preco) {
        try {
            var novoPreco = materialUseCases.atualizarPreco(id, preco);
            return ResponseEntity.ok().body(novoPreco);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao atualizar preço do material");
        }
    }

    @PatchMapping("/{id}/quantidade")
    public ResponseEntity<?> atualizarQuantidadeMaterial(@PathVariable Long id, @RequestParam BigDecimal quantidade) {
        try {
            var novaQuantidade = materialUseCases.atualizarQuantidade(id, quantidade);
            return ResponseEntity.ok().body(novaQuantidade);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao atualizar quantidade do material");
        }
    }

    @GetMapping("/{id}/preco-total")
    public ResponseEntity<?> calcularPrecoTotalMaterial(@PathVariable Long id) {
        try {
            var precoTotal = materialUseCases.calcularPrecoTotal(id);
            return ResponseEntity.ok().body(precoTotal);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao calcular preço total do material");
        }
    }

}

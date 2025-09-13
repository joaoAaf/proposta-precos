package br.edu.infnet.joaoandersonrelatoriosapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.joaoandersonrelatoriosapi.model.domain.Proposta;
import br.edu.infnet.joaoandersonrelatoriosapi.use_cases.RelatorioUseCases;

@RestController
@RequestMapping("/api/relatorio")
public class RelatorioController {

    private final RelatorioUseCases relatorioUseCases;

    public RelatorioController(RelatorioUseCases relatorioUseCases) {
        this.relatorioUseCases = relatorioUseCases;
    }

    @GetMapping
    public ResponseEntity<?> gerarRelatorioComparacaoPropostas(@RequestBody List<Proposta> propostas) {
        var relatorio = this.relatorioUseCases.gerarRelatorioComparacaoPropostas(propostas);
        return ResponseEntity.ok(relatorio);
    }

}

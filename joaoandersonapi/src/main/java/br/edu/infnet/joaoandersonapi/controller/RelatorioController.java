package br.edu.infnet.joaoandersonapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.joaoandersonapi.dtos.PropostaRelatorio;
import br.edu.infnet.joaoandersonapi.model.use_cases.RelatorioUseCases;

@RestController
@RequestMapping("/api/relatorio")
public class RelatorioController {

    private final RelatorioUseCases relatorioUseCases;

    public RelatorioController(RelatorioUseCases relatorioUseCases) {
        this.relatorioUseCases = relatorioUseCases;
    }

    @PostMapping
    public ResponseEntity<?> gerarRelatorioComparacaoPropostas(@RequestBody List<PropostaRelatorio> propostas) {
        return ResponseEntity.ok(this.relatorioUseCases.gerarRelatorioComparacaoPropostas(propostas));
    }

}

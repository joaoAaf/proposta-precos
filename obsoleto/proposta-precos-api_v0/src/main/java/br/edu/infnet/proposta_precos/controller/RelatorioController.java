package br.edu.infnet.proposta_precos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.proposta_precos.dtos.comparacao_propostas.PropostasIds;
import br.edu.infnet.proposta_precos.model.use_cases.RelatorioUseCases;

@RestController
@RequestMapping("/api/relatorio")
public class RelatorioController {

    private final RelatorioUseCases relatorioUseCases;

    public RelatorioController(RelatorioUseCases relatorioUseCases) {
        this.relatorioUseCases = relatorioUseCases;
    }

    @PostMapping
    public ResponseEntity<?> gerarRelatorioComparacaoPropostas(@RequestBody PropostasIds propostasIds) {
        return ResponseEntity.ok(this.relatorioUseCases.gerarRelatorioComparacaoPropostas(propostasIds));
    }

}

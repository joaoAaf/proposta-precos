package br.com.apisemaperreio.relatorios_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apisemaperreio.relatorios_api.model.domain.Proposta;
import br.com.apisemaperreio.relatorios_api.use_cases.RelatorioUseCases;

@RestController
@RequestMapping("/api/relatorio")
public class RelatorioController {

    private final RelatorioUseCases relatorioUseCases;

    public RelatorioController(RelatorioUseCases relatorioUseCases) {
        this.relatorioUseCases = relatorioUseCases;
    }

    @PostMapping
    public ResponseEntity<?> gerarRelatorioComparacaoPropostas(@RequestBody List<Proposta> propostas) {
        return ResponseEntity.ok(this.relatorioUseCases.gerarRelatorioComparacaoPropostas(propostas));
    }

}

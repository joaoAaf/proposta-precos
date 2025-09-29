package br.com.apisemaperreio.proposta_precos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apisemaperreio.proposta_precos.model.dto.relatorio.PropostasIdsRequest;
import br.com.apisemaperreio.proposta_precos.model.use_cases.RelatorioUseCases;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/relatorio")
public class RelatorioController {

    private final RelatorioUseCases relatorioUseCases;

    public RelatorioController(RelatorioUseCases relatorioUseCases) {
        this.relatorioUseCases = relatorioUseCases;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> gerarRelatorioComparacaoPropostas(@RequestBody @Valid PropostasIdsRequest propostasIds) {
        return ResponseEntity.ok(this.relatorioUseCases.gerarRelatorioComparacaoPropostas(propostasIds));
    }

}

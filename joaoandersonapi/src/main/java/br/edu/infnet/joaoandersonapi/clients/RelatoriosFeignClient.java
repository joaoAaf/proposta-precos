package br.edu.infnet.joaoandersonapi.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.infnet.joaoandersonapi.dtos.PropostaRelatorio;
import br.edu.infnet.joaoandersonapi.model.domain.RelatorioComparacaoPropostas;

@FeignClient(name = "relatoriosClient", url = "${relatorios.api.url}")
public interface RelatoriosFeignClient {

    @PostMapping("/api/relatorio")
    RelatorioComparacaoPropostas gerarRelatorioComparacaoPropostas(@RequestBody List<PropostaRelatorio> propostas);

}

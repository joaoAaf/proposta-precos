package br.edu.infnet.joaoandersonapi.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.infnet.joaoandersonapi.dtos.comparacao_propostas.ComparacaoPropostasApi;
import br.edu.infnet.joaoandersonapi.dtos.comparacao_propostas.PropostaRelatorioApi;

@FeignClient(name = "relatoriosClient", url = "${relatorios.api.url}")
public interface RelatoriosFeignClient {

    @PostMapping("/api/relatorio")
    ComparacaoPropostasApi gerarRelatorioComparacaoPropostas(@RequestBody List<PropostaRelatorioApi> propostas);

}

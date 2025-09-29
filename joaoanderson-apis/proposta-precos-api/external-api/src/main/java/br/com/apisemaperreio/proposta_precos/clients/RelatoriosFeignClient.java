package br.com.apisemaperreio.proposta_precos.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.apisemaperreio.proposta_precos.model.dto.ComparacaoPropostasApi;
import br.com.apisemaperreio.proposta_precos.model.dto.PropostaRelatorioApi;

@FeignClient(name = "relatoriosClient", url = "${relatorios.api.url}")
public interface RelatoriosFeignClient {

    @PostMapping("/api/relatorio")
    ComparacaoPropostasApi gerarRelatorioComparacaoPropostas(@RequestBody List<PropostaRelatorioApi> propostas);

}

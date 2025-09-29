package br.com.apisemaperreio.proposta_precos.model.use_cases;

import br.com.apisemaperreio.proposta_precos.model.dto.relatorio.ComparacaoPropostasResponse;
import br.com.apisemaperreio.proposta_precos.model.dto.relatorio.PropostasIdsRequest;

public interface RelatorioUseCases {

    ComparacaoPropostasResponse gerarRelatorioComparacaoPropostas(PropostasIdsRequest propostasIds);

}

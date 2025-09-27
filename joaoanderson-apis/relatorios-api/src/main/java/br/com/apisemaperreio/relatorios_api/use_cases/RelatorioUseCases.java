package br.com.apisemaperreio.relatorios_api.use_cases;

import java.util.List;

import br.com.apisemaperreio.relatorios_api.model.domain.ComparacaoPropostas;
import br.com.apisemaperreio.relatorios_api.model.domain.Proposta;

public interface RelatorioUseCases {

    ComparacaoPropostas gerarRelatorioComparacaoPropostas(List<Proposta> propostas);

}

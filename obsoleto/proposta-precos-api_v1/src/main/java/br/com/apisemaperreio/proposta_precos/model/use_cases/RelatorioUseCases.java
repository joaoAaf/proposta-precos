package br.com.apisemaperreio.proposta_precos.model.use_cases;

import br.com.apisemaperreio.proposta_precos.model.dto.relatorio.ComparacaoPropostasUsuario;
import br.com.apisemaperreio.proposta_precos.model.dto.relatorio.PropostasIds;

public interface RelatorioUseCases {

    ComparacaoPropostasUsuario gerarRelatorioComparacaoPropostas(PropostasIds propostasIds);

}

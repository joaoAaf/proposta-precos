package br.edu.infnet.proposta_precos.model.use_cases;

import br.edu.infnet.proposta_precos.dtos.comparacao_propostas.ComparacaoPropostasUsuario;
import br.edu.infnet.proposta_precos.dtos.comparacao_propostas.PropostasIds;

public interface RelatorioUseCases {

    ComparacaoPropostasUsuario gerarRelatorioComparacaoPropostas(PropostasIds propostasIds);

}

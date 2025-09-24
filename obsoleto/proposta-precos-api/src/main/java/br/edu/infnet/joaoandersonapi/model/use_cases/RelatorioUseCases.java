package br.edu.infnet.joaoandersonapi.model.use_cases;

import br.edu.infnet.joaoandersonapi.dtos.comparacao_propostas.ComparacaoPropostasUsuario;
import br.edu.infnet.joaoandersonapi.dtos.comparacao_propostas.PropostasIds;

public interface RelatorioUseCases {

    ComparacaoPropostasUsuario gerarRelatorioComparacaoPropostas(PropostasIds propostasIds);

}

package br.edu.infnet.joaoandersonapi.model.use_cases;

import java.util.List;

import br.edu.infnet.joaoandersonapi.dtos.PropostaRelatorio;
import br.edu.infnet.joaoandersonapi.model.domain.RelatorioComparacaoPropostas;

public interface RelatorioUseCases {

    RelatorioComparacaoPropostas gerarRelatorioComparacaoPropostas(List<PropostaRelatorio> propostas);

}

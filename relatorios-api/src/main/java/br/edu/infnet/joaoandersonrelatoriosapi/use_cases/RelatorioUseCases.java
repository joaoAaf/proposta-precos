package br.edu.infnet.joaoandersonrelatoriosapi.use_cases;

import java.util.List;

import br.edu.infnet.joaoandersonrelatoriosapi.model.domain.ComparacaoPropostas;
import br.edu.infnet.joaoandersonrelatoriosapi.model.domain.Proposta;

public interface RelatorioUseCases {

    ComparacaoPropostas gerarRelatorioComparacaoPropostas(List<Proposta> propostas);

}

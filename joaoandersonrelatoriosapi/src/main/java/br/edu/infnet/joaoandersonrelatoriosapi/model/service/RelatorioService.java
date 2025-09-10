package br.edu.infnet.joaoandersonrelatoriosapi.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.joaoandersonrelatoriosapi.model.domain.Proposta;
import br.edu.infnet.joaoandersonrelatoriosapi.model.domain.RelatorioComparacaoPropostas;

@Service
public class RelatorioService {

    public RelatorioComparacaoPropostas gerarRelatorioComparacaoPropostas(List<Proposta> propostas) {
        return new RelatorioComparacaoPropostas(propostas);
    }

}

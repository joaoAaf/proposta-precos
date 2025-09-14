package br.edu.infnet.joaoandersonapi.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.joaoandersonapi.clients.RelatoriosFeignClient;
import br.edu.infnet.joaoandersonapi.dtos.PropostaRelatorio;
import br.edu.infnet.joaoandersonapi.model.domain.RelatorioComparacaoPropostas;
import br.edu.infnet.joaoandersonapi.model.use_cases.RelatorioUseCases;

@Service
public class RelatorioService implements RelatorioUseCases {

    private final RelatoriosFeignClient relatoriosClient;

    public RelatorioService(RelatoriosFeignClient relatoriosClient) {
        this.relatoriosClient = relatoriosClient;
    }

    @Override
    public RelatorioComparacaoPropostas gerarRelatorioComparacaoPropostas(List<PropostaRelatorio> propostas) {
        return relatoriosClient.gerarRelatorioComparacaoPropostas(propostas);
    }

}

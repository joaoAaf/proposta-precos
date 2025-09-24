package br.edu.infnet.joaoandersonapi.model.service;

import org.springframework.stereotype.Service;

import br.edu.infnet.joaoandersonapi.clients.RelatoriosFeignClient;
import br.edu.infnet.joaoandersonapi.dtos.comparacao_propostas.ComparacaoPropostasUsuario;
import br.edu.infnet.joaoandersonapi.dtos.comparacao_propostas.PropostaRelatorioApi;
import br.edu.infnet.joaoandersonapi.dtos.comparacao_propostas.PropostasIds;
import br.edu.infnet.joaoandersonapi.model.use_cases.PropostaUseCases;
import br.edu.infnet.joaoandersonapi.model.use_cases.RelatorioUseCases;

@Service
public class RelatorioService implements RelatorioUseCases {

    private final RelatoriosFeignClient relatoriosClient;
    private final PropostaUseCases propostaUseCases;

    public RelatorioService(RelatoriosFeignClient relatoriosClient, PropostaUseCases propostaUseCases) {
        this.relatoriosClient = relatoriosClient;
        this.propostaUseCases = propostaUseCases;
    }

    @Override
    public ComparacaoPropostasUsuario gerarRelatorioComparacaoPropostas(PropostasIds propostasIds) {
        var propostas = propostaUseCases.obterPorIds(propostasIds.ids());
        var relatorioApi = relatoriosClient.gerarRelatorioComparacaoPropostas(propostas.stream()
                .map(PropostaRelatorioApi::new)
                .toList());
        return new ComparacaoPropostasUsuario(relatorioApi, propostas);
    }

}

package br.com.apisemaperreio.proposta_precos.model.service;

import org.springframework.stereotype.Service;

import br.com.apisemaperreio.proposta_precos.clients.RelatoriosFeignClient;
import br.com.apisemaperreio.proposta_precos.model.dto.relatorio.ComparacaoPropostasUsuario;
import br.com.apisemaperreio.proposta_precos.model.dto.relatorio.PropostaRelatorioApi;
import br.com.apisemaperreio.proposta_precos.model.dto.relatorio.PropostasIds;
import br.com.apisemaperreio.proposta_precos.model.use_cases.PropostaUseCases;
import br.com.apisemaperreio.proposta_precos.model.use_cases.RelatorioUseCases;

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

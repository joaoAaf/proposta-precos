package br.com.apisemaperreio.proposta_precos.model.dto;

import java.time.LocalDate;
import java.util.List;

public record ComparacaoPropostasApi(LocalDate dataEmissao,
        Double media,
        Double mediana,
        String conclusao,
        List<PropostaRelatorioApi> propostas,
        List<PropostaRelatorioApi> propostasVantajosas) {

}

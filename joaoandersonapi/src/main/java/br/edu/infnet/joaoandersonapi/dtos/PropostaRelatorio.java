package br.edu.infnet.joaoandersonapi.dtos;

import java.util.List;

import br.edu.infnet.joaoandersonapi.model.domain.Proposta;

public record PropostaRelatorio(Long id, List<MaterialRelatorio> materiais, Double desconto, Double precoGlobal) {

        public PropostaRelatorio(Proposta proposta) {
                this(proposta.getId(), proposta.getMateriais().stream().map(MaterialRelatorio::new).toList(),
                                proposta.getDesconto().doubleValue(), proposta.calcularPrecoGlobal().doubleValue());
        }

}
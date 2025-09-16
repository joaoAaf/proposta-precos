package br.com.apisemaperreio.proposta_precos.model.dto.proposta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.apisemaperreio.proposta_precos.model.domain.Proposta;
import br.com.apisemaperreio.proposta_precos.model.dto.endereco.EnderecoResponse;
import br.com.apisemaperreio.proposta_precos.model.dto.fornecedor.FornecedorResponse;
import br.com.apisemaperreio.proposta_precos.model.dto.material.MaterialResponseRequisitante;
import br.com.apisemaperreio.proposta_precos.model.dto.requisitante.RequisitanteResponse;

public record PropostaResponse(
                Long id,
                LocalDate dataCriacao,
                RequisitanteResponse requisitante,
                FornecedorResponse fornecedor,
                List<MaterialResponseRequisitante> materiais,
                BigDecimal desconto,
                EnderecoResponse enderecoEntrega,
                String observacoesRequisitante,
                String observacoesFornecedor) {

        public PropostaResponse(Proposta proposta) {
                this(proposta.getId(), proposta.getDataCriacao(),
                                new RequisitanteResponse(proposta.getRequisitante()),
                                new FornecedorResponse(proposta.getFornecedor()),
                                proposta.getMateriais().stream().map(MaterialResponseRequisitante::new).toList(),
                                proposta.getDesconto(),
                                new EnderecoResponse(proposta.getEnderecoEntrega()),
                                proposta.getObservacoesRequisitante(),
                                proposta.getObservacoesFornecedor());
        }

}

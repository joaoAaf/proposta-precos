package br.com.apisemaperreio.proposta_precos.model.dto.proposta;

import java.util.List;

import br.com.apisemaperreio.proposta_precos.model.domain.Proposta;
import br.com.apisemaperreio.proposta_precos.model.dto.endereco.EnderecoResponse;
import br.com.apisemaperreio.proposta_precos.model.dto.material.MaterialResponseFornecedor;
import br.com.apisemaperreio.proposta_precos.model.dto.requisitante.RequisitanteResponse;

public record PropostaModeloResponse(
                RequisitanteResponse requisitante,
                List<MaterialResponseFornecedor> materiais,
                EnderecoResponse enderecoEntrega,
                String observacoesRequisitante) {

        public PropostaModeloResponse(Proposta proposta) {
                this(new RequisitanteResponse(proposta.getRequisitante()),
                                proposta.getMateriais().stream().map(MaterialResponseFornecedor::new).toList(),
                                new EnderecoResponse(proposta.getEnderecoEntrega()),
                                proposta.getObservacoesRequisitante());
        }

}

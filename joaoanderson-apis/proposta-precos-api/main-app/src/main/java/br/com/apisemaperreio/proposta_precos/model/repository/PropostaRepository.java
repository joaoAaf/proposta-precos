package br.com.apisemaperreio.proposta_precos.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apisemaperreio.proposta_precos.model.domain.Proposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

        List<Proposta> findByIdIn(List<Long> ids);

        List<Proposta> findByDataCriacaoIsNotNull();

        void deleteByDataCriacaoIsNull();

}

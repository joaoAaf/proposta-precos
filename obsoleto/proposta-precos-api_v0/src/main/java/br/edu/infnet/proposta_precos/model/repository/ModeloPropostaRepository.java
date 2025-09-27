package br.edu.infnet.proposta_precos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.proposta_precos.model.domain.ModeloProposta;

@Repository
public interface ModeloPropostaRepository extends JpaRepository<ModeloProposta, Long> {

}

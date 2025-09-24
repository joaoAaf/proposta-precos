package br.edu.infnet.joaoandersonapi.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.joaoandersonapi.model.domain.Proposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

        List<Proposta> findByIdIn(List<Long> ids);

}

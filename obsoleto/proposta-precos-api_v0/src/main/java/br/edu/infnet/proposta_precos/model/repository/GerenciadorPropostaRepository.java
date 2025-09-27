package br.edu.infnet.proposta_precos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.infnet.proposta_precos.model.domain.GerenciadorProposta;

@Repository
public interface GerenciadorPropostaRepository extends JpaRepository<GerenciadorProposta, String> {

    @Modifying
    @Query("DELETE FROM GerenciadorProposta g WHERE g.valido = false OR g.dataExpiracao < CURRENT_TIMESTAMP")
    void deleteInvalidosOuExpirados();

}

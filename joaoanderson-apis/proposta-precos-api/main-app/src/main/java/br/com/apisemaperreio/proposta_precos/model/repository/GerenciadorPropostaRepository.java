package br.com.apisemaperreio.proposta_precos.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.apisemaperreio.proposta_precos.model.domain.GerenciadorProposta;

@Repository
public interface GerenciadorPropostaRepository extends JpaRepository<GerenciadorProposta, String> {

    @Query("SELECT g FROM GerenciadorProposta g WHERE g.valido = false OR g.dataExpiracao < CURRENT_TIMESTAMP")
    List<GerenciadorProposta> obterInvalidosOuExpirados();

}

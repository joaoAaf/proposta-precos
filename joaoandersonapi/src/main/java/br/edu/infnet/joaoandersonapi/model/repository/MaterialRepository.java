package br.edu.infnet.joaoandersonapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.joaoandersonapi.model.domain.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

}

package br.gov.mt.seplag.seletivo.repository;

import br.gov.mt.seplag.seletivo.model.entities.ServidorTemporario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para operações de persistência da entidade ServidorTemporario.
 */
@Repository
public interface ServidorTemporarioRepository extends JpaRepository<ServidorTemporario, Integer> {
}
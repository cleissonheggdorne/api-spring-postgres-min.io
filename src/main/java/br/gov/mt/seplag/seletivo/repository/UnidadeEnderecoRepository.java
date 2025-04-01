package br.gov.mt.seplag.seletivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.seplag.seletivo.model.entities.UnidadeEndereco;

/**
 * Repository interface for UnidadeEndereco entity.
 * Provides CRUD operations for the UnidadeEndereco entity.
 */
@Repository
public interface UnidadeEnderecoRepository extends JpaRepository<UnidadeEndereco, Integer> {
    // You can add custom query methods here if needed
}
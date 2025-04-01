package br.gov.mt.seplag.seletivo.repository;

import br.gov.mt.seplag.seletivo.model.entities.Pessoa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para operações de persistência da entidade Pessoa.
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    List<Pessoa> findByNomeContainingIgnoreCase(String nome);
}
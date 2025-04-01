package br.gov.mt.seplag.seletivo.repository;

import br.gov.mt.seplag.seletivo.model.entities.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório para operações de persistência da entidade Unidade.
 */
@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Integer> {
    
    /**
     * Busca unidades por parte do nome.
     *
     * @param nome Parte do nome a ser buscada
     * @return Lista de unidades
     */
    List<Unidade> findByNomeContainingIgnoreCase(String nome);
    
    /**
     * Busca unidades por sigla.
     *
     * @param sigla Sigla a ser buscada
     * @return Lista de unidades
     */
    List<Unidade> findBySiglaIgnoreCase(String sigla);
}
package br.gov.mt.seplag.seletivo.repository;

import br.gov.mt.seplag.seletivo.model.entities.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório para operações de persistência da entidade Cidade.
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
    
    /**
     * Busca cidades por parte do nome.
     *
     * @param nome Parte do nome a ser buscada
     * @return Lista de cidades
     */
    List<Cidade> findByNomeContainingIgnoreCase(String nome);
    
    /**
     * Busca cidades por UF.
     *
     * @param uf UF a ser buscada
     * @return Lista de cidades
     */
    List<Cidade> findByUfIgnoreCase(String uf);
    
    /**
     * Busca cidades por nome e UF.
     *
     * @param nome Nome da cidade
     * @param uf UF da cidade
     * @return Lista de cidades
     */
    List<Cidade> findByNomeContainingIgnoreCaseAndUfIgnoreCase(String nome, String uf);
}
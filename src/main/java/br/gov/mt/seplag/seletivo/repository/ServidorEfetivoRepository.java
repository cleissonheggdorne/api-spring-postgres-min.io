package br.gov.mt.seplag.seletivo.repository;

import br.gov.mt.seplag.seletivo.controller.dto.projection.ServidorEfetivoInfo;
import br.gov.mt.seplag.seletivo.model.entities.ServidorEfetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório para operações de persistência da entidade ServidorEfetivo.
 */
@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Integer> {
    
    /**
     * Busca um servidor efetivo pela matrícula.
     *
     * @param matricula Matrícula do servidor
     * @return Optional contendo o servidor efetivo, se encontrado
     */
    Optional<ServidorEfetivo> findByMatricula(String matricula);
    
    /**
     * Busca servidores efetivos por parte do nome.
     *
     * @param nome Parte do nome a ser buscada
     * @return Lista de servidores efetivos
     */
    @Query("SELECT se FROM ServidorEfetivo se JOIN se.pessoa p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<ServidorEfetivo> findByNomeContaining(String nome);

    @Query("""
        SELECT new br.gov.mt.seplag.seletivo.controller.dto.projection.ServidorEfetivoInfo(
        p.nome, 
        cast(FUNCTION('TIMESTAMPDIFF', YEAR, p.dataNascimento, CURRENT_DATE) as java.lang.Long), 
        u.nome,
        fp.hash) 
        FROM ServidorEfetivo se 
        JOIN se.pessoa p 
        LEFT JOIN Lotacao l ON l.pessoa = p 
        LEFT JOIN l.unidade u 
        LEFT JOIN FotoPessoa fp ON fp.pessoa = p  
        WHERE u.id = :idUnidade
    """)
    List<ServidorEfetivoInfo> findServidoresLotadosPorUnidade(Integer idUnidade);
}
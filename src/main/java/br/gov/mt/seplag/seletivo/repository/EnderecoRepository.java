package br.gov.mt.seplag.seletivo.repository;

import br.gov.mt.seplag.seletivo.model.entities.Cidade;
import br.gov.mt.seplag.seletivo.model.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório para operações de persistência da entidade Endereco.
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    
    /**
     * Busca endereços por cidade.
     *
     * @param cidade Cidade a ser buscada
     * @return Lista de endereços
     */
    List<Endereco> findByCidade(Cidade cidade);
    
    /**
     * Busca endereços por ID da cidade.
     *
     * @param cidadeId ID da cidade a ser buscada
     * @return Lista de endereços
     */
    List<Endereco> findByCidadeId(Integer cidadeId);
    
    /**
     * Busca endereços por bairro.
     *
     * @param bairro Bairro a ser buscado
     * @return Lista de endereços
     */
    List<Endereco> findByBairroContainingIgnoreCase(String bairro);
    
    /**
     * Busca endereços por logradouro.
     *
     * @param logradouro Logradouro a ser buscado
     * @return Lista de endereços
     */
    List<Endereco> findByLogradouroContainingIgnoreCase(String logradouro);

     @Query("""
        SELECT DISTINCT e
        FROM Pessoa p 
        LEFT JOIN Lotacao l ON l.pessoa = p 
        LEFT JOIN l.unidade u 
        LEFT JOIN UnidadeEndereco ue ON ue.unidade = u
        LEFT JOIN ue.endereco e 
        WHERE p.nome ILIKE %:nomeServidor%
    """)
    List<Endereco> findEnderecoUnidadePorNomeServidor(String nomeServidor);

    
}
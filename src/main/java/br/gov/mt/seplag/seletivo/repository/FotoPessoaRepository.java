package br.gov.mt.seplag.seletivo.repository;

import br.gov.mt.seplag.seletivo.model.entities.FotoPessoa;
import br.gov.mt.seplag.seletivo.model.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório para operações de persistência da entidade FotoPessoa.
 */
@Repository
public interface FotoPessoaRepository extends JpaRepository<FotoPessoa, Integer> {
    
    /**
     * Busca todas as fotos de uma pessoa específica.
     *
     * @param pessoa A pessoa cujas fotos serão buscadas
     * @return Lista de fotos da pessoa
     */
    List<FotoPessoa> findByPessoa(Pessoa pessoa);
    
    /**
     * Busca todas as fotos de uma pessoa pelo ID da pessoa.
     *
     * @param pessoaId O ID da pessoa cujas fotos serão buscadas
     * @return Lista de fotos da pessoa
     */
    List<FotoPessoa> findByPessoaId(Integer pessoaId);
}
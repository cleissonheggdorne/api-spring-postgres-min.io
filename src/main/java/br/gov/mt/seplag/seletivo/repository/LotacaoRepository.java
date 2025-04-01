package br.gov.mt.seplag.seletivo.repository;

import br.gov.mt.seplag.seletivo.model.entities.Lotacao;
import br.gov.mt.seplag.seletivo.model.entities.Pessoa;
import br.gov.mt.seplag.seletivo.model.entities.Unidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repositório para operações de persistência da entidade Lotacao.
 */
@Repository
public interface LotacaoRepository extends JpaRepository<Lotacao, Integer> {
    
    /**
     * Busca todas as lotações de uma pessoa.
     *
     * @param pessoa A pessoa cujas lotações serão buscadas
     * @return Lista de lotações da pessoa
     */
    List<Lotacao> findByPessoa(Pessoa pessoa);
    
    /**
     * Busca todas as lotações de uma pessoa pelo ID da pessoa.
     *
     * @param pessoaId O ID da pessoa cujas lotações serão buscadas
     * @return Lista de lotações da pessoa
     */
    List<Lotacao> findByPessoaId(Integer pessoaId);
    
    /**
     * Busca todas as lotações em uma unidade.
     *
     * @param unidade A unidade cujas lotações serão buscadas
     * @return Lista de lotações na unidade
     */
    List<Lotacao> findByUnidade(Unidade unidade);
    
    /**
     * Busca todas as lotações em uma unidade pelo ID da unidade.
     *
     * @param unidadeId O ID da unidade cujas lotações serão buscadas
     * @return Lista de lotações na unidade
     */
    List<Lotacao> findByUnidadeId(Integer unidadeId);
    
    /**
     * Busca a lotação atual de uma pessoa.
     *
     * @param pessoa A pessoa cuja lotação atual será buscada
     * @param data Data de referência
     * @return Optional contendo a lotação atual, se encontrada
     */
    @Query("SELECT l FROM Lotacao l WHERE l.pessoa = :pessoa AND l.dataLotacao <= :data AND (l.dataRemocao IS NULL OR l.dataRemocao >= :data) ORDER BY l.dataLotacao DESC")
    Optional<Lotacao> findLotacaoAtual(Pessoa pessoa, LocalDate data);
    
    /**
     * Busca a lotação atual de uma pessoa pelo ID da pessoa.
     *
     * @param pessoaId O ID da pessoa cuja lotação atual será buscada
     * @param data Data de referência
     * @return Optional contendo a lotação atual, se encontrada
     */
    @Query("SELECT l FROM Lotacao l WHERE l.pessoa.id = :pessoaId AND l.dataLotacao <= :data AND (l.dataRemocao IS NULL OR l.dataRemocao >= :data) ORDER BY l.dataLotacao DESC")
    Optional<Lotacao> findLotacaoAtualByPessoaId(Integer pessoaId, LocalDate data);
    
    /**
     * Busca todas as lotações ativas em uma data específica.
     *
     * @param data Data de referência
     * @return Lista de lotações ativas
     */
    @Query("SELECT l FROM Lotacao l WHERE l.dataLotacao <= :data AND (l.dataRemocao IS NULL OR l.dataRemocao >= :data)")
    List<Lotacao> findLotacoesAtivas(LocalDate data);
}
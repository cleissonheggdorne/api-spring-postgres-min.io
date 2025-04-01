package br.gov.mt.seplag.seletivo.service;

import br.gov.mt.seplag.seletivo.model.entities.Lotacao;
import br.gov.mt.seplag.seletivo.model.entities.Pessoa;
import br.gov.mt.seplag.seletivo.model.entities.Unidade;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface de serviço para operações relacionadas à entidade Lotacao.
 */
public interface LotacaoService {

    /**
     * Busca todas as lotações cadastradas.
     *
     * @return Lista de todas as lotações
     */
    List<Lotacao> findAll();

    /**
     * Busca uma lotação pelo ID.
     *
     * @param id ID da lotação
     * @return Optional contendo a lotação, se encontrada
     */
    Optional<Lotacao> findById(Integer id);

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
    Optional<Lotacao> findLotacaoAtual(Pessoa pessoa, LocalDate data);

    /**
     * Busca a lotação atual de uma pessoa pelo ID da pessoa.
     *
     * @param pessoaId O ID da pessoa cuja lotação atual será buscada
     * @param data Data de referência
     * @return Optional contendo a lotação atual, se encontrada
     */
    Optional<Lotacao> findLotacaoAtualByPessoaId(Integer pessoaId, LocalDate data);

    /**
     * Busca todas as lotações ativas em uma data específica.
     *
     * @param data Data de referência
     * @return Lista de lotações ativas
     */
    List<Lotacao> findLotacoesAtivas(LocalDate data);

    /**
     * Salva uma nova lotação ou atualiza uma existente.
     *
     * @param lotacao Objeto lotação a ser salvo
     * @return Lotacao salva
     */
    Lotacao save(Lotacao lotacao);

    /**
     * Remove uma lotação pelo ID.
     *
     * @param id ID da lotação a ser removida
     */
    void deleteById(Integer id);

    /**
     * Verifica se existe uma lotação com o ID informado.
     *
     * @param id ID da lotação
     * @return true se existir, false caso contrário
     */
    boolean existsById(Integer id);
}
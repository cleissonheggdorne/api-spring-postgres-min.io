package br.gov.mt.seplag.seletivo.service;

import br.gov.mt.seplag.seletivo.model.entities.Unidade;

import java.util.List;
import java.util.Optional;

/**
 * Interface de serviço para operações relacionadas à entidade Unidade.
 */
public interface UnidadeService {

    /**
     * Busca todas as unidades cadastradas.
     *
     * @return Lista de todas as unidades
     */
    List<Unidade> findAll();

    /**
     * Busca uma unidade pelo ID.
     *
     * @param id ID da unidade
     * @return Optional contendo a unidade, se encontrada
     */
    Optional<Unidade> findById(Integer id);

    /**
     * Busca unidades por parte do nome.
     *
     * @param nome Parte do nome a ser buscada
     * @return Lista de unidades
     */
    List<Unidade> findByNomeContaining(String nome);

    /**
     * Busca unidades por sigla.
     *
     * @param sigla Sigla a ser buscada
     * @return Lista de unidades
     */
    List<Unidade> findBySigla(String sigla);

    /**
     * Salva uma nova unidade ou atualiza uma existente.
     *
     * @param unidade Objeto unidade a ser salvo
     * @return Unidade salva
     */
    Unidade save(Unidade unidade);

    /**
     * Remove uma unidade pelo ID.
     *
     * @param id ID da unidade a ser removida
     */
    void deleteById(Integer id);

    /**
     * Verifica se existe uma unidade com o ID informado.
     *
     * @param id ID da unidade
     * @return true se existir, false caso contrário
     */
    boolean existsById(Integer id);
}
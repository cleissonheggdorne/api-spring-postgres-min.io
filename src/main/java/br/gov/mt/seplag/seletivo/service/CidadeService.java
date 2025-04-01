package br.gov.mt.seplag.seletivo.service;

import br.gov.mt.seplag.seletivo.model.entities.Cidade;

import java.util.List;
import java.util.Optional;

/**
 * Interface de serviço para operações relacionadas à entidade Cidade.
 */
public interface CidadeService {

    /**
     * Busca todas as cidades cadastradas.
     *
     * @return Lista de todas as cidades
     */
    List<Cidade> findAll();

    /**
     * Busca uma cidade pelo ID.
     *
     * @param id ID da cidade
     * @return Optional contendo a cidade, se encontrada
     */
    Optional<Cidade> findById(Integer id);

    /**
     * Busca cidades por parte do nome.
     *
     * @param nome Parte do nome a ser buscada
     * @return Lista de cidades
     */
    List<Cidade> findByNomeContaining(String nome);

    /**
     * Busca cidades por UF.
     *
     * @param uf UF a ser buscada
     * @return Lista de cidades
     */
    List<Cidade> findByUf(String uf);

    /**
     * Busca cidades por nome e UF.
     *
     * @param nome Nome da cidade
     * @param uf UF da cidade
     * @return Lista de cidades
     */
    List<Cidade> findByNomeContainingAndUf(String nome, String uf);

    /**
     * Salva uma nova cidade ou atualiza uma existente.
     *
     * @param cidade Objeto cidade a ser salvo
     * @return Cidade salva
     */
    Cidade save(Cidade cidade);

    /**
     * Remove uma cidade pelo ID.
     *
     * @param id ID da cidade a ser removida
     */
    void deleteById(Integer id);

    /**
     * Verifica se existe uma cidade com o ID informado.
     *
     * @param id ID da cidade
     * @return true se existir, false caso contrário
     */
    boolean existsById(Integer id);
}
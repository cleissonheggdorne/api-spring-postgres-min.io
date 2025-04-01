package br.gov.mt.seplag.seletivo.service;

import br.gov.mt.seplag.seletivo.model.entities.Pessoa;

import java.util.List;
import java.util.Optional;

/**
 * Interface de serviço para operações relacionadas à entidade Pessoa.
 */
public interface PessoaService {

    /**
     * Busca todas as pessoas cadastradas.
     *
     * @return Lista de todas as pessoas
     */
    List<Pessoa> findAll();

    /**
     * Busca uma pessoa pelo ID.
     *
     * @param id ID da pessoa
     * @return Optional contendo a pessoa, se encontrada
     */
    Optional<Pessoa> findById(Integer id);

    /**
     * Salva uma nova pessoa ou atualiza uma existente.
     *
     * @param pessoa Objeto pessoa a ser salvo
     * @return Pessoa salva
     */
    Pessoa save(Pessoa pessoa);

    /**
     * Remove uma pessoa pelo ID.
     *
     * @param id ID da pessoa a ser removida
     */
    void deleteById(Integer id);

    /**
     * Verifica se existe uma pessoa com o ID informado.
     *
     * @param id ID da pessoa
     * @return true se existir, false caso contrário
     */
    boolean existsById(Integer id);
}
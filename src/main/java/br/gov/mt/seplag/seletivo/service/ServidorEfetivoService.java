package br.gov.mt.seplag.seletivo.service;

import br.gov.mt.seplag.seletivo.model.entities.ServidorEfetivo;

import java.util.List;
import java.util.Optional;

/**
 * Interface de serviço para operações relacionadas à entidade ServidorEfetivo.
 */
public interface ServidorEfetivoService {

    /**
     * Busca todos os servidores efetivos cadastrados.
     *
     * @return Lista de todos os servidores efetivos
     */
    List<ServidorEfetivo> findAll();

    /**
     * Busca um servidor efetivo pelo ID.
     *
     * @param id ID do servidor efetivo
     * @return Optional contendo o servidor efetivo, se encontrado
     */
    Optional<ServidorEfetivo> findById(Integer id);

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
    List<ServidorEfetivo> findByNomeContaining(String nome);

    /**
     * Salva um novo servidor efetivo ou atualiza um existente.
     *
     * @param servidorEfetivo Objeto servidor efetivo a ser salvo
     * @return ServidorEfetivo salvo
     */
    ServidorEfetivo save(ServidorEfetivo servidorEfetivo);

    /**
     * Remove um servidor efetivo pelo ID.
     *
     * @param id ID do servidor efetivo a ser removido
     */
    void deleteById(Integer id);

    /**
     * Verifica se existe um servidor efetivo com o ID informado.
     *
     * @param id ID do servidor efetivo
     * @return true se existir, false caso contrário
     */
    boolean existsById(Integer id);
}
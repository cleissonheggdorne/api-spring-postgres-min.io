package br.gov.mt.seplag.seletivo.service;

import br.gov.mt.seplag.seletivo.model.entities.ServidorTemporario;

import java.util.List;
import java.util.Optional;

/**
 * Interface de serviço para operações relacionadas à entidade ServidorTemporario.
 */
public interface ServidorTemporarioService {

    /**
     * Busca todos os servidores temporários cadastrados.
     *
     * @return Lista de todos os servidores temporários
     */
    List<ServidorTemporario> findAll();

    /**
     * Busca um servidor temporário pelo ID.
     *
     * @param id ID do servidor temporário
     * @return Optional contendo o servidor temporário, se encontrado
     */
    Optional<ServidorTemporario> findById(Integer id);

    /**
     * Salva um novo servidor temporário ou atualiza um existente.
     *
     * @param servidorTemporario Objeto servidor temporário a ser salvo
     * @return ServidorTemporario salvo
     */
    ServidorTemporario save(ServidorTemporario servidorTemporario);

    /**
     * Remove um servidor temporário pelo ID.
     *
     * @param id ID do servidor temporário a ser removido
     */
    void deleteById(Integer id);

    /**
     * Verifica se existe um servidor temporário com o ID informado.
     *
     * @param id ID do servidor temporário
     * @return true se existir, false caso contrário
     */
    boolean existsById(Integer id);
}
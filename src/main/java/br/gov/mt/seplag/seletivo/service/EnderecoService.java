package br.gov.mt.seplag.seletivo.service;

import br.gov.mt.seplag.seletivo.model.entities.Endereco;

import java.util.List;
import java.util.Optional;

/**
 * Interface de serviço para operações relacionadas à entidade Endereco.
 */
public interface EnderecoService {

    /**
     * Busca todos os endereços cadastrados.
     *
     * @return Lista de todos os endereços
     */
    List<Endereco> findAll();

    /**
     * Busca um endereço pelo ID.
     *
     * @param id ID do endereço
     * @return Optional contendo o endereço, se encontrado
     */
    Optional<Endereco> findById(Integer id);


    /**
     * Salva um novo endereço ou atualiza um existente.
     *
     * @param endereco Objeto endereço a ser salvo
     * @return Endereco salvo
     */
    Endereco save(Endereco endereco);

    /**
     * Remove um endereço pelo ID.
     *
     * @param id ID do endereço a ser removido
     */
    void deleteById(Integer id);

    /**
     * Verifica se existe um endereço com o ID informado.
     *
     * @param id ID do endereço
     * @return true se existir, false caso contrário
     */
    boolean existsById(Integer id);
}
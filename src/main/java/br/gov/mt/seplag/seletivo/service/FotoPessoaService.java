package br.gov.mt.seplag.seletivo.service;

import br.gov.mt.seplag.seletivo.model.entities.FotoPessoa;
import br.gov.mt.seplag.seletivo.model.entities.Pessoa;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * Interface de serviço para operações relacionadas à entidade FotoPessoa.
 */
public interface FotoPessoaService {

    /**
     * Busca todas as fotos cadastradas.
     *
     * @return Lista de todas as fotos
     */
    List<FotoPessoa> findAll();

    /**
     * Busca uma foto pelo ID.
     *
     * @param id ID da foto
     * @return Optional contendo a foto, se encontrada
     */
    Optional<FotoPessoa> findById(Integer id);

    /**
     * Busca todas as fotos de uma pessoa.
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

    /**
     * Salva uma nova foto ou atualiza uma existente.
     *
     * @param fotoPessoa Objeto fotoPessoa a ser salvo
     * @return FotoPessoa salva
     */
    FotoPessoa save(FotoPessoa fotoPessoa);

    /**
     * Faz upload de uma foto para uma pessoa.
     *
     * @param pessoaId ID da pessoa
     * @param file Arquivo da foto
     * @return FotoPessoa salva
     */
    FotoPessoa uploadFoto(Integer pessoaId, MultipartFile file);

    /**
     * Remove uma foto pelo ID.
     *
     * @param id ID da foto a ser removida
     */
    void deleteById(Integer id);

    /**
     * Verifica se existe uma foto com o ID informado.
     *
     * @param id ID da foto
     * @return true se existir, false caso contrário
     */
    boolean existsById(Integer id);
}
package br.gov.mt.seplag.seletivo.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Interface para serviço de armazenamento de arquivos.
 */
public interface FileStorageService {

    /**
     * Faz upload de um arquivo para o armazenamento.
     *
     * @param file Arquivo a ser enviado
     * @param objectName Nome do objeto no armazenamento
     * @return URL do arquivo armazenado
     */
    String uploadFile(MultipartFile file, String objectName);

    /**
     * Remove um arquivo do armazenamento.
     *
     * @param objectName Nome do objeto no armazenamento
     */
    void deleteFile(String objectName);
}
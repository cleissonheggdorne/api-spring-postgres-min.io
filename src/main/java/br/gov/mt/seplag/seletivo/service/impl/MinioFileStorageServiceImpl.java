package br.gov.mt.seplag.seletivo.service.impl;

import br.gov.mt.seplag.seletivo.service.FileStorageService;
import io.minio.*;
import io.minio.http.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

@Service
public class MinioFileStorageServiceImpl implements FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(MinioFileStorageServiceImpl.class);
    
    private final MinioClient minioClient;
    
    @Value("${minio.bucketName}")
    private String bucketName;

    @Autowired
    public MinioFileStorageServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public String uploadFile(MultipartFile file, String objectName) {
        try {
            // Verifica se o bucket existe, se não, cria
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                logger.info("Bucket '{}' criado com sucesso", bucketName);
            }

            // Faz upload do arquivo
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
            
            logger.info("Arquivo '{}' enviado com sucesso para o bucket '{}'", objectName, bucketName);
            
            // Retorna a URL do objeto
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .method(Method.GET)
                            .build());
        } catch (Exception e) {
            logger.error("Erro ao enviar arquivo para o Min.io", e);
            throw new RuntimeException("Não foi possível armazenar o arquivo", e);
        }
    }
    @Override
    public void deleteFile(String objectName) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build());
            logger.info("Arquivo '{}' removido com sucesso do bucket '{}'", objectName, bucketName);
        } catch (Exception e) {
            logger.error("Erro ao remover arquivo do Min.io", e);
            throw new RuntimeException("Não foi possível remover o arquivo", e);
        }
    }

    public String generateTemporaryUrl(String objectName, int expirationMinutes) {
        try {
            return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName)
                    .object(objectName)
                    .expiry(expirationMinutes, TimeUnit.MINUTES)
                    .build()
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar URL temporária", e);
        }
    }
}
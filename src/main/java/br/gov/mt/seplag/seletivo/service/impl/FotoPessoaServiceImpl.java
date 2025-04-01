package br.gov.mt.seplag.seletivo.service.impl;

import br.gov.mt.seplag.seletivo.model.entities.FotoPessoa;
import br.gov.mt.seplag.seletivo.model.entities.Pessoa;
import br.gov.mt.seplag.seletivo.repository.FotoPessoaRepository;
import br.gov.mt.seplag.seletivo.repository.PessoaRepository;
import br.gov.mt.seplag.seletivo.service.FotoPessoaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementação do serviço para operações relacionadas à entidade FotoPessoa.
 */
@Service
public class FotoPessoaServiceImpl implements FotoPessoaService {

    private static final Logger logger = LoggerFactory.getLogger(FotoPessoaServiceImpl.class);

    private final FotoPessoaRepository fotoPessoaRepository;
    private final PessoaRepository pessoaRepository;
    private final MinioFileStorageServiceImpl fileStorageService;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Autowired
    public FotoPessoaServiceImpl(
            FotoPessoaRepository fotoPessoaRepository,
            PessoaRepository pessoaRepository,
            MinioFileStorageServiceImpl fileStorageService) {
        this.fotoPessoaRepository = fotoPessoaRepository;
        this.pessoaRepository = pessoaRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public List<FotoPessoa> findAll() {
        return fotoPessoaRepository.findAll();
    }

    @Override
    public Optional<FotoPessoa> findById(Integer id) {
        return fotoPessoaRepository.findById(id);
    }

    @Override
    public List<FotoPessoa> findByPessoa(Pessoa pessoa) {
        return fotoPessoaRepository.findByPessoa(pessoa);
    }

    @Override
    public List<FotoPessoa> findByPessoaId(Integer pessoaId) {
        return fotoPessoaRepository.findByPessoaId(pessoaId);
    }

    @Override
    @Transactional
    public FotoPessoa save(FotoPessoa fotoPessoa) {
        return fotoPessoaRepository.save(fotoPessoa);
    }

    @Override
    @Transactional
    public FotoPessoa uploadFoto(Integer pessoaId, MultipartFile file) {
        // Busca a pessoa pelo ID
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com o ID: " + pessoaId));

        try {
            // Gera um hash único para o arquivo
            String hash = UUID.randomUUID().toString();
            
            // Define o nome do objeto no Min.io
            String objectName = "pessoa/" + pessoaId + "/" + hash;
            
            // Faz upload do arquivo para o Min.io
            fileStorageService.uploadFile(file, objectName);
            
            // Cria e salva a entidade FotoPessoa
            FotoPessoa fotoPessoa = new FotoPessoa();
            fotoPessoa.setPessoa(pessoa);
            fotoPessoa.setData(LocalDate.now());
            fotoPessoa.setBucket(bucketName);
            fotoPessoa.setHash(hash);
            
            return fotoPessoaRepository.save(fotoPessoa);
        } catch (Exception e) {
            logger.error("Erro ao fazer upload da foto para a pessoa com ID: {}", pessoaId, e);
            throw new RuntimeException("Não foi possível fazer upload da foto", e);
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        // Busca a foto pelo ID
        FotoPessoa fotoPessoa = fotoPessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Foto não encontrada com o ID: " + id));
        
        try {
            // Define o nome do objeto no Min.io
            String objectName = "pessoa/" + fotoPessoa.getPessoa().getId() + "/" + fotoPessoa.getHash();
            
            // Remove o arquivo do Min.io
            fileStorageService.deleteFile(objectName);
            
            // Remove a entidade FotoPessoa
            fotoPessoaRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Erro ao remover a foto com ID: {}", id, e);
            throw new RuntimeException("Não foi possível remover a foto", e);
        }
    }

    @Override
    public boolean existsById(Integer id) {
        return fotoPessoaRepository.existsById(id);
    }
}
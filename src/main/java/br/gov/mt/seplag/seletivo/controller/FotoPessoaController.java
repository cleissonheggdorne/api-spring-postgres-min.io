package br.gov.mt.seplag.seletivo.controller;

import br.gov.mt.seplag.seletivo.model.entities.FotoPessoa;
import br.gov.mt.seplag.seletivo.service.FotoPessoaService;
import br.gov.mt.seplag.seletivo.service.impl.MinioFileStorageServiceImpl;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/fotos")
public class FotoPessoaController {

    private final FotoPessoaService fotoPessoaService;
    private final MinioFileStorageServiceImpl fileStorageService;

    @Autowired
    public FotoPessoaController(FotoPessoaService fotoPessoaService, MinioFileStorageServiceImpl fileStorageService) {
        this.fotoPessoaService = fotoPessoaService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping
    public ResponseEntity<List<FotoPessoa>> getAllFotos() {
        return ResponseEntity.ok(fotoPessoaService.findAll());
    }

    @Operation(summary = "Upload de foto de uma pessoa")
    @PostMapping(value="/upload/{pessoaId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FotoPessoa> uploadFoto(@PathVariable Integer pessoaId, @RequestParam("file")  MultipartFile file) {
        FotoPessoa fotoPessoa = fotoPessoaService.uploadFoto(pessoaId, file);
        return ResponseEntity.ok(fotoPessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoto(@PathVariable Integer id) {
        fotoPessoaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/url/{id}")
    @Operation(summary = "Gera uma URL temporária para acessar a foto de uma pessoa")
    public ResponseEntity<String> getTemporaryUrl(@PathVariable Integer idPessoa) {
        List<FotoPessoa> fotos = fotoPessoaService.findByPessoaId(idPessoa);
        return fotos.stream()
            .map(foto -> {
                String objectName = "pessoa/" + foto.getPessoa().getId() + "/" + foto.getHash();
                String url = fileStorageService.generateTemporaryUrl(objectName, 5); // 5 minutes
                return ResponseEntity.ok(url);
            })
            .findFirst()
            .orElse(ResponseEntity.notFound().build());
    }
}
package br.gov.mt.seplag.seletivo.controller;

import br.gov.mt.seplag.seletivo.model.entities.Cidade;
import br.gov.mt.seplag.seletivo.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para operações relacionadas à entidade Cidade.
 */
@RestController
@RequestMapping("/api/cidades")
public class CidadeController {

    private final CidadeService cidadeService;

    @Autowired
    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public ResponseEntity<List<Cidade>> getAllCidades() {
        return ResponseEntity.ok(cidadeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> getCidadeById(@PathVariable Integer id) {
        return cidadeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/busca")
    public ResponseEntity<List<Cidade>> getCidadesByNome(@RequestParam String nome) {
        return ResponseEntity.ok(cidadeService.findByNomeContaining(nome));
    }

    @GetMapping("/uf/{uf}")
    public ResponseEntity<List<Cidade>> getCidadesByUf(@PathVariable String uf) {
        return ResponseEntity.ok(cidadeService.findByUf(uf));
    }

    @GetMapping("/busca-avancada")
    public ResponseEntity<List<Cidade>> getCidadesByNomeAndUf(
            @RequestParam String nome,
            @RequestParam String uf) {
        return ResponseEntity.ok(cidadeService.findByNomeContainingAndUf(nome, uf));
    }

    @PostMapping
    public ResponseEntity<Cidade> createCidade(@RequestBody Cidade cidade) {
        Cidade novaCidade = cidadeService.save(cidade);
        return ResponseEntity.ok(novaCidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cidade> updateCidade(
            @PathVariable Integer id, 
            @RequestBody Cidade cidade) {
        
        if (!cidadeService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        cidade.setId(id);
        Cidade cidadeAtualizada = cidadeService.save(cidade);
        
        return ResponseEntity.ok(cidadeAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCidade(@PathVariable Integer id) {
        if (!cidadeService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        cidadeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
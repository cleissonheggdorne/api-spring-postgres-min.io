package br.gov.mt.seplag.seletivo.controller;

import br.gov.mt.seplag.seletivo.model.entities.Pessoa;
import br.gov.mt.seplag.seletivo.service.impl.PessoaServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaServiceImpl pessoaServiceImpl;

    @Autowired
    public PessoaController(PessoaServiceImpl pessoaServiceImpl) {
        this.pessoaServiceImpl = pessoaServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoas() {
        return ResponseEntity.ok(pessoaServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Integer id) {
        return pessoaServiceImpl.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/busca")
    public ResponseEntity<List<Pessoa>> getPessoasByNome(@RequestParam String nome) {
        return ResponseEntity.ok(pessoaServiceImpl.findByNomeContaining(nome));
    }

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = pessoaServiceImpl.save(pessoa);
        return ResponseEntity.ok(novaPessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(
            @PathVariable Integer id, 
            @RequestBody Pessoa pessoa) {
        
        if (!pessoaServiceImpl.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        pessoa.setId(id);
        Pessoa pessoaAtualizada = pessoaServiceImpl.save(pessoa);
        
        return ResponseEntity.ok(pessoaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Integer id) {
        if (!pessoaServiceImpl.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        pessoaServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
package br.gov.mt.seplag.seletivo.controller;

import br.gov.mt.seplag.seletivo.model.entities.Endereco;
import br.gov.mt.seplag.seletivo.service.impl.EnderecoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para operações relacionadas à entidade Endereco.
 */
@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    private final EnderecoServiceImpl enderecoService;

    @Autowired
    public EnderecoController(EnderecoServiceImpl enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> getAllEnderecos() {
        return ResponseEntity.ok(enderecoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> getEnderecoById(@PathVariable Integer id) {
        return enderecoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Endereco> createEndereco(@RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoService.save(endereco);
        return ResponseEntity.ok(novoEndereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> updateEndereco(
            @PathVariable Integer id, 
            @RequestBody Endereco endereco) {
        
        if (!enderecoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        endereco.setId(id);
        Endereco enderecoAtualizado = enderecoService.save(endereco);
        
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Integer id) {
        if (!enderecoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        enderecoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/por-servidor/")
    public ResponseEntity<List<Endereco>> getEnderecoUnidadePorServidor(@RequestParam String nomeServidor) {
        return ResponseEntity.ok(enderecoService.findEnderecoUnidadePorNomeServidor(nomeServidor));
    }
}
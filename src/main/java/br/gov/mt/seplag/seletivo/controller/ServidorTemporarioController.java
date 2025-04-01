package br.gov.mt.seplag.seletivo.controller;

import br.gov.mt.seplag.seletivo.controller.dto.ServidorTemporarioRecordDTO;
import br.gov.mt.seplag.seletivo.model.entities.ServidorTemporario;
import br.gov.mt.seplag.seletivo.service.impl.ServidorTemporarioServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servidores-temporarios")
public class ServidorTemporarioController {

    private final ServidorTemporarioServiceImpl servidorTemporarioService;

    @Autowired
    public ServidorTemporarioController(ServidorTemporarioServiceImpl servidorTemporarioService) {
        this.servidorTemporarioService = servidorTemporarioService;
    }

    @GetMapping
    public ResponseEntity<List<ServidorTemporario>> getAllServidoresTemporarios() {
        return ResponseEntity.ok(servidorTemporarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServidorTemporario> getServidorTemporarioById(@PathVariable Integer id) {
        return servidorTemporarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ServidorTemporario> createServidorTemporario(@RequestBody ServidorTemporarioRecordDTO servidorTemporarioDto) {
        ServidorTemporario novoServidorTemporario = servidorTemporarioService.save(servidorTemporarioDto);
        return ResponseEntity.ok(novoServidorTemporario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServidorTemporario> updateServidorTemporario(
            @RequestBody ServidorTemporarioRecordDTO servidorTemporarioDto) {
        
        ServidorTemporario servidorTemporarioAtualizado = servidorTemporarioService.update(servidorTemporarioDto);
        
        return ResponseEntity.ok(servidorTemporarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServidorTemporario(@PathVariable Integer id) {
        if (!servidorTemporarioService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        servidorTemporarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
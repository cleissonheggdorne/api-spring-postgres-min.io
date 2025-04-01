package br.gov.mt.seplag.seletivo.controller;

import br.gov.mt.seplag.seletivo.controller.dto.ServidorEfetivoRecordDTO;
import br.gov.mt.seplag.seletivo.controller.dto.projection.ServidorEfetivoInfo;
import br.gov.mt.seplag.seletivo.model.entities.ServidorEfetivo;
import br.gov.mt.seplag.seletivo.service.impl.ServidorEfetivoServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para operações relacionadas à entidade ServidorEfetivo.
 */
@RestController
@RequestMapping("/api/servidores-efetivos")
public class ServidorEfetivoController {

    private final ServidorEfetivoServiceImpl servidorEfetivoService;

    public ServidorEfetivoController(ServidorEfetivoServiceImpl servidorEfetivoService) {
        this.servidorEfetivoService = servidorEfetivoService;
    }

    @GetMapping
    public ResponseEntity<List<ServidorEfetivo>> getAllServidoresEfetivos() {
        return ResponseEntity.ok(servidorEfetivoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServidorEfetivo> getServidorEfetivoById(@PathVariable Integer id) {
        return servidorEfetivoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<ServidorEfetivo> getServidorEfetivoByMatricula(@PathVariable String matricula) {
        return servidorEfetivoService.findByMatricula(matricula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/busca")
    public ResponseEntity<List<ServidorEfetivo>> getServidoresEfetivosByNome(@RequestParam String nome) {
        return ResponseEntity.ok(servidorEfetivoService.findByNomeContaining(nome));
    }

    @GetMapping("/busca-por-unidade/")
    public ResponseEntity<List<ServidorEfetivoInfo>> getServidoresEfetivosByNomeUnidade(@PathVariable Integer idUnidade) {
        return ResponseEntity.ok(servidorEfetivoService.findServidoresLotadosPorUnidade(idUnidade));
    }

    @PostMapping
    public ResponseEntity<ServidorEfetivo> createServidorEfetivo(@RequestBody ServidorEfetivoRecordDTO servidorEfetivoDto) {
        ServidorEfetivo novoServidorEfetivo = servidorEfetivoService.save(servidorEfetivoDto);
        return ResponseEntity.ok(novoServidorEfetivo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServidorEfetivo> updateServidorEfetivo( 
            @RequestBody ServidorEfetivoRecordDTO servidorEfetivoDto) {
        ServidorEfetivo servidorEfetivoAtualizado = servidorEfetivoService.update(servidorEfetivoDto);

        return ResponseEntity.ok(servidorEfetivoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServidorEfetivo(@PathVariable Integer id) {
        if (!servidorEfetivoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        servidorEfetivoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
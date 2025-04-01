package br.gov.mt.seplag.seletivo.controller;

import br.gov.mt.seplag.seletivo.model.entities.Unidade;
import br.gov.mt.seplag.seletivo.service.UnidadeService;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para operações relacionadas à entidade Unidade.
 */
@RestController
@RequestMapping("/api/unidades")
public class UnidadeController {

    private final UnidadeService unidadeService;

    @Autowired
    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    /**
     * Retorna todas as unidades.
     *
     * @return Lista de unidades
     */
    @GetMapping
    public ResponseEntity<List<Unidade>> findAll() {
        return ResponseEntity.ok(unidadeService.findAll());
    }

    /**
     * Busca uma unidade pelo ID.
     *
     * @param id ID da unidade
     * @return Unidade encontrada ou status 404 se não existir
     */
    @GetMapping("/{id}")
    public ResponseEntity<Unidade> findById(@PathVariable Integer id) {
        return unidadeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Busca unidades pelo nome.
     *
     * @param nome Nome ou parte do nome da unidade
     * @return Lista de unidades que contêm o nome informado
     */
    @GetMapping("/search/nome")
    public ResponseEntity<List<Unidade>> findByNome(@RequestParam String nome) {
        return ResponseEntity.ok(unidadeService.findByNomeContaining(nome));
    }

    /**
     * Cria uma nova unidade.
     *
     * @param unidade Dados da unidade a ser criada
     * @return Unidade criada com status 201
     */
    @PostMapping
    @Operation(summary = "Cria uma nova unidade")
    public ResponseEntity<Unidade> create(@RequestBody Unidade unidade) {
        Unidade savedUnidade = unidadeService.save(unidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUnidade);
    }

    /**
     * Atualiza uma unidade existente.
     *
     * @param id ID da unidade a ser atualizada
     * @param unidade Novos dados da unidade
     * @return Unidade atualizada ou status 404 se não existir
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma unidade existente")
    public ResponseEntity<Unidade> update(@PathVariable Integer id, @RequestBody Unidade unidade) {
        if (!unidadeService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        unidade.setId(id);
        return ResponseEntity.ok(unidadeService.save(unidade));
    }

    /**
     * Remove uma unidade pelo ID.
     *
     * @param id ID da unidade a ser removida
     * @return Status 204 se removida com sucesso ou 404 se não existir
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!unidadeService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        unidadeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
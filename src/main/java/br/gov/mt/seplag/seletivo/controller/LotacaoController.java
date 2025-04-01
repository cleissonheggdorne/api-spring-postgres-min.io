package br.gov.mt.seplag.seletivo.controller;

import br.gov.mt.seplag.seletivo.controller.dto.LotacaoRecordDTO;
import br.gov.mt.seplag.seletivo.model.entities.Lotacao;
import br.gov.mt.seplag.seletivo.service.impl.LotacaoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para operações relacionadas à entidade Lotacao.
 */
@RestController
@RequestMapping("/api/lotacoes")
public class LotacaoController {

    private final LotacaoServiceImpl lotacaoService;

    @Autowired
    public LotacaoController(LotacaoServiceImpl lotacaoService) {
        this.lotacaoService = lotacaoService;
    }

    /**
     * Retorna todas as lotações.
     *
     * @return Lista de lotações
     */
    @GetMapping
    public ResponseEntity<List<Lotacao>> findAll() {
        return ResponseEntity.ok(lotacaoService.findAll());
    }

    /**
     * Busca uma lotação pelo ID.
     *
     * @param id ID da lotação
     * @return Lotação encontrada ou status 404 se não existir
     */
    @GetMapping("/{id}")
    public ResponseEntity<Lotacao> findById(@PathVariable Integer id) {
        return lotacaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Cria uma nova lotação.
     *
     * @param lotacao Dados da lotação a ser criada
     * @return Lotação criada com status 201
     */
    @PostMapping
    public ResponseEntity<Lotacao> create(@RequestBody LotacaoRecordDTO lotacaoDto) {
        Lotacao savedLotacao = lotacaoService.save(lotacaoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLotacao);
    }

    /**
     * Atualiza uma lotação existente.
     *
     * @param id ID da lotação a ser atualizada
     * @param lotacao Novos dados da lotação
     * @return Lotação atualizada ou status 404 se não existir
     */
    @PutMapping("/{id}")
    public ResponseEntity<Lotacao> update(@RequestBody LotacaoRecordDTO lotacao) {
        return ResponseEntity.ok(lotacaoService.update(lotacao));
    }

    /**
     * Remove uma lotação pelo ID.
     *
     * @param id ID da lotação a ser removida
     * @return Status 204 se removida com sucesso ou 404 se não existir
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!lotacaoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        lotacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
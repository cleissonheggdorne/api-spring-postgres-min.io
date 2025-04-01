package br.gov.mt.seplag.seletivo.service.impl;

import br.gov.mt.seplag.seletivo.model.entities.Unidade;
import br.gov.mt.seplag.seletivo.repository.UnidadeRepository;
import br.gov.mt.seplag.seletivo.service.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço para operações relacionadas à entidade Unidade.
 */
@Service
public class UnidadeServiceImpl implements UnidadeService {

    private final UnidadeRepository unidadeRepository;

    @Autowired
    public UnidadeServiceImpl(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    @Override
    public List<Unidade> findAll() {
        return unidadeRepository.findAll();
    }

    @Override
    public Optional<Unidade> findById(Integer id) {
        return unidadeRepository.findById(id);
    }

    @Override
    public List<Unidade> findByNomeContaining(String nome) {
        return unidadeRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Override
    public List<Unidade> findBySigla(String sigla) {
        return unidadeRepository.findBySiglaIgnoreCase(sigla);
    }

    @Override
    @Transactional
    public Unidade save(Unidade unidade) {
        return unidadeRepository.save(unidade);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        unidadeRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return unidadeRepository.existsById(id);
    }
}
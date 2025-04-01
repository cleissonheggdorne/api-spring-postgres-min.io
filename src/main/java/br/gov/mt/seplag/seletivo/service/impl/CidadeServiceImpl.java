package br.gov.mt.seplag.seletivo.service.impl;

import br.gov.mt.seplag.seletivo.model.entities.Cidade;
import br.gov.mt.seplag.seletivo.repository.CidadeRepository;
import br.gov.mt.seplag.seletivo.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço para operações relacionadas à entidade Cidade.
 */
@Service
public class CidadeServiceImpl implements CidadeService {

    private final CidadeRepository cidadeRepository;

    @Autowired
    public CidadeServiceImpl(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @Override
    public List<Cidade> findAll() {
        return cidadeRepository.findAll();
    }

    @Override
    public Optional<Cidade> findById(Integer id) {
        return cidadeRepository.findById(id);
    }

    @Override
    public List<Cidade> findByNomeContaining(String nome) {
        return cidadeRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Override
    public List<Cidade> findByUf(String uf) {
        return cidadeRepository.findByUfIgnoreCase(uf);
    }

    @Override
    public List<Cidade> findByNomeContainingAndUf(String nome, String uf) {
        return cidadeRepository.findByNomeContainingIgnoreCaseAndUfIgnoreCase(nome, uf);
    }

    @Override
    @Transactional
    public Cidade save(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        cidadeRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return cidadeRepository.existsById(id);
    }
}
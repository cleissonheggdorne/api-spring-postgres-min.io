package br.gov.mt.seplag.seletivo.service.impl;

import br.gov.mt.seplag.seletivo.model.entities.Pessoa;
import br.gov.mt.seplag.seletivo.repository.PessoaRepository;
import br.gov.mt.seplag.seletivo.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço para operações relacionadas à entidade Pessoa.
 */
@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    @Override
    public Optional<Pessoa> findById(Integer id) {
        return pessoaRepository.findById(id);
    }

    @Override
    @Transactional
    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        pessoaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return pessoaRepository.existsById(id);
    }

    public List<Pessoa> findByNomeContaining(String nome) {
        return pessoaRepository.findByNomeContainingIgnoreCase(nome);
    }
}
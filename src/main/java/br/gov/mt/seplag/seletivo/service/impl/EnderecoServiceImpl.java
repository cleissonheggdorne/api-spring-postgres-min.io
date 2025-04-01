package br.gov.mt.seplag.seletivo.service.impl;

import br.gov.mt.seplag.seletivo.model.entities.Endereco;
import br.gov.mt.seplag.seletivo.repository.EnderecoRepository;
import br.gov.mt.seplag.seletivo.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço para operações relacionadas à entidade Endereco.
 */
@Service
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoServiceImpl(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    @Override
    public Optional<Endereco> findById(Integer id) {
        return enderecoRepository.findById(id);
    }

    @Override
    @Transactional
    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        enderecoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return enderecoRepository.existsById(id);
    }

    public List<Endereco> findEnderecoUnidadePorNomeServidor(String nome){
        return enderecoRepository.findEnderecoUnidadePorNomeServidor(nome);
    }
}
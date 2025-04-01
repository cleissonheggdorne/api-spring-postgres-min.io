package br.gov.mt.seplag.seletivo.service.impl;

import br.gov.mt.seplag.seletivo.controller.dto.ServidorTemporarioRecordDTO;
import br.gov.mt.seplag.seletivo.model.entities.Pessoa;
import br.gov.mt.seplag.seletivo.model.entities.ServidorTemporario;
import br.gov.mt.seplag.seletivo.repository.ServidorTemporarioRepository;
import br.gov.mt.seplag.seletivo.service.PessoaService;
import br.gov.mt.seplag.seletivo.service.ServidorTemporarioService;
import br.gov.mt.seplag.seletivo.tools.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço para operações relacionadas à entidade ServidorTemporario.
 */
@Service
public class ServidorTemporarioServiceImpl implements ServidorTemporarioService {

    private final ServidorTemporarioRepository servidorTemporarioRepository;
    private final PessoaService pessoaService;

    @Autowired
    public ServidorTemporarioServiceImpl(ServidorTemporarioRepository servidorTemporarioRepository,PessoaService pessoaService) {
        this.servidorTemporarioRepository = servidorTemporarioRepository;
        this.pessoaService = pessoaService;
    }

    @Override
    public List<ServidorTemporario> findAll() {
        return servidorTemporarioRepository.findAll();
    }

    @Override
    public Optional<ServidorTemporario> findById(Integer id) {
        return servidorTemporarioRepository.findById(id);
    }

    @Override
    @Transactional
    public ServidorTemporario save(ServidorTemporario servidorTemporario) {
        return servidorTemporarioRepository.save(servidorTemporario);
    }

    @Transactional
    public ServidorTemporario save(ServidorTemporarioRecordDTO servidorTemporarioDto) {       
        return pessoaService.findById(servidorTemporarioDto.idPessoa())
        .map(
            pessoa -> {
                ServidorTemporario servidorTemporario = new ServidorTemporario();
                servidorTemporario.setPessoa(pessoa);
                servidorTemporario.setDataAdmissao(servidorTemporarioDto.dataAdmissao());
                servidorTemporario.setDataDemissao(servidorTemporarioDto.dataDemissao());
                return save(servidorTemporario);
            })
            .orElseThrow(() -> 
                new CustomException("Pessoa não encontrada com o ID: " + servidorTemporarioDto.idPessoa()));
            
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        servidorTemporarioRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return servidorTemporarioRepository.existsById(id);
    }

     public ServidorTemporario update(ServidorTemporarioRecordDTO servidorTemporarioDto) {
        return servidorTemporarioRepository.findById(servidorTemporarioDto.id())
        .map(
            servidor -> {
                Optional<Pessoa> pessoa = pessoaService.findById(servidorTemporarioDto.idPessoa());
                if(pessoa.isEmpty()) {
                    throw new CustomException("Pessoa não encontrada com o ID: " + servidorTemporarioDto.idPessoa());
                }
                servidor.setPessoa(pessoa.get());
                servidor.setDataAdmissao(servidorTemporarioDto.dataAdmissao());
                servidor.setDataDemissao(servidorTemporarioDto.dataDemissao());
                return save(servidor);
            })
            .orElseThrow(() -> 
                new CustomException("Servidor Temporário não encontrado com o ID: " + servidorTemporarioDto.id()));
    }
}
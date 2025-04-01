package br.gov.mt.seplag.seletivo.service.impl;

import br.gov.mt.seplag.seletivo.controller.dto.ServidorEfetivoRecordDTO;
import br.gov.mt.seplag.seletivo.controller.dto.projection.ServidorEfetivoInfo;
import br.gov.mt.seplag.seletivo.model.entities.Pessoa;
import br.gov.mt.seplag.seletivo.model.entities.ServidorEfetivo;
import br.gov.mt.seplag.seletivo.repository.ServidorEfetivoRepository;
import br.gov.mt.seplag.seletivo.service.PessoaService;
import br.gov.mt.seplag.seletivo.service.ServidorEfetivoService;
import br.gov.mt.seplag.seletivo.tools.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço para operações relacionadas à entidade ServidorEfetivo.
 */
@Service
public class ServidorEfetivoServiceImpl implements ServidorEfetivoService {

    private final ServidorEfetivoRepository servidorEfetivoRepository;
    private final PessoaService pessoaService;

    @Autowired
    public ServidorEfetivoServiceImpl(ServidorEfetivoRepository servidorEfetivoRepository,PessoaService pessoaService) {
        this.servidorEfetivoRepository = servidorEfetivoRepository;
        this.pessoaService = pessoaService;
    }

    @Override
    public List<ServidorEfetivo> findAll() {
        return servidorEfetivoRepository.findAll();
    }

    @Override
    public Optional<ServidorEfetivo> findById(Integer id) {
        return servidorEfetivoRepository.findById(id);
    }

    @Override
    public Optional<ServidorEfetivo> findByMatricula(String matricula) {
        return servidorEfetivoRepository.findByMatricula(matricula);
    }

    @Override
    public List<ServidorEfetivo> findByNomeContaining(String nome) {
        return servidorEfetivoRepository.findByNomeContaining(nome);
    }

    @Override
    @Transactional
    public ServidorEfetivo save(ServidorEfetivo servidorEfetivo) {
        return servidorEfetivoRepository.save(servidorEfetivo);
    }

    @Transactional
    public ServidorEfetivo save(ServidorEfetivoRecordDTO servidorEfetivoDto) {
        servidorEfetivoRepository.findByMatricula(servidorEfetivoDto.matricula())
        .ifPresent(servidor -> { 
            throw new CustomException("Já existe servidor com a matrícula: " + servidor.getMatricula());
        });
        
        return pessoaService.findById(servidorEfetivoDto.idPessoa())
        .map(
            pessoa -> {
                ServidorEfetivo servidorEfetivo = new ServidorEfetivo();
                servidorEfetivo.setPessoa(pessoa);
                servidorEfetivo.setMatricula(servidorEfetivoDto.matricula());
                return save(servidorEfetivo);
            })
            .orElseThrow(() -> 
                new CustomException("Pessoa não encontrada com o ID: " + servidorEfetivoDto.idPessoa()));
            
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        servidorEfetivoRepository.deleteById(id);
    }

    public ServidorEfetivo update(ServidorEfetivoRecordDTO servidorEfetivoDto) {
        return servidorEfetivoRepository.findById(servidorEfetivoDto.id())
        .map(
            servidor -> {
                Optional<Pessoa> pessoa = pessoaService.findById(servidorEfetivoDto.idPessoa());
                if(pessoa.isEmpty()) {
                    throw new CustomException("Pessoa não encontrada com o ID: " + servidorEfetivoDto.idPessoa());
                }
                servidor.setPessoa(pessoa.get());
                servidor.setMatricula(servidorEfetivoDto.matricula());
                return save(servidor);
            })
            .orElseThrow(() -> 
                new CustomException("Servidor não encontrado com o ID: " + servidorEfetivoDto.id()));
    }

    public List<ServidorEfetivoInfo> findServidoresLotadosPorUnidade(Integer idUnidade){
        return servidorEfetivoRepository.findServidoresLotadosPorUnidade(idUnidade);
    }

    @Override
    public boolean existsById(Integer id) {
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    } 
}
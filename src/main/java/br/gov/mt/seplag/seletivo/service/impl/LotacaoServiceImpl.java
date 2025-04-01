package br.gov.mt.seplag.seletivo.service.impl;

import br.gov.mt.seplag.seletivo.controller.dto.LotacaoRecordDTO;
import br.gov.mt.seplag.seletivo.model.entities.Lotacao;
import br.gov.mt.seplag.seletivo.model.entities.Pessoa;
import br.gov.mt.seplag.seletivo.model.entities.Unidade;
import br.gov.mt.seplag.seletivo.repository.LotacaoRepository;
import br.gov.mt.seplag.seletivo.service.LotacaoService;
import br.gov.mt.seplag.seletivo.tools.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço para operações relacionadas à entidade Lotacao.
 */
@Service
public class LotacaoServiceImpl implements LotacaoService {

    private final LotacaoRepository lotacaoRepository;
    private final PessoaServiceImpl pessoaService;
    private final UnidadeServiceImpl unidadeService;

    @Autowired
    public LotacaoServiceImpl(LotacaoRepository lotacaoRepository, PessoaServiceImpl pessoaService, UnidadeServiceImpl unidadeService) {
        this.lotacaoRepository = lotacaoRepository;
        this.pessoaService = pessoaService;
        this.unidadeService = unidadeService;
    }

    @Override
    public List<Lotacao> findAll() {
        return lotacaoRepository.findAll();
    }

    @Override
    public Optional<Lotacao> findById(Integer id) {
        return lotacaoRepository.findById(id);
    }

    @Override
    public List<Lotacao> findByPessoa(Pessoa pessoa) {
        return lotacaoRepository.findByPessoa(pessoa);
    }

    @Override
    public List<Lotacao> findByPessoaId(Integer pessoaId) {
        return lotacaoRepository.findByPessoaId(pessoaId);
    }

    @Override
    public List<Lotacao> findByUnidade(Unidade unidade) {
        return lotacaoRepository.findByUnidade(unidade);
    }

    @Override
    public List<Lotacao> findByUnidadeId(Integer unidadeId) {
        return lotacaoRepository.findByUnidadeId(unidadeId);
    }

    @Override
    public Optional<Lotacao> findLotacaoAtual(Pessoa pessoa, LocalDate data) {
        return lotacaoRepository.findLotacaoAtual(pessoa, data);
    }

    @Override
    public Optional<Lotacao> findLotacaoAtualByPessoaId(Integer pessoaId, LocalDate data) {
        return lotacaoRepository.findLotacaoAtualByPessoaId(pessoaId, data);
    }

    @Override
    public List<Lotacao> findLotacoesAtivas(LocalDate data) {
        return lotacaoRepository.findLotacoesAtivas(data);
    }

    @Override
    @Transactional
    public Lotacao save(Lotacao lotacao) {
        return lotacaoRepository.save(lotacao);
    }

    @Transactional
    public Lotacao save(LotacaoRecordDTO lotacaoDto) {
        Optional<Pessoa> pessoa = pessoaService.findById(lotacaoDto.idPessoa());
                if(pessoa.isEmpty()) {
                    throw new CustomException("Pessoa não encontrada com o ID: " + lotacaoDto.idPessoa());
                }
        Optional<Unidade> unidade = unidadeService.findById(lotacaoDto.idUnidade());
        if(unidade.isEmpty())  {
            throw new CustomException("Unidade não encontrada com o ID: " + lotacaoDto.idUnidade());
        }
        Lotacao lotacao = new Lotacao();
        lotacao.setDataLotacao(lotacaoDto.dataLotacao());
        lotacao.setDataRemocao(lotacaoDto.dataRemocao());
        lotacao.setPessoa(pessoa.get());
        lotacao.setUnidade(unidade.get());
        lotacao.setPortaria(lotacaoDto.portaria());
        return save(lotacao);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        lotacaoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return lotacaoRepository.existsById(id);
    }

    @Transactional
    public Lotacao update(LotacaoRecordDTO lotacaoDto) {
       return lotacaoRepository.findById(lotacaoDto.id())
        .map(
            lotacao -> {
                Optional<Pessoa> pessoa = pessoaService.findById(lotacaoDto.idPessoa());
                if(pessoa.isEmpty()) {
                    throw new CustomException("Pessoa não encontrada com o ID: " + lotacaoDto.idPessoa());
                }
                Optional<Unidade> unidade = unidadeService.findById(lotacaoDto.idUnidade());
                if(unidade.isEmpty())  {
                    throw new CustomException("Unidade não encontrada com o ID: " + lotacaoDto.idUnidade());
                }
                lotacao.setUnidade(unidade.get());
                lotacao.setPessoa(pessoa.get());
                lotacao.setDataLotacao(lotacaoDto.dataLotacao());
                lotacao.setDataRemocao(lotacaoDto.dataRemocao());
                lotacao.setPortaria(lotacaoDto.portaria());
                return save(lotacao);
                
            })
            .orElseThrow(() -> 
                // Ação quando lotacao não existe
                new CustomException("Lotação não encontrada com o ID: " + lotacaoDto.id()));
        }
}
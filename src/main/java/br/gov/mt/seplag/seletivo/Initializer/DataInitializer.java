package br.gov.mt.seplag.seletivo.Initializer;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import br.gov.mt.seplag.seletivo.config.PasswordEncoderConfig;
import br.gov.mt.seplag.seletivo.model.entities.Cidade;
import br.gov.mt.seplag.seletivo.model.entities.Endereco;
import br.gov.mt.seplag.seletivo.model.entities.Lotacao;
import br.gov.mt.seplag.seletivo.model.entities.Pessoa;
import br.gov.mt.seplag.seletivo.model.entities.ServidorEfetivo;
import br.gov.mt.seplag.seletivo.model.entities.ServidorTemporario;
import br.gov.mt.seplag.seletivo.model.entities.Unidade;
import br.gov.mt.seplag.seletivo.model.entities.UnidadeEndereco;
import br.gov.mt.seplag.seletivo.model.entities.User;
import br.gov.mt.seplag.seletivo.repository.CidadeRepository;
import br.gov.mt.seplag.seletivo.repository.EnderecoRepository;
import br.gov.mt.seplag.seletivo.repository.LotacaoRepository;
import br.gov.mt.seplag.seletivo.repository.PessoaRepository;
import br.gov.mt.seplag.seletivo.repository.ServidorEfetivoRepository;
import br.gov.mt.seplag.seletivo.repository.ServidorTemporarioRepository;
import br.gov.mt.seplag.seletivo.repository.UnidadeEnderecoRepository;
import br.gov.mt.seplag.seletivo.repository.UnidadeRepository;
import br.gov.mt.seplag.seletivo.repository.UserRepository;
import jakarta.transaction.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {

    private  UserRepository userRepository; 
    private  PasswordEncoderConfig passwordEncoderConfig;
    private  PessoaRepository pessoaRepository;
    private  ServidorEfetivoRepository servidorEfetivoRepository;
    private  ServidorTemporarioRepository servidorTemporarioRepository;
    private UnidadeRepository unidadeRepository;
    private LotacaoRepository lotacaoRepository;
    private EnderecoRepository enderecoRepository;
    private CidadeRepository cidadeRepository;
    private UnidadeEnderecoRepository unidadeEnderecoRepository;

    public DataInitializer(UserRepository userRepository,
                           PasswordEncoderConfig passwordEncoderConfig,
                           PessoaRepository pessoaRepository,
                           ServidorEfetivoRepository servidorEfetivoRepository,
                           ServidorTemporarioRepository servidorTemporarioRepository,
                           UnidadeRepository unidadeRepository,
                           LotacaoRepository lotacaoRepository,
                           EnderecoRepository enderecoRepository,
                           CidadeRepository cidadeRepository,
                           UnidadeEnderecoRepository unidadeEnderecoRepository){
        this.userRepository = userRepository;
        this.passwordEncoderConfig = passwordEncoderConfig;
        this.pessoaRepository = pessoaRepository;
        this.servidorEfetivoRepository = servidorEfetivoRepository;
        this.servidorTemporarioRepository = servidorTemporarioRepository;
        this.unidadeRepository = unidadeRepository;
        this.lotacaoRepository = lotacaoRepository;
        this.enderecoRepository = enderecoRepository;
        this.cidadeRepository = cidadeRepository;
        this.unidadeEnderecoRepository = unidadeEnderecoRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        
        if (!userRepository.findById(1).isPresent()){        
            User user = new User();
            user.setActive(true);
            user.setDocument("12345678912");
            user.setEmail("user@seplag.mt.gov.br");
            user.setPassword(passwordEncoderConfig.encoder().encode("123456"));
            try {
                userRepository.save(user);
            } catch (DataIntegrityViolationException dive) {		
                throw new Exception("", dive);
            }
        }

        Pessoa pes1 = new Pessoa();
        Pessoa pes2 = new Pessoa();
        Pessoa pes3 = new Pessoa();
        if(pessoaRepository.findById(1).isEmpty()){
            pes1 = pessoaRepository.save(new Pessoa("José Maria", LocalDate.parse("1973-01-01"),"Masculino", "Maria Flor", "João Rosa"));
            pes2 = pessoaRepository.save(new Pessoa("Maria Rosa",LocalDate.parse("1990-04-07"),"Feminino", "Lurdes Albuquerque", "Pedro Silva"));
            pes3 = pessoaRepository.save(new Pessoa("João Bernardo",LocalDate.parse("1997-01-01"),"Masculino", "Rosa Maria", "José Silva"));
        }
        if(servidorEfetivoRepository.findById(1).isEmpty()){
            servidorEfetivoRepository.save(new ServidorEfetivo(null, pes1, "111111"));
            servidorEfetivoRepository.save(new ServidorEfetivo(null, pes2, "222222"));
        }
        if(servidorTemporarioRepository.findById(1).isEmpty()){
            servidorTemporarioRepository.save(new ServidorTemporario(null, pes3, LocalDate.parse("2024-01-01"),null));
        }

        Unidade unid1 = new Unidade();
        Unidade unid2 = new Unidade();
        Unidade unid3 = new Unidade();
        if(unidadeRepository.findById(1).isEmpty()){
            unid1 = unidadeRepository.save(new Unidade(null, "Unidade 1", "UNI1"));
            unid2 = unidadeRepository.save(new Unidade(null, "Unidade 2", "UNI2"));
            unid3 = unidadeRepository.save(new Unidade(null, "Unidade 3", "UNI3"));
        }

        if(lotacaoRepository.findById(1).isEmpty()){
            lotacaoRepository.save(new Lotacao(null, pes1, unid1, LocalDate.parse("2024-03-21"), null, "Portaria 1"));
            lotacaoRepository.save(new Lotacao(null, pes2, unid2, LocalDate.parse("2024-02-20"), null, "Portaria 1"));
            lotacaoRepository.save(new Lotacao(null, pes3, unid3, LocalDate.parse("2025-01-10"), null, "Portaria 1"));
        }

        Cidade cid1 = new Cidade();
        Cidade cid2 = new Cidade();
        Cidade cid3 = new Cidade();
        if(enderecoRepository.findById(1).isEmpty()){
            cid1 =cidadeRepository.save(new Cidade(null,"Rio de Janeiro", "RJ"));
            cid2 = cidadeRepository.save(new Cidade(null,"Cuiabá", "MT"));
            cid3 = cidadeRepository.save(new Cidade(null,"São Paulo", "SP"));
        }
        Endereco end1 = new Endereco();
        Endereco end2 = new Endereco();
        Endereco end3 = new Endereco();
        if(enderecoRepository.findById(1).isEmpty()){
            end1 = enderecoRepository.save(new Endereco(null, "Rua", "das Palmeiras", 123, "Centro", cid1));
            end2 = enderecoRepository.save(new Endereco(null, "Avenida", "Historiador Rubens de Mendonça", 3415, "CPA", cid2));
            end3 = enderecoRepository.save(new Endereco(null, "Rua", "Major Gama", 742, "Araés", cid3));
        }

        if(unidadeEnderecoRepository.findById(1).isEmpty()){
            unidadeEnderecoRepository.save(new UnidadeEndereco(null, unid1, end1));
            unidadeEnderecoRepository.save(new UnidadeEndereco(null, unid2, end2));
            unidadeEnderecoRepository.save(new UnidadeEndereco(null, unid3, end3));
        }

    }
}
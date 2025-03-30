package br.gov.mt.seplag.seletivo.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entidade que representa uma pessoa no sistema.
 */
@Entity
@Table(name = "pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pes_id")
    private Integer id;

    @Column(name = "pes_nome", length = 200, nullable = false)
    private String nome;

    @Column(name = "pes_data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "pes_sexo", length = 9)
    private String sexo;

    @Column(name = "pes_mae", length = 200)
    private String mae;

    @Column(name = "pes_pai", length = 200)
    private String pai;

    @JsonIgnore
    @OneToMany(mappedBy = "pessoa")
    private List<ServidorTemporario> servidoresTemporarios = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "pessoa")
    private List<ServidorEfetivo> servidoresEfetivos = new ArrayList<>();


}
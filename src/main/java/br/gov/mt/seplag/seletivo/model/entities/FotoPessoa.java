package br.gov.mt.seplag.seletivo.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entidade que representa a foto de uma pessoa no sistema.
 */
@Entity
@Table(name = "foto_pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FotoPessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fp_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    @Column(name = "fp_data")
    private LocalDate data;

    @Column(name = "fp_bucket", length = 50)
    private String bucket;

    @Column(name = "fp_hash", length = 50)
    private String hash;
}
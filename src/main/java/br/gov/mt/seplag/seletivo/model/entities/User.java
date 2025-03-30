package br.gov.mt.seplag.seletivo.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa um usuário do sistema para autenticação.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "document", length = 20, nullable = false, unique = true)
    private String document;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @Column(name = "role", length = 20)
    private String role;

    // Construtor com campos essenciais
    public User(String document, String password) {
        this.document = document;
        this.password = password;
        this.active = true;
    }

    // Construtor com pessoa associada
    public User(String document, String password, Pessoa pessoa) {
        this.document = document;
        this.password = password;
        this.pessoa = pessoa;
        this.active = true;
    }
}
package br.gov.mt.seplag.seletivo.repository;
import br.gov.mt.seplag.seletivo.model.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repositório para operações de persistência da entidade User
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByDocument(String document);
}
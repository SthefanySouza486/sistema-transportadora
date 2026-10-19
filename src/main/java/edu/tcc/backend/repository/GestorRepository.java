package edu.tcc.backend.repository;

import edu.tcc.backend.model.gestor.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GestorRepository extends JpaRepository<Gestor, Long> {

    Optional<Gestor> findByNome(String nome);

    Optional<Gestor> findByNomeIgnoreCase(String nome);

    boolean existsByNomeIgnoreCase(String nome);
}

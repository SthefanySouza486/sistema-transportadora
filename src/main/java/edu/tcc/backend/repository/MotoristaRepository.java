package edu.tcc.backend.repository;

import edu.tcc.backend.model.motorista.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

    boolean existsByNomeIgnoreCase(String nome);

    Optional<Motorista> findByNome(String nome);

    Optional<Motorista> findByNomeIgnoreCase(String nome);

    Optional<Motorista> findByEmailIgnoreCase(String email);
}

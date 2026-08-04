package edu.tcc.backend.repository;

import edu.tcc.backend.model.motorista.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

    List<Motorista> findByNomeContainingIgnoreCase(String nome);
    
    boolean existsByNomeIgnoreCase(String nome);

    Optional<Motorista> findByEmail(String email);
}

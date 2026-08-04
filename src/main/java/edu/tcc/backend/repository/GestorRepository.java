package edu.tcc.backend.repository;

import edu.tcc.backend.model.gestor.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestorRepository extends JpaRepository<Gestor, Long> {
}

package edu.tcc.backend.repository;

import edu.tcc.backend.model.gestor.Gestor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestorRepository extends CrudRepository<Gestor, Long> {
}

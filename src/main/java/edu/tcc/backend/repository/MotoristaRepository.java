package edu.tcc.backend.repository;

import edu.tcc.backend.model.motorista.Motorista;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoristaRepository extends CrudRepository<Motorista, Long> {

    List<Motorista> findByNomeContainingIgnoreCase(String nome);
}

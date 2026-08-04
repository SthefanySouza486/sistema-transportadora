package edu.tcc.backend.repository;

import edu.tcc.backend.model.despesa.CategoriaDespesa;
import edu.tcc.backend.model.despesa.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    List<Despesa> findByCategoria (CategoriaDespesa categoriaDespesa);

    List<Despesa> findByViagemId (Long viagemId);

    List<Despesa> findByViagemIdAndCategoria (Long viagemId, CategoriaDespesa categoriaDespesa);
}

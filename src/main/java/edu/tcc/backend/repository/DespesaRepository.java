package edu.tcc.backend.repository;

import edu.tcc.backend.model.despesa.CategoriaDespesa;
import edu.tcc.backend.model.despesa.Despesa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DespesaRepository extends CrudRepository<Despesa, Long> {

    List<Despesa> findByCategoria (CategoriaDespesa categoriaDespesa);

    List<Despesa> findByViagemId (Long viagemId);

    List<Despesa> findByViagemIdAndCategoria (Long viagemId, CategoriaDespesa categoriaDespesa);
}

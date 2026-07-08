package edu.tcc.backend.repository;

import edu.tcc.backend.model.viagem.StatusViagem;
import edu.tcc.backend.model.viagem.Viagem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ViagemRepository extends CrudRepository<Viagem, Long> {

    List<Viagem> findByStatusViagem (StatusViagem statusViagem);

    List<Viagem> findByDataViagem (LocalDate dataViagem);

    List<Viagem> findByMotoristaId (Long motoristaId);

    List<Viagem> findByVeiculoId (Long veiculoId);

    List<Viagem> findByMotoristaIdAndStatusViagem(Long motoristaId, StatusViagem statusViagem);
}

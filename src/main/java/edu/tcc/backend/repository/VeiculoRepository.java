package edu.tcc.backend.repository;

import edu.tcc.backend.model.veiculo.TipoVeiculo;
import edu.tcc.backend.model.veiculo.Veiculo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends CrudRepository<Veiculo, Long> {

    Optional<Veiculo> findByPlaca (String placa);

    List<Veiculo> findByTipo (TipoVeiculo veiculo);

    List<Veiculo> findByPlacaAndTipo (String placa, TipoVeiculo veiculo);
}

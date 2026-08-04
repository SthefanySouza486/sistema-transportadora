package edu.tcc.backend.repository;

import edu.tcc.backend.model.veiculo.TipoVeiculo;
import edu.tcc.backend.model.veiculo.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Optional<Veiculo> findByPlaca (String placa);
    
    boolean existsByPlaca(String placa);

    List<Veiculo> findByTipo (TipoVeiculo veiculo);

    List<Veiculo> findByPlacaAndTipo (String placa, TipoVeiculo veiculo);
}

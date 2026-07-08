package edu.tcc.backend.repository;

import edu.tcc.backend.model.pagamento.Pagamento;
import edu.tcc.backend.model.pagamento.StatusPagamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagamentoRepository extends CrudRepository<Pagamento, Long> {

    List<Pagamento> findByDataPagamento (LocalDate dataPagamento);

    List<Pagamento> findByStatusPagamento (StatusPagamento statusPagamento);

    List<Pagamento> findByPeriodoReferencia (LocalDate periodoReferencia);

    List<Pagamento> findByMotoristaId(Long motoristaId);
}

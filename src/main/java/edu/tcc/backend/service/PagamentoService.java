package edu.tcc.backend.service;

import edu.tcc.backend.dto.pagamento.PagamentoMapper;
import edu.tcc.backend.dto.pagamento.PagamentoRequest;
import edu.tcc.backend.dto.pagamento.PagamentoResponse;
import edu.tcc.backend.model.pagamento.Pagamento;
import edu.tcc.backend.model.pagamento.StatusPagamento;
import edu.tcc.backend.repository.MotoristaRepository;
import edu.tcc.backend.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository repository;
    private final MotoristaRepository motoristaRepository;
    private final PagamentoMapper mapper;

    @Transactional
    public PagamentoResponse registrarPagamento(PagamentoRequest request) {
        if (!motoristaRepository.existsById(request.getMotoristaId())) {
            throw new IllegalArgumentException("Erro: Motorista não encontrado para realizar o pagamento.");
        }

        Pagamento pagamento = mapper.toEntity(request);
        Pagamento pagamentoSalvo = repository.save(pagamento);

        return mapper.toResponse(pagamentoSalvo);
    }

    @Transactional
    public PagamentoResponse atualizar(Long id, PagamentoRequest request) {
        Pagamento pagamento = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado."));

        pagamento.setMotoristaId(request.getMotoristaId());
        pagamento.setDataPagamento(request.getDataPagamento());
        pagamento.setValorPago(request.getValorPago());
        pagamento.setStatusPagamento(request.getStatusPagamento());
        pagamento.setFotoComprovante(request.getFotoComprovante());

        if (request.getPeriodoReferencia() != null) {
            pagamento.setPeriodoReferencia(LocalDate.parse(request.getPeriodoReferencia() + "-01"));
        }

        return mapper.toResponse(repository.save(pagamento));
    }

    @Transactional
    public PagamentoResponse atualizarStatus (Long id, StatusPagamento novoStatus) {
        Pagamento pagamento = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado."));
        pagamento.setStatusPagamento(novoStatus);
        return mapper.toResponse(repository.save(pagamento));
    }

    public List<PagamentoResponse> listarPorMotorista(Long motoristaId) {
        List<Pagamento> pagamentos = repository.findByMotoristaId(motoristaId);
        return mapper.toResponseList(pagamentos);
    }

    public List<PagamentoResponse> listarTodos() {
        List<Pagamento> pagamentos = repository.findAll();
        return mapper.toResponseList(pagamentos);
    }

    public PagamentoResponse buscarPorId(Long id) {
        Pagamento pagamento = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado."));
        return mapper.toResponse(pagamento);
    }

    public List<PagamentoResponse> buscarPorDataPagamento (LocalDate dataPagamento) {
        List<Pagamento> dataDePagamento = repository.findByDataPagamento(dataPagamento);
        return mapper.toResponseList(dataDePagamento);
    }

    public List<PagamentoResponse> buscarPorStatusPagamento (StatusPagamento statusPagamento) {
        List<Pagamento> statusDoPagamento = repository.findByStatusPagamento(statusPagamento);
        return mapper.toResponseList(statusDoPagamento);
    }

    public List<PagamentoResponse> buscarPorPeriodoReferencia (LocalDate periodoReferencia) {
        List<Pagamento> mesReferencia = repository.findByPeriodoReferencia(periodoReferencia);
        return mapper.toResponseList(mesReferencia);
    }

    @Transactional
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}

package edu.tcc.backend.service;

import edu.tcc.backend.dto.pagamento.PagamentoMapper;
import edu.tcc.backend.dto.pagamento.PagamentoRequest;
import edu.tcc.backend.dto.pagamento.PagamentoResponse;
import edu.tcc.backend.model.pagamento.Pagamento;
import edu.tcc.backend.repository.MotoristaRepository;
import edu.tcc.backend.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<PagamentoResponse> listarPorMotorista(Long motoristaId) {
        List<Pagamento> pagamentos = repository.findByMotoristaId(motoristaId);
        return mapper.toResponseList(pagamentos);
    }
}

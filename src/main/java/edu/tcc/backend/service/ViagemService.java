package edu.tcc.backend.service;

import edu.tcc.backend.dto.viagem.ViagemMapper;
import edu.tcc.backend.dto.viagem.ViagemRequest;
import edu.tcc.backend.dto.viagem.ViagemResponse;
import edu.tcc.backend.model.motorista.Motorista;
import edu.tcc.backend.model.viagem.StatusViagem;
import edu.tcc.backend.model.viagem.Viagem;
import edu.tcc.backend.repository.MotoristaRepository;
import edu.tcc.backend.repository.ViagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class ViagemService {

    private final ViagemRepository repository;
    private final MotoristaRepository motoristaRepository;
    private final ViagemMapper mapper;

    @Transactional
    public ViagemResponse registrarViagem(ViagemRequest request) {
        Motorista motorista = motoristaRepository.findById(request.getMotoristaId())
                .orElseThrow(() -> new IllegalArgumentException("Motorista não encontrado no sistema."));

        BigDecimal valorTotalFrete = request.getPesoTonelada().multiply(request.getValorTonelada());

        BigDecimal porcentagem = motorista.getComissao().divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);

        BigDecimal valorComissao = valorTotalFrete.multiply(porcentagem);

        Viagem viagem = mapper.toEntity(request);

        viagem.setValorFrete(valorTotalFrete);
        viagem.setValorComissao(valorComissao);

        viagem.setStatusViagem(StatusViagem.EM_ANDAMENTO);

        Viagem viagemSalva = repository.save(viagem);

        return mapper.toResponse(viagemSalva);
    }
}

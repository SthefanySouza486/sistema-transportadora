package edu.tcc.backend.service;

import edu.tcc.backend.dto.viagem.ViagemMapper;
import edu.tcc.backend.dto.viagem.ViagemRequest;
import edu.tcc.backend.dto.viagem.ViagemResponse;
import edu.tcc.backend.dto.viagem.ViagemStatusRequest;
import edu.tcc.backend.model.despesa.Despesa;
import edu.tcc.backend.model.motorista.Motorista;
import edu.tcc.backend.model.viagem.StatusViagem;
import edu.tcc.backend.model.viagem.Viagem;
import edu.tcc.backend.repository.DespesaRepository;
import edu.tcc.backend.repository.MotoristaRepository;
import edu.tcc.backend.repository.ViagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ViagemService {

    private final ViagemRepository repository;
    private final MotoristaRepository motoristaRepository;
    private final DespesaRepository despesaRepository;
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

    @Transactional(readOnly = true)
    public List<ViagemResponse> listarTodos() {
        java.util.List<Viagem> viagens = new java.util.ArrayList<>();
        repository.findAll().forEach(viagens::add);
        return mapper.toResponseList(viagens);
    }

    @Transactional(readOnly = true)
    public ViagemResponse buscarPorId(Long id) {
        Viagem viagem = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Viagem não encontrada."));
        return mapper.toResponse(viagem);
    }

    @Transactional
    public ViagemResponse atualizar(Long id, ViagemRequest request) {
        Viagem viagem = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Viagem não encontrada."));

        Motorista motorista = motoristaRepository.findById(request.getMotoristaId())
                .orElseThrow(() -> new IllegalArgumentException("Motorista não encontrado no sistema."));

        viagem.setMotoristaId(request.getMotoristaId());
        viagem.setVeiculoId(request.getVeiculoId());
        viagem.setDataViagem(request.getDataViagem());
        viagem.setOrigem(request.getOrigem());
        viagem.setDestino(request.getDestino());
        viagem.setDinheiroEntregue(request.getDinheiroEntregue());
        viagem.setPesoTonelada(request.getPesoTonelada());
        viagem.setValorTonelada(request.getValorTonelada());

        BigDecimal valorTotalFrete = request.getPesoTonelada().multiply(request.getValorTonelada());
        BigDecimal porcentagem = motorista.getComissao().divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        BigDecimal valorComissao = valorTotalFrete.multiply(porcentagem);

        viagem.setValorFrete(valorTotalFrete);
        viagem.setValorComissao(valorComissao);

        return mapper.toResponse(repository.save(viagem));
    }

    @Transactional
    public ViagemResponse atualizarStatus(Long id, ViagemStatusRequest request) {
        Viagem viagem = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Viagem não encontrada."));

        viagem.setStatusViagem(request.getStatusViagem());

        Viagem viagemSalva = repository.save(viagem);
        return  mapper.toResponse(viagemSalva);
    }

    public List<ViagemResponse> listarPorMotorista(Long motoristaId) {
        List<Viagem> viagens = repository.findByMotoristaId(motoristaId);
        return mapper.toResponseList(viagens);
    }

    public List<ViagemResponse> listarPorDataViagem (LocalDate dataViagem) {
        List<Viagem> viagemData = repository.findByDataViagem(dataViagem);
        return mapper.toResponseList(viagemData);
    }

    public List<ViagemResponse> listarPorStatusViagem (StatusViagem statusViagem) {
        List<Viagem> viagemStatus = repository.findByStatusViagem(statusViagem);
        return mapper.toResponseList(viagemStatus);
    }

    public List<ViagemResponse> listarPorVeiculoId (Long veiculoId) {
        List<Viagem> veiculo = repository.findByVeiculoId(veiculoId);
        return mapper.toResponseList(veiculo);
    }

    public List<ViagemResponse> listarPorMotoristaIdEStatusViagem (Long motoristaId, StatusViagem statusViagem) {
        List<Viagem> motoristaStatus = repository.findByMotoristaIdAndStatusViagem(motoristaId, statusViagem);
        return  mapper.toResponseList(motoristaStatus); 
    }

    @Transactional
    public void excluir(Long id) {
        Viagem viagem = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Viagem não encontrada."));
        
        if (StatusViagem.FINALIZADA.equals(viagem.getStatusViagem())) {
            throw new IllegalArgumentException("Não é possível excluir uma viagem já concluída.");
        }

        List<Despesa> despesas = despesaRepository.findByViagemId(id);
        if (despesas != null && !despesas.isEmpty()) {
            despesaRepository.deleteAll(despesas);
        }
        
        repository.delete(viagem);
    }
}

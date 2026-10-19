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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ViagemServiceTest {

    @Mock
    private ViagemRepository repository;

    @Mock
    private MotoristaRepository motoristaRepository;

    @Mock
    private DespesaRepository despesaRepository;

    @Mock
    private ViagemMapper mapper;

    @InjectMocks
    private ViagemService service;

    @Test
    void deveRegistrarViagemComCalculosDeFreteEComissaoCorretos() {
        Motorista motorista = Motorista.builder()
                .id(1L)
                .nome("José da Silva")
                .comissao(new BigDecimal("15.00"))
                .build();

        ViagemRequest request = ViagemRequest.builder()
                .motoristaId(1L)
                .veiculoId(2L)
                .dataViagem(LocalDate.now())
                .origem("Assis - SP")
                .destino("Santos - SP")
                .pesoTonelada(new BigDecimal("30"))
                .valorTonelada(new BigDecimal("100.00"))
                .build();

        Viagem viagemEntidade = new Viagem();
        when(motoristaRepository.findById(1L)).thenReturn(Optional.of(motorista));
        when(mapper.toEntity(request)).thenReturn(viagemEntidade);
        when(repository.save(any(Viagem.class))).thenAnswer(inv -> inv.getArgument(0));
        when(mapper.toResponse(any(Viagem.class))).thenReturn(new ViagemResponse());

        ViagemResponse response = service.registrarViagem(request);

        assertNotNull(response);

        ArgumentCaptor<Viagem> captor = ArgumentCaptor.forClass(Viagem.class);
        verify(repository).save(captor.capture());

        Viagem viagemSalva = captor.getValue();
        assertEquals(0, new BigDecimal("3000.00").compareTo(viagemSalva.getValorFrete()));
        assertEquals(0, new BigDecimal("450.00").compareTo(viagemSalva.getValorComissao()));
        assertEquals(StatusViagem.EM_ANDAMENTO, viagemSalva.getStatusViagem());
    }

    @Test
    void deveLancarExcecaoAoRegistrarViagemComMotoristaInexistente() {
        ViagemRequest request = ViagemRequest.builder()
                .motoristaId(99L)
                .build();

        when(motoristaRepository.findById(99L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.registrarViagem(request)
        );

        assertEquals("Motorista não encontrado no sistema.", exception.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void naoDevePermitirExcluirViagemFinalizada() {
        Viagem viagemFinalizada = Viagem.builder()
                .id(1L)
                .statusViagem(StatusViagem.FINALIZADA)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(viagemFinalizada));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.excluir(1L)
        );

        assertEquals("Não é possível excluir uma viagem já concluída.", exception.getMessage());
        verify(repository, never()).delete(any());
        verify(despesaRepository, never()).deleteAll(any());
    }

    @Test
    void deveExcluirViagemEDespesasAssociadasQuandoEmAndamento() {
        Viagem viagem = Viagem.builder()
                .id(1L)
                .statusViagem(StatusViagem.EM_ANDAMENTO)
                .build();

        Despesa despesa1 = Despesa.builder().id(10L).viagemId(1L).build();
        List<Despesa> despesas = List.of(despesa1);

        when(repository.findById(1L)).thenReturn(Optional.of(viagem));
        when(despesaRepository.findByViagemId(1L)).thenReturn(despesas);

        service.excluir(1L);

        verify(despesaRepository).deleteAll(despesas);
        verify(repository).delete(viagem);
    }

    @Test
    void deveAtualizarStatusViagemComSucesso() {
        Viagem viagem = Viagem.builder()
                .id(1L)
                .statusViagem(StatusViagem.EM_ANDAMENTO)
                .build();

        ViagemStatusRequest statusRequest = new ViagemStatusRequest(StatusViagem.FINALIZADA);

        when(repository.findById(1L)).thenReturn(Optional.of(viagem));
        when(repository.save(viagem)).thenReturn(viagem);
        when(mapper.toResponse(viagem)).thenReturn(new ViagemResponse());

        ViagemResponse response = service.atualizarStatus(1L, statusRequest);

        assertNotNull(response);
        assertEquals(StatusViagem.FINALIZADA, viagem.getStatusViagem());
        verify(repository).save(viagem);
    }
}

package edu.tcc.backend.service;

import edu.tcc.backend.dto.despesa.DespesaMapper;
import edu.tcc.backend.dto.despesa.DespesaRequest;
import edu.tcc.backend.dto.despesa.DespesaResponse;
import edu.tcc.backend.model.despesa.CategoriaDespesa;
import edu.tcc.backend.model.despesa.Despesa;
import edu.tcc.backend.repository.DespesaRepository;
import edu.tcc.backend.repository.ViagemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DespesaServiceTest {

    @Mock
    private DespesaRepository repository;

    @Mock
    private ViagemRepository viagemRepository;

    @Mock
    private DespesaMapper mapper;

    @InjectMocks
    private DespesaService service;

    @Test
    void deveRegistrarDespesaComSucessoQuandoViagemExiste() {
        DespesaRequest request = DespesaRequest.builder()
                .viagemId(1L)
                .categoria(CategoriaDespesa.COMBUSTIVEL)
                .dataDespesa(LocalDate.now())
                .valor(new BigDecimal("350.00"))
                .descricao("Abastecimento")
                .fotoComprovante("comprovante-base64")
                .build();

        Despesa despesaSalva = Despesa.builder()
                .id(10L)
                .viagemId(1L)
                .categoria(CategoriaDespesa.COMBUSTIVEL)
                .valor(new BigDecimal("350.00"))
                .build();

        when(viagemRepository.existsById(1L)).thenReturn(true);
        when(mapper.toEntity(request)).thenReturn(despesaSalva);
        when(repository.save(despesaSalva)).thenReturn(despesaSalva);
        when(mapper.toResponse(despesaSalva)).thenReturn(new DespesaResponse());

        DespesaResponse response = service.registrarDespesa(request);

        assertNotNull(response);
        verify(viagemRepository).existsById(1L);
        verify(repository).save(despesaSalva);
    }

    @Test
    void deveLancarExcecaoAoRegistrarDespesaParaViagemInexistente() {
        DespesaRequest request = DespesaRequest.builder()
                .viagemId(99L)
                .build();

        when(viagemRepository.existsById(99L)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.registrarDespesa(request)
        );

        assertEquals("Erro: Não é possível lançar uma despesa para uma viagem inexistente.", exception.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoAoBuscarDespesaInexistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.buscarPorId(99L)
        );

        assertEquals("Despesa não encontrada.", exception.getMessage());
    }

    @Test
    void deveExcluirDespesaComSucesso() {
        Despesa despesa = Despesa.builder().id(5L).build();
        when(repository.findById(5L)).thenReturn(Optional.of(despesa));

        service.excluir(5L);

        verify(repository).delete(despesa);
    }
}

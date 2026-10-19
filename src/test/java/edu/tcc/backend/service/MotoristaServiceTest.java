package edu.tcc.backend.service;

import edu.tcc.backend.dto.motorista.MotoristaMapper;
import edu.tcc.backend.dto.motorista.MotoristaRequest;
import edu.tcc.backend.dto.motorista.MotoristaResponse;
import edu.tcc.backend.model.motorista.Motorista;
import edu.tcc.backend.repository.MotoristaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MotoristaServiceTest {

    @Mock
    private MotoristaRepository repository;

    @Mock
    private MotoristaMapper mapper;

    @InjectMocks
    private MotoristaService service;

    @Test
    void deveCadastrarMotoristaComSucesso() {
        MotoristaRequest request = MotoristaRequest.builder()
                .nome("Carlos Eduardo")
                .telefone("18999999999")
                .senha("senha123")
                .comissao(new BigDecimal("12.00"))
                .build();

        Motorista motorista = Motorista.builder()
                .id(1L)
                .nome("Carlos Eduardo")
                .telefone("18999999999")
                .comissao(new BigDecimal("12.00"))
                .build();

        when(repository.existsByNomeIgnoreCase("Carlos Eduardo")).thenReturn(false);
        when(mapper.toEntity(request)).thenReturn(motorista);
        when(repository.save(motorista)).thenReturn(motorista);
        when(mapper.toResponse(motorista)).thenReturn(new MotoristaResponse());

        MotoristaResponse response = service.cadastrar(request);

        assertNotNull(response);
        assertEquals("MOTORISTA", motorista.getPerfil());
        verify(repository).save(motorista);
    }

    @Test
    void deveLancarExcecaoAoCadastrarMotoristaComNomeDuplicado() {
        MotoristaRequest request = MotoristaRequest.builder()
                .nome("Carlos Eduardo")
                .senha("senha123")
                .comissao(new BigDecimal("12.00"))
                .build();

        when(repository.existsByNomeIgnoreCase("Carlos Eduardo")).thenReturn(true);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.cadastrar(request)
        );

        assertEquals("Erro: Já existe um motorista cadastrado com este nome.", exception.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoAoCadastrarMotoristaSemSenha() {
        MotoristaRequest request = MotoristaRequest.builder()
                .nome("Motorista Novo")
                .senha("   ")
                .comissao(new BigDecimal("10.00"))
                .build();

        when(repository.existsByNomeIgnoreCase("Motorista Novo")).thenReturn(false);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.cadastrar(request)
        );

        assertEquals("Erro: A senha é obrigatória para novos cadastros.", exception.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoAoBuscarMotoristaInexistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.buscarPorId(99L)
        );

        assertEquals("Motorista não encontrado.", exception.getMessage());
    }
}

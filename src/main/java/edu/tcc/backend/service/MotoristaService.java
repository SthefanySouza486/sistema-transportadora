package edu.tcc.backend.service;

import edu.tcc.backend.dto.motorista.MotoristaMapper;
import edu.tcc.backend.dto.motorista.MotoristaRequest;
import edu.tcc.backend.dto.motorista.MotoristaResponse;
import edu.tcc.backend.model.motorista.Motorista;
import edu.tcc.backend.repository.MotoristaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MotoristaService {

    private final MotoristaRepository repository;
    private final MotoristaMapper mapper;

    @Transactional
    public MotoristaResponse cadastrar(MotoristaRequest request){
        if (repository.existsByNomeIgnoreCase(request.getNome())) {
            throw new IllegalArgumentException("Erro: Já existe um motorista cadastrado com este nome.");
        }

        if (request.getSenha() == null || request.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: A senha é obrigatória para novos cadastros.");
        }

        Motorista motorista = mapper.toEntity(request);
        motorista.setPerfil("MOTORISTA");
        Motorista motoristaSalvo = repository.save(motorista);
        return mapper.toResponse(motoristaSalvo);
    }

    public List<MotoristaResponse> listarTodos() {
        List<Motorista> motoristas = repository.findAll();
        return mapper.toResponseList(motoristas);
    }

    public MotoristaResponse buscarPorId(Long id) {
        Motorista motorista = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Motorista não encontrado."));
        return mapper.toResponse(motorista);
    }

    @Transactional
    public MotoristaResponse atualizar(Long id, MotoristaRequest request) {
        Motorista motorista = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Motorista não encontrado."));

        if (!motorista.getNome().equalsIgnoreCase(request.getNome()) && 
            repository.existsByNomeIgnoreCase(request.getNome())) {
            throw new IllegalArgumentException("Erro: Já existe um motorista cadastrado com este nome.");
        }

        motorista.setNome(request.getNome());
        motorista.setTelefone(request.getTelefone());
        motorista.setComissao(request.getComissao());

        if (request.getSenha() != null && !request.getSenha().trim().isEmpty()) {
            motorista.setSenha(request.getSenha().trim());
        }

        if (motorista.getPerfil() == null) {
            motorista.setPerfil("MOTORISTA");
        }
        Motorista atualizado = repository.save(motorista);
        return mapper.toResponse(atualizado);
    }

    @Transactional
    public void excluir(Long id) {
        Motorista motorista = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Motorista não encontrado."));
        repository.delete(motorista);
    }
}

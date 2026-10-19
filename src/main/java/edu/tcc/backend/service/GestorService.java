package edu.tcc.backend.service;

import edu.tcc.backend.dto.gestor.GestorMapper;
import edu.tcc.backend.dto.gestor.GestorRequest;
import edu.tcc.backend.dto.gestor.GestorResponse;
import edu.tcc.backend.model.gestor.Gestor;
import edu.tcc.backend.repository.GestorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GestorService {

    private final GestorRepository repository;
    private final GestorMapper mapper;

    @Transactional
    public GestorResponse cadastrar(GestorRequest request) {
        if (repository.existsByNomeIgnoreCase(request.getNome())) {
            throw new IllegalArgumentException("Erro: Já existe um gestor cadastrado com esse nome.");
        }

        if (request.getSenha() == null || request.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: A senha é obrigatória para novos cadastros.");
        }

        Gestor gestor = mapper.toEntity(request);
        Gestor gestorSalvo = repository.save(gestor);

        return mapper.toResponse(gestorSalvo);
    }

    public List<GestorResponse> listarTodos() {
        List<Gestor> gestores = repository.findAll();
        return mapper.toResponseList(gestores);
    }

    @Transactional
    public GestorResponse atualizar(Long id, GestorRequest request) {
        Gestor gestor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gestor não encontrado"));

        gestor.setNome(request.getNome());

        if (request.getSenha() != null && !request.getSenha().trim().isEmpty()) {
            gestor.setSenha(request.getSenha().trim());
        }

        Gestor salvo = repository.save(gestor);
        return mapper.toResponse(salvo);
    }

    public GestorResponse buscarPorId(Long id) {
        Gestor gestor = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gestor não encontrado."));
        return mapper.toResponse(gestor);
    }

    @Transactional
    public void excluir(Long id) {
        Gestor gestor = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gestor não encontrado."));
        repository.delete(gestor);
    }
}

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
        Gestor gestor = mapper.toEntity(request);
        Gestor gestorSalvo = repository.save(gestor);

        return mapper.toResponse(gestorSalvo);
    }

    public List<GestorResponse> listarTodos() {
        List<Gestor> gestores = (List<Gestor>) repository.findAll();

        return mapper.toResponseList(gestores);
    }
}

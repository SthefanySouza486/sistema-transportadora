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
        Motorista motorista = mapper.toEntity(request);

        Motorista motoristaSalvo = repository.save(motorista);

        return mapper.toResponse(motoristaSalvo);
    }

    public List<MotoristaResponse> listarTodos() {
        List<Motorista> motoristas = (List<Motorista>) repository.findAll();
        return mapper.toResponseList(motoristas);
    }
}

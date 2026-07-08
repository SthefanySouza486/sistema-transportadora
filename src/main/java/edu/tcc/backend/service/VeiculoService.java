package edu.tcc.backend.service;

import edu.tcc.backend.dto.veiculo.VeiculoMapper;
import edu.tcc.backend.dto.veiculo.VeiculoRequest;
import edu.tcc.backend.dto.veiculo.VeiculoResponse;
import edu.tcc.backend.model.veiculo.Veiculo;
import edu.tcc.backend.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository repository;
    private final VeiculoMapper mapper;

    @Transactional
    public VeiculoResponse cadastrar(VeiculoRequest request) {
        Veiculo veiculo = mapper.toEntity(request);
        Veiculo veiculoSalvo = repository.save(veiculo);
        return mapper.toResponse(veiculoSalvo);
    }

    public List<VeiculoResponse> listarTodos() {
        List<Veiculo> veiculos = (List<Veiculo>) repository.findAll();
        return mapper.toResponseList(veiculos);
    }
}

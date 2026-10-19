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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository repository;
    private final VeiculoMapper mapper;

    @Transactional
    public VeiculoResponse cadastrar(VeiculoRequest request) {
        if (repository.existsByPlaca(request.getPlaca())) {
            throw new IllegalArgumentException("Erro: Já existe um veículo cadastrado com esta placa.");
        }
        Veiculo veiculo = mapper.toEntity(request);
        Veiculo veiculoSalvo = repository.save(veiculo);
        return mapper.toResponse(veiculoSalvo);
    }

    public List<VeiculoResponse> listarTodos() {
        List<Veiculo> veiculos = repository.findAll();
        return mapper.toResponseList(veiculos);
    }

    public VeiculoResponse buscarPorId(Long id) {
        Veiculo veiculo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado."));
        return mapper.toResponse(veiculo);
    }

    public Optional<VeiculoResponse> buscarPorPlaca(String placa) {
        return repository.findByPlaca(placa).map(mapper::toResponse);
    }


    @Transactional
    public VeiculoResponse atualizar(Long id, VeiculoRequest request) {
        Veiculo veiculo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado."));
        
        if (!veiculo.getPlaca().equalsIgnoreCase(request.getPlaca()) && 
            repository.existsByPlaca(request.getPlaca())) {
            throw new IllegalArgumentException("Erro: Já existe um veículo cadastrado com esta placa.");
        }
        
        veiculo.setPlaca(request.getPlaca());
        veiculo.setPlacaCarreta1(request.getPlacaCarreta1());
        veiculo.setPlacaCarreta2(request.getPlacaCarreta2());
        
        return mapper.toResponse(repository.save(veiculo));
    }

    @Transactional
    public void excluir(Long id) {
        Veiculo veiculo = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado."));
        repository.delete(veiculo);
    }
}

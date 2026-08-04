package edu.tcc.backend.service;

import edu.tcc.backend.dto.despesa.DespesaMapper;
import edu.tcc.backend.dto.despesa.DespesaRequest;
import edu.tcc.backend.dto.despesa.DespesaResponse;
import edu.tcc.backend.model.despesa.Despesa;
import edu.tcc.backend.repository.DespesaRepository;
import edu.tcc.backend.repository.ViagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository repository;
    private final ViagemRepository viagemRepository;
    private final DespesaMapper mapper;

    @Transactional
    public DespesaResponse registrarDespesa(DespesaRequest request) {
        if (!viagemRepository.existsById(request.getViagemId())) {
            throw new IllegalArgumentException("Erro: Não é possível lançar uma despesa para uma viagem inexistente.");
        }

        Despesa despesa = mapper.toEntity(request);
        Despesa despesaSalva = repository.save(despesa);

        return mapper.toResponse(despesaSalva);
    }

    public List<DespesaResponse> listarPorViagem(Long viagemId) {
        List<Despesa> despesas = repository.findByViagemId(viagemId);
        return mapper.toResponseList(despesas);
    }

    public List<DespesaResponse> listarTodos() {
        List<Despesa> despesas = (List<Despesa>) repository.findAll();
        return mapper.toResponseList(despesas);
    }

    public DespesaResponse buscarPorId(Long id) {
        Despesa despesa = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Despesa não encontrada."));
        return mapper.toResponse(despesa);
    }

    @Transactional
    public DespesaResponse atualizar(Long id, DespesaRequest request) {
        Despesa despesa = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Despesa não encontrada."));

        if (!viagemRepository.existsById(request.getViagemId())) {
            throw new IllegalArgumentException("Erro: Não é possível atualizar para uma viagem inexistente.");
        }

        despesa.setViagemId(request.getViagemId());
        despesa.setDataDespesa(request.getDataDespesa());
        despesa.setCategoria(request.getCategoria());
        despesa.setDescricao(request.getDescricao());
        despesa.setValor(request.getValor());

        return mapper.toResponse(repository.save(despesa));
    }

    @Transactional
    public void excluir(Long id) {
        Despesa despesa = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Despesa não encontrada."));
        repository.delete(despesa);
    }
}

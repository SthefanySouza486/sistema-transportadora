package edu.tcc.backend.controller;

import edu.tcc.backend.dto.viagem.ViagemRequest;
import edu.tcc.backend.dto.viagem.ViagemResponse;
import edu.tcc.backend.model.viagem.Viagem;
import edu.tcc.backend.repository.ViagemRepository;
import edu.tcc.backend.service.ViagemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/viagens")
@RequiredArgsConstructor
public class ViagemController {

    private final ViagemService service;
    private final ViagemRepository repository;

    @PostMapping
    public ResponseEntity<ViagemResponse> registrarViagem(@Valid @RequestBody ViagemRequest request){
        ViagemResponse response = service.registrarViagem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ViagemResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViagemResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/faturamento/motorista/{motoristaId}")
    public ResponseEntity<Double> calcularFaturamentoMotorista(@PathVariable Long motoristaId) {

        List<Viagem> todasAsViagens = repository.findAll();

        double totalComissao = 0.0;

        for (Viagem viagem : todasAsViagens) {
            if (viagem.getMotoristaId() != null && viagem.getMotoristaId().equals(motoristaId)) {

                if (viagem.getValorComissao() != null) {
                    totalComissao += viagem.getValorComissao().doubleValue();
                }

            }
        }
        return ResponseEntity.ok(totalComissao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViagemResponse> atualizar(@PathVariable Long id, @Valid @RequestBody ViagemRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

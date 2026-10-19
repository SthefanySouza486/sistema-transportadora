package edu.tcc.backend.controller;

import edu.tcc.backend.dto.viagem.ViagemRequest;
import edu.tcc.backend.dto.viagem.ViagemResponse;
import edu.tcc.backend.dto.viagem.ViagemStatusRequest;
import edu.tcc.backend.model.viagem.StatusViagem;
import edu.tcc.backend.model.viagem.Viagem;
import edu.tcc.backend.repository.ViagemRepository;
import edu.tcc.backend.service.ViagemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = {
        RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS
})
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

        List<Viagem> viagensDoMotorista = repository.findByMotoristaId(motoristaId);

        double totalComissao = 0.0;

        for (Viagem viagem : viagensDoMotorista) {
            if (viagem.getValorComissao() != null) {
                    totalComissao += viagem.getValorComissao().doubleValue();
                }
        }
        return ResponseEntity.ok(totalComissao);
    }

    @GetMapping("/motorista/{motoristaId}")
    public ResponseEntity<List<ViagemResponse>> findByMotoristaId (@PathVariable Long motoristaId) {
        return ResponseEntity.ok(service.listarPorMotorista(motoristaId));
    }

    @GetMapping("/status-viagem/{statusViagem}")
    public ResponseEntity<List<ViagemResponse>> findByStatusViagem (@PathVariable StatusViagem statusViagem) {
        return ResponseEntity.ok(service.listarPorStatusViagem(statusViagem));
    }

    @GetMapping("/data-viagem/{dataViagem}")
    public ResponseEntity<List<ViagemResponse>> findByDataViagem (@PathVariable LocalDate dataViagem) {
        return ResponseEntity.ok(service.listarPorDataViagem(dataViagem));
    }

    @GetMapping("/veiculo/{id}")
    public ResponseEntity<List<ViagemResponse>> findByVeiculoId (@PathVariable Long veiculoId) {
        return ResponseEntity.ok(service.listarPorVeiculoId(veiculoId));
    }

    @GetMapping("/motorista/{id}/status-viagem/{status}")
    public ResponseEntity<List<ViagemResponse>> findByMotoristaIdAndStatusViagem (@PathVariable Long motoristaId,
                                                                                  @PathVariable StatusViagem statusViagem) {
        return ResponseEntity.ok(service.listarPorMotoristaIdEStatusViagem(motoristaId, statusViagem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ViagemResponse> atualizar(@PathVariable Long id, @Valid @RequestBody ViagemRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ViagemResponse> atualizarStatus(@PathVariable Long id,
                                                          @Valid @RequestBody ViagemStatusRequest request) {
        return ResponseEntity.ok(service.atualizarStatus(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

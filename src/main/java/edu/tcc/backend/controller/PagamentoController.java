package edu.tcc.backend.controller;

import edu.tcc.backend.dto.pagamento.PagamentoRequest;
import edu.tcc.backend.dto.pagamento.PagamentoResponse;
import edu.tcc.backend.model.pagamento.StatusPagamento;
import edu.tcc.backend.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService service;

    @PostMapping
    public ResponseEntity<PagamentoResponse> registrarPagamento(@Valid @RequestBody PagamentoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarPagamento(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoResponse> atualizar(@PathVariable Long id, @Valid @RequestBody PagamentoRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @PatchMapping("status/{id}")
    public ResponseEntity<PagamentoResponse> atualizarStatus(@PathVariable Long id, @RequestBody StatusPagamento status) {
        return ResponseEntity.ok(service.atualizarStatus(id, status));
    }

    @GetMapping("/motorista/{motoristaId}")
    public ResponseEntity<List<PagamentoResponse>> listarPorMotoristas(@PathVariable Long motoristaId) {
        return ResponseEntity.ok(service.listarPorMotorista(motoristaId));
    }

    @GetMapping("/data-pagamento/{dataPagamento}")
    public ResponseEntity<List<PagamentoResponse>> findByDataPagamento (@PathVariable LocalDate dataPagamento) {
        return ResponseEntity.ok(service.buscarPorDataPagamento(dataPagamento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/status-pagamento/{statusPagamento}")
    public ResponseEntity<List<PagamentoResponse>> findByStatusPagamento (@PathVariable StatusPagamento statusPagamento){
        return ResponseEntity.ok(service.buscarPorStatusPagamento(statusPagamento));
    }

    @GetMapping("/periodo-referencia/{periodoReferencia}")
    public ResponseEntity<List<PagamentoResponse>> findByPeriodoReferencia (@PathVariable LocalDate periodoReferencia) {
        return ResponseEntity.ok(service.buscarPorPeriodoReferencia(periodoReferencia));
    }

    @GetMapping
    public ResponseEntity<List<PagamentoResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

package edu.tcc.backend.controller;

import edu.tcc.backend.dto.pagamento.PagamentoRequest;
import edu.tcc.backend.dto.pagamento.PagamentoResponse;
import edu.tcc.backend.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/motorista/{motoristaId}")
    public ResponseEntity<List<PagamentoResponse>> listarPorMotoristas(@PathVariable Long motoristaId) {
        return ResponseEntity.ok(service.listarPorMotorista(motoristaId));
    }

}

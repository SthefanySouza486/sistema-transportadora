package edu.tcc.backend.controller;

import edu.tcc.backend.dto.despesa.DespesaRequest;
import edu.tcc.backend.dto.despesa.DespesaResponse;
import edu.tcc.backend.service.DespesaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/despesas")
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaService service;

    @PostMapping
    public ResponseEntity<DespesaResponse> registrarDespesa(@Valid @RequestBody DespesaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarDespesa(request));
    }

    @GetMapping("/viagem/{viagemId}")
    public ResponseEntity<List<DespesaResponse>> listarPorViagem(@PathVariable Long viagemId) {
        return ResponseEntity.ok(service.listarPorViagem(viagemId));
    }
}

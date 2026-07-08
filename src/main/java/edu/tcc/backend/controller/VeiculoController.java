package edu.tcc.backend.controller;

import edu.tcc.backend.dto.veiculo.VeiculoRequest;
import edu.tcc.backend.dto.veiculo.VeiculoResponse;
import edu.tcc.backend.service.VeiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService service;

    @PostMapping
    public ResponseEntity<VeiculoResponse> cadastrar(@Valid @RequestBody VeiculoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
}

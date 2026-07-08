package edu.tcc.backend.controller;

import edu.tcc.backend.dto.motorista.MotoristaRequest;
import edu.tcc.backend.dto.motorista.MotoristaResponse;
import edu.tcc.backend.service.MotoristaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/motoristas")
@RequiredArgsConstructor
public class MotoristaController {

    private final MotoristaService service;

    @PostMapping
    public ResponseEntity<MotoristaResponse> cadastrar(@Valid @RequestBody MotoristaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<MotoristaResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
}

package edu.tcc.backend.controller;

import edu.tcc.backend.dto.gestor.GestorRequest;
import edu.tcc.backend.dto.gestor.GestorResponse;
import edu.tcc.backend.service.GestorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/gestores")
@RequiredArgsConstructor
public class GestorController {

    private final GestorService service;

    @PostMapping
    public ResponseEntity<GestorResponse> cadastrar (@Valid @RequestBody GestorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<GestorResponse>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }
}

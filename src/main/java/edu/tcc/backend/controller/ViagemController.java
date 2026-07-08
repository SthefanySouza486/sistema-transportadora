package edu.tcc.backend.controller;

import edu.tcc.backend.dto.viagem.ViagemRequest;
import edu.tcc.backend.dto.viagem.ViagemResponse;
import edu.tcc.backend.service.ViagemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/viagens")
@RequiredArgsConstructor
public class ViagemController {

    private final ViagemService service;

    @PostMapping
    public ResponseEntity<ViagemResponse> registrarViagem(@Valid @RequestBody ViagemRequest request){
        ViagemResponse response = service.registrarViagem(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

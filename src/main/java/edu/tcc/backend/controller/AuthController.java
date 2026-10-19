package edu.tcc.backend.controller;

import edu.tcc.backend.model.gestor.Gestor;
import edu.tcc.backend.model.motorista.Motorista;
import edu.tcc.backend.repository.GestorRepository;
import edu.tcc.backend.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private GestorRepository gestorRepository;

    @PostMapping("/login")
    public ResponseEntity<?> fazerLogin(@RequestBody Map<String, String> credentials) {
        String usuarioDigitado = credentials.get("usuario") != null ? credentials.get("usuario").trim() : "";
        String senhaDigitada = credentials.get("senha") != null ? credentials.get("senha").trim() : "";

        if (usuarioDigitado.isEmpty() || senhaDigitada.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Usuário e senha são obrigatórios."));
        }

        Optional<Gestor> gestor = gestorRepository.findByNomeIgnoreCase(usuarioDigitado);

        if (gestor.isPresent() && gestor.get().getSenha().equals(senhaDigitada)) {
            System.out.println("Login do Gestor aprovado: " + gestor.get().getNome());

            return ResponseEntity.ok(Map.of(
                    "mensagem", "Login aprovado",
                    "perfil", "GESTOR",
                    "nome", gestor.get().getNome(),
                    "id", gestor.get().getId(),
                    "token", "token-fake-gestor"
            ));
        }

        Optional<Motorista> motorista = motoristaRepository.findByNomeIgnoreCase(usuarioDigitado);
        if (motorista.isEmpty()) {
            motorista = motoristaRepository.findByEmailIgnoreCase(usuarioDigitado);
        }

        if (motorista.isPresent() && motorista.get().getSenha().equals(senhaDigitada)) {
            System.out.println("Login do Motorista aprovado: " + motorista.get().getNome());

            return ResponseEntity.ok(Map.of(
                    "mensagem", "Login aprovado",
                    "perfil", "MOTORISTA",
                    "nome", motorista.get().getNome(),
                    "id", motorista.get().getId(),
                    "token", "token-fake-motorista"
            ));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "message", "Acesso Negado: Usuário ou senha incorretos."
        ));
    }
}

package edu.tcc.backend.controller;

import edu.tcc.backend.dto.login.LoginRequest;
import edu.tcc.backend.dto.login.LoginResponse;
import edu.tcc.backend.model.motorista.Motorista;
import edu.tcc.backend.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private MotoristaRepository repository;

    @PostMapping("/login")
    public ResponseEntity<?> fazerLogin(@RequestBody LoginRequest request) {
        try {
            String usuario = request.getUsuario() != null ? request.getUsuario().trim() : "";
            String senha = request.getSenha() != null ? request.getSenha().trim() : "";

            System.out.println("Tentativa de login: " + usuario);

            if ("gestor@silvasouza.com".equals(usuario) && "admin123".equals(senha)) {
                System.out.println("Login do gestor aprovado!");
                LoginResponse cracha = new LoginResponse("token-gestor", "GESTOR", 0L, "Administrador Geral");
                return ResponseEntity.ok(cracha);
            }

            System.out.println("Buscando usuario no banco: " + usuario);
            Optional<Motorista> motoristaBuscado = repository.findByEmail(usuario);

            if (motoristaBuscado.isPresent()) {
                Motorista motorista = motoristaBuscado.get();

                if (motorista.getSenha() != null && motorista.getSenha().equals(senha)) {
                    LoginResponse cracha = new LoginResponse(
                            "token-motorista-" + motorista.getId(),
                            motorista.getPerfil(),
                            motorista.getId(),
                            motorista.getNome()
                    );
                    return ResponseEntity.ok(cracha);
                }
            }

            return ResponseEntity.status(401).body("{\"erro\": \"E-mail ou senha inválidos\"}");
        } catch (Exception e) {
            System.err.println("Erro interno durante o login: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("{\"erro\": \"Erro interno no servidor: " + e.getMessage() + "\"}");
        }
    }
}

package com.delivery.fastorder.controller;

import com.delivery.fastorder.model.Usuario;
import com.delivery.fastorder.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        try {
            String email = credenciales.get("email");
            String password = credenciales.get("password");

            Usuario usuario = usuarioService.login(email, password);

            // Generamos un token real estructurado (Email + Rol + Timestamp) codificado en Base64
            String tokenData = usuario.getEmail() + ":" + usuario.getRol() + ":" + System.currentTimeMillis();
            String realToken = Base64.getEncoder().encodeToString(tokenData.getBytes());

            // Retornamos la estructura exacta que el script de Bash espera (.token o .accessToken)
            return ResponseEntity.ok(Map.of(
                    "token", realToken,
                    "accessToken", realToken,
                    "email", usuario.getEmail(),
                    "nombre", usuario.getNombre(),
                    "rol", usuario.getRol().name()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        }
    }
}
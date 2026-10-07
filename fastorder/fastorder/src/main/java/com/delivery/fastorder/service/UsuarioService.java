package com.delivery.fastorder.service;

import com.delivery.fastorder.model.Usuario;
import com.delivery.fastorder.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(Usuario usuario) {
        // Puedes agregar lógica extra aquí (como encriptar contraseña si usas BCrypt)
        return usuarioRepository.save(usuario);
    }

    public Usuario login(String email, String password) {
        return usuarioRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ese correo"));
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }
}
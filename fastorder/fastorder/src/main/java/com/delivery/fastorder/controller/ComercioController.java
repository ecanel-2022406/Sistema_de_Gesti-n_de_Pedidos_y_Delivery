package com.delivery.fastorder.controller;

import com.delivery.fastorder.model.Comercio;
import com.delivery.fastorder.repository.ComercioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comercios")
public class ComercioController {

    @Autowired
    private ComercioRepository comercioRepository;

    @GetMapping
    public List<Comercio> listarComercios() {
        return comercioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Comercio> crearComercio(@RequestBody Comercio comercio) {
        Comercio nuevoComercio = comercioRepository.save(comercio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoComercio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comercio> obtenerComercioPorId(@PathVariable Long id) {
        return comercioRepository.findById(id)
                .map(comercio -> ResponseEntity.ok().body(comercio))
                .orElse(ResponseEntity.notFound().build());
    }
}
package com.delivery.fastorder.service;

import com.delivery.fastorder.model.Comercio;
import com.delivery.fastorder.repository.ComercioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComercioService {

    @Autowired
    private ComercioRepository comercioRepository;

    public List<Comercio> listarComercios() {
        return comercioRepository.findAll();
    }

    public Optional<Comercio> obtenerComercioPorId(Long id) {
        return comercioRepository.findById(id);
    }

    public Comercio guardarComercio(Comercio comercio) {
        return comercioRepository.save(comercio);
    }

    public void eliminarComercio(Long id) {
        comercioRepository.deleteById(id);
    }
}
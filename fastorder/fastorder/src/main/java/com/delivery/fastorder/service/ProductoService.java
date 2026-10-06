package com.delivery.fastorder.service;

import com.delivery.fastorder.model.Producto;
import com.delivery.fastorder.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }
}
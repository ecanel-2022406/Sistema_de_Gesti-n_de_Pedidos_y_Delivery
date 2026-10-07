package com.delivery.fastorder.repository;

import com.delivery.fastorder.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByComercioIdAndDisponibleTrue(Long comercioId);
}
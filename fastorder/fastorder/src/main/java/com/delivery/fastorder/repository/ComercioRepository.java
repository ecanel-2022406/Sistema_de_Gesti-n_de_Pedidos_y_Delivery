package com.delivery.fastorder.repository;

import com.delivery.fastorder.model.Comercio;
import com.delivery.fastorder.model.enums.CategoriaComercio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComercioRepository extends JpaRepository<Comercio, Long> {
    List<Comercio> findByAbiertoTrue();
    List<Comercio> findByCategoriaAndAbiertoTrue(CategoriaComercio categoria);
}
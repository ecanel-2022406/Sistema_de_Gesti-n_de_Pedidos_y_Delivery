package com.delivery.fastorder.model;

import com.delivery.fastorder.model.enums.CategoriaComercio;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comercios")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Comercio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private CategoriaComercio categoria;

    private String direccion;
    private boolean abierto;
}
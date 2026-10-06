package com.delivery.fastorder.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comercio_id", nullable = false)
    private Comercio comercio;

    private String nombre;
    private Double precio;
    private Integer stock;
    private boolean disponible;
}
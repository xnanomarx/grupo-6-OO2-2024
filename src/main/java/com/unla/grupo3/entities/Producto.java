package com.unla.grupo3.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "codigo", unique = true, nullable = false, length = 45)
    private String codigo;

    @Column(name = "nombre", unique = false, nullable = false, length = 45)
    private String nombre;

    @Column(name = "descripcion", unique = false, nullable = false, length = 45)
    private String descripcion;

    @Column(name = "costo", unique = false, nullable = false)
    private double costo;

    @Column(name = "precioVenta", unique = false, nullable = false)
    private double precioVenta;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Producto(String codigo, String nombre, String descripcion, double costo, double precioVenta) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.precioVenta = precioVenta;
    }

}

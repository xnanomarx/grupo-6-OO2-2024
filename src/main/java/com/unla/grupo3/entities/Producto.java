package com.unla.grupo3.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;

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
}

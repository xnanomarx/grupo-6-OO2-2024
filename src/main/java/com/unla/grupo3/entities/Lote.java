package com.unla.grupo3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.unla.grupo3.entities.Almacen;

import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "fechaRecepcion", nullable = false)
    private LocalDate fechaRecepcion;

    @Column(name = "proveedor", nullable = false)
    private String proveedor;

    @Column(name = "precioCompra", nullable = false)
    private double precioCompra;

    @ManyToOne
    @JoinColumn(name = "almacen_id", referencedColumnName = "id")
    private Almacen almacen;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    private Producto producto;

}

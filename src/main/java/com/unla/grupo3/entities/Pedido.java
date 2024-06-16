package com.unla.grupo3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "usuario", nullable = false)
    private String usuario;

    @Column(name = "cantFaltante", nullable = false)
    private int cantFaltante;

    @Column(name = "proveedor", nullable = false)
    private String proveedor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    private Producto producto;

}

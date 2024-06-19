package com.unla.grupo3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
public  class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    private Producto producto;

    @Column(name = "cantExistente", unique = false, nullable = false)
    private int cantExistente;

    @Column(name = "cantMinima", unique = false, nullable = false)
    private int cantMinima;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Stock(Producto producto, int cantExistente, int cantMinima) {
        this.producto = producto;
        this.cantExistente = cantExistente;
        this.cantMinima = cantMinima;
    }
}

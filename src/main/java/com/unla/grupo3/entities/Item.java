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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "item")
    private Venta venta;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto producto;

    @Column(name = "cantidad", unique = false, nullable = false)
    private int cantidad;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

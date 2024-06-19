package com.unla.grupo3.repositories;

import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable> {

    @Query("SELECT p FROM Producto p WHERE p.codigo = (:codigo)")
    public abstract Producto findByCodigo(@Param("codigo") String codigo);

    @Query("SELECT COUNT(v) FROM Venta v")
    public abstract int contarVentas();

    @Modifying
    @Transactional
    @Query("UPDATE Producto p SET p.codigo = :codigo, p.nombre = :nombre, p.descripcion = :descripcion, p.costo = :costo, p.precioVenta = :precioVenta WHERE p.id = :id")
    void actualizarProducto(@Param("id") int id, @Param("codigo") String codigo, @Param("nombre") String nombre, @Param("descripcion") String descripcion, @Param("costo") double costo, @Param("precioVenta") double precioVenta);


}
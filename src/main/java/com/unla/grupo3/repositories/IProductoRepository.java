package com.unla.grupo3.repositories;

import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable> {

    @Query("SELECT p FROM Producto p WHERE p.codigo = (:codigo)")
    public abstract Producto findByCodigo(@Param("codigo") String codigo);


}
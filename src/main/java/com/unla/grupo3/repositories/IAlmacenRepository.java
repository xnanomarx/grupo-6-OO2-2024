package com.unla.grupo3.repositories;

import com.unla.grupo3.entities.Almacen;
import com.unla.grupo3.entities.Pedido;
import com.unla.grupo3.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("almacenRepository")
public interface IAlmacenRepository extends JpaRepository<Almacen, Serializable> {

    @Query("SELECT a FROM Almacen a WHERE a.id = (:id)")
    public abstract Almacen findById(@Param("id") int id);



}

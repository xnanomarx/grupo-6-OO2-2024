package com.unla.grupo3.repositories;

import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository("stockRepository")
public interface IStockRepository extends JpaRepository<Stock, Serializable> {
    Stock findByProducto(Producto producto);

    @Modifying
    @Transactional
    @Query("UPDATE Stock s SET s.cantExistente = (:cantExistente) WHERE s.id = :id")
    void actualizarCantidadStock(@Param("id") int id, @Param("cantExistente") int cantExistente);


    @Query("SELECT s FROM Stock s ORDER BY s.cantExistente DESC")
    List<Stock> findProductoConMasStock();

    @Query("SELECT s FROM Stock s JOIN FETCH s.producto")
    public abstract List<Stock> traerStocksConProducto();

    @Query("SELECT s FROM Stock s JOIN FETCH s.producto where s.id = (:id)")
    public abstract Stock traerPorId(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE Stock s SET s.cantExistente = :cantExistente, s.cantMinima = :cantMinima WHERE s.id = :id")
    void actualizarStock(@Param("id") int id, @Param("cantExistente") int cantExistente, @Param("cantMinima") int cantMinima);


}
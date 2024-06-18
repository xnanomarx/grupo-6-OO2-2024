package com.unla.grupo3.repositories;

import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository("stockRepository")
public interface IStockRepository extends JpaRepository<Stock, Serializable> {
    Stock findByProducto(Producto producto);

}

package com.unla.grupo3.repositories;

import com.unla.grupo3.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("ventaRepository")
public interface IVentaRepository extends JpaRepository<Venta, Serializable> {


}

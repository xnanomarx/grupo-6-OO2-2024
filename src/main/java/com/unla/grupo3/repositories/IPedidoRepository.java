package com.unla.grupo3.repositories;

import com.unla.grupo3.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPedidoRepository extends JpaRepository<Pedido, Long> {
    // Aquí puedes agregar métodos personalizados de consulta si es necesario
}

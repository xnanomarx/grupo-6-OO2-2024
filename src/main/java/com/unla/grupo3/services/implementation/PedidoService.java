package com.unla.grupo3.services.implementation;

import com.unla.grupo3.entities.Pedido;
import com.unla.grupo3.repositories.IPedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final IPedidoRepository IPedidoRepository;

    public PedidoService(IPedidoRepository IPedidoRepository) {
        this.IPedidoRepository = IPedidoRepository;
    }

    public Pedido savePedido(Pedido pedido) {
        return IPedidoRepository.save(pedido);
    }

    // Otros métodos según sea necesario, como buscar pedidos, eliminar, etc.
}

package com.unla.grupo3.services.implementation;

import com.unla.grupo3.entities.Pedido;
import com.unla.grupo3.repositories.IPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service("pedidoService")
public class PedidoService {

    @Autowired
    private IPedidoRepository repository;
    @Autowired
    private ProductoService service;

    public PedidoService(IPedidoRepository repository) {
        this.repository = repository;
    }

    public void guardarPedido(int productId, String nombre, int cantidad, String proveedor){

        Pedido pedido = new Pedido();
        pedido.setProducto(service.encontrarPorId(productId));
        pedido.setProveedor(proveedor);
        pedido.setUsuario(nombre);
        pedido.setCantFaltante(cantidad);
    }

}

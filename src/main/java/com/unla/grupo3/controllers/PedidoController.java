package com.unla.grupo3.controllers;

import com.unla.grupo3.entities.Pedido;
import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.services.implementation.PedidoService;
import com.unla.grupo3.services.implementation.ProductoService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PedidoController {

    private final PedidoService pedidoService;
    private final ProductoService productoService;

    public PedidoController(PedidoService pedidoService, ProductoService productoService) {
        this.pedidoService = pedidoService;
        this.productoService = productoService;
    }

    @GetMapping("/producto/{id}/pedido-aprovisionamiento")
    public String showPedidoForm(@PathVariable("id") Long id, Model model) {
        Producto producto = productoService.findById(id); // Obtener el producto por su ID
        Pedido pedido = new Pedido();
        pedido.setProducto(producto); // Establecer el producto en el pedido
        model.addAttribute("pedido", pedido);
        return "pedido-aprovisionamiento"; // Nombre de la vista HTML
    }
}

package com.unla.grupo3.controllers;

import com.unla.grupo3.services.implementation.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PedidoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/pedido")
    public String showPedidoForm(@RequestParam("productId") Long productId, Model model) {
        // Cargar el producto relacionado usando el productId si es necesario
        model.addAttribute("productId", productId);
        return "pedido";
    }

    @PostMapping("/pedido/realizar")
    public String realizarPedido(@RequestParam("productId") Long productId,
                                 @RequestParam("nombre") String nombre,
                                 @RequestParam("cantidad") int cantidad,
                                 @RequestParam("proveedor") String proveedor) {
        // Lógica para procesar el pedido
        // ...
        return "redirect:/confirmacionPedido"; // Redirigir a una página de confirmación o similar
    }
}

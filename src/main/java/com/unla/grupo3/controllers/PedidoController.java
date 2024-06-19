package com.unla.grupo3.controllers;

import com.unla.grupo3.entities.Pedido;
import com.unla.grupo3.services.implementation.LoteService;
import com.unla.grupo3.services.implementation.PedidoService;
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
    private PedidoService servicePedido;
    @Autowired
    private LoteService loteService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/pedido")
    public String showPedidoForm(@RequestParam("productId") int productId, Model model) {
        // Cargar el producto relacionado usando el productId si es necesario
        model.addAttribute("productId", productId);
        return "/pedido/pedido";
    }

    @PostMapping("/pedido/realizar")
    public String realizarPedido(@RequestParam("productId") int productId,
                                 @RequestParam("nombre") String nombre,
                                 @RequestParam("cantidad") int cantidad,
                                 @RequestParam("proveedor") String proveedor) {

        servicePedido.guardarPedido(productId, nombre, cantidad, proveedor);
        loteService.guardarLote(productId, cantidad, proveedor);

        return "/pedido/confirmarPedido";
    }


}

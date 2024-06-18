package com.unla.grupo3.controllers;

import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.entities.Stock;
import com.unla.grupo3.services.implementation.ProductoService;
import com.unla.grupo3.services.implementation.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;


    @GetMapping("/register")
    public String mostrarFormularioRegistro() {
        return "producto/initialRegister";
    }

    @PostMapping("/guardar")
    public RedirectView guardarProducto(@RequestParam("nombre") String nombre,
                                        @RequestParam("descripcion") String descripcion,
                                        @RequestParam("costo") double costo,
                                        @RequestParam("precioVenta") double precioVenta) {
        productoService.guardarProducto(nombre, descripcion, costo, precioVenta);
        return new RedirectView("/producto/register");
    }
    

}

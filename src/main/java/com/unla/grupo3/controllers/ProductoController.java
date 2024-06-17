package com.unla.grupo3.controllers;

import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.helpers.ViewRouteHelper;
import com.unla.grupo3.services.implementation.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/register")
    public String mostrarFormularioRegistro() {
        System.out.println("Mostrando formulario de registro de producto");
        return "producto/initialRegister";
    }


    @PostMapping("/guardar")
    public RedirectView guardarProducto(@RequestParam("nombre") String nombre,
                                        @RequestParam("descripcion") String descripcion,
                                        @RequestParam("costo") double costo,
                                        @RequestParam("precioVenta") double precioVenta) {
        // Crear objeto Producto con los datos recibidos
        Producto producto = new Producto();
        producto.setCodigo(productoService.generarCodigoUnico());
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setCosto(costo);
        producto.setPrecioVenta(precioVenta);

        // Guardar el producto en la base de datos usando el servicio
        productoService.guardarProducto(producto);

        // Redirigir a una página de confirmación o a donde sea necesario
        return new RedirectView("/producto/register");
    }
}

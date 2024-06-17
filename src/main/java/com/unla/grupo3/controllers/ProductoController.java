package com.unla.grupo3.controllers;

import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.entities.Stock;
import com.unla.grupo3.services.implementation.ProductoService;
import com.unla.grupo3.services.implementation.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private StockService stockService;


    @GetMapping("/register")
    public String mostrarFormularioRegistro() {
        return "producto/initialRegister";
    }

    @PostMapping("/guardar")
    public RedirectView guardarProducto(@RequestParam("nombre") String nombre,
                                        @RequestParam("descripcion") String descripcion,
                                        @RequestParam("costo") double costo,
                                        @RequestParam("precioVenta") double precioVenta) {

        //creacion del objeto
        Producto producto = new Producto();
        producto.setCodigo(productoService.generarCodigoUnico());
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setCosto(costo);
        producto.setPrecioVenta(precioVenta);

        productoService.guardarProducto(producto);

        return new RedirectView("/producto/register");
    }

    @GetMapping("/lista")
    public String mostrarListaProductos(Model model) {
        List<Stock> stocks = stockService.traerStocksOrdenados();
        model.addAttribute("stocks", stocks);
        return "producto/listaProductos";
    }


}

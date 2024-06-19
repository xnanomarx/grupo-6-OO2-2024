package com.unla.grupo3.controllers;


import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.entities.Stock;
import com.unla.grupo3.entities.User;
import com.unla.grupo3.entities.Venta;
import com.unla.grupo3.services.implementation.ProductoService;
import com.unla.grupo3.services.implementation.StockService;
import com.unla.grupo3.services.implementation.UserService;
import com.unla.grupo3.services.implementation.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private StockService stockService;

    @Autowired
    private UserService userService;

    @GetMapping("/venta")
    public String mostrarFormularioVenta(Model model){
        List<Stock> stocks = stockService.getStocks();
        model.addAttribute("stocks", stocks);
        return "venta/venta";
    }


    @GetMapping("/productos-con-stock")
    public List<Producto> getProductosConStock() {
        return productoService.getProductosConStock();
    }

    @GetMapping("/producto")
    public List<Producto> getAllProductos(){
        return productoService.getAllProductos();
    }

    @PostMapping("/realizarVenta")
    public String registrarVenta(@RequestParam("stock") Stock stock,
                                 @RequestParam("cantidad") int cantidad) {
        Venta venta = new Venta();

        ventaService.registrarVenta(venta, stock, cantidad);
        return "venta/confirmacion";
    }
}
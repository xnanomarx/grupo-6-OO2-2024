package com.unla.grupo3.controllers;


import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.entities.User;
import com.unla.grupo3.entities.Venta;
import com.unla.grupo3.services.implementation.ProductoService;
import com.unla.grupo3.services.implementation.VentaService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/venta")
    public String mostrarFormularioVenta(Model model){
        //lista de stock en vez de productos
        List<Producto> listaProductos = productoService.getAllProductos();
        model.addAttribute("listaHTML", listaProductos);
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

    @PostMapping("/ventaPost")
    public void registrarVenta(@RequestBody VentaRequest ventaRequest) {
        Venta venta = new Venta();
        venta.setUser(ventaRequest.getUser());
        venta.setFechaCompra(LocalDate.now());

        Producto producto = ventaRequest.getProducto();
        int cantidad = ventaRequest.getCantidad();

        ventaService.registrarVenta(venta, producto, cantidad);
    }
}

//Clase auxiliar para manejar la entrada de datos desde el cliente.
@Getter @Setter
class VentaRequest {
    private User user;
    private Producto producto;
    private int cantidad;
}

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

    @PostMapping("/baja/{id}")
    public RedirectView darDeBajaProducto(@PathVariable("id") int id) {
        stockService.eliminarStock(id);
        return new RedirectView("/producto/lista");
    }

    @GetMapping("/actualizar/{id}")
    public String mostrarFormularioActualizar(@PathVariable("id") int id, Model model) {
        Stock stock = stockService.traerPorId(id);
        String codigo = stock.getProducto().getCodigo();
        model.addAttribute("stock", stock);
        model.addAttribute("codigo", codigo);
        return "producto/actualizarProducto";
    }

    @PostMapping("/actualizar")
    public String actualizarProducto(@ModelAttribute("stock") Stock stock) {
        String codigo = stock.getProducto().getCodigo();
        stock.getProducto().setCodigo("no");
        Producto producto = new Producto(codigo, stock.getProducto().getNombre(), stock.getProducto().getDescripcion(), stock.getProducto().getCosto(), stock.getProducto().getPrecioVenta());
        Stock nuevoStock = new Stock(producto, stock.getCantExistente(), stock.getCantMinima());
        stockService.eliminarStock(stock.getId());

        stockService.guardarStock(nuevoStock);
        return "redirect:/producto/lista";
    }
    

}

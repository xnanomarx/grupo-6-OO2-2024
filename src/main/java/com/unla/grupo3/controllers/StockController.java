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
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/lista")
    public String mostrarListaProductos(Model model) {
        List<Stock> stocks = stockService.traerStocksOrdenados();
        model.addAttribute("stocks", stocks);
        return "producto/listaProductos";
    }

    @PostMapping("/baja/{id}")
    public RedirectView darDeBajaProducto(@PathVariable("id") int id) {
        stockService.eliminarStock(id);
        return new RedirectView("/stock/lista");
    }

    @GetMapping("/actualizar/{id}")
    public String mostrarFormularioActualizar(@PathVariable("id") int id, Model model) {
        Stock stock = stockService.traerPorId(id);
        int idProd = stock.getProducto().getId();
        String codigo = stock.getProducto().getCodigo();
        model.addAttribute("stock", stock);
        model.addAttribute("codigo", codigo);
        model.addAttribute("idProd", idProd);
        return "producto/actualizarProducto";
    }
    @PostMapping("/actualizar")
    public String actualizarProducto(@ModelAttribute("stock") Stock stock) {
        productoService.actualizarProducto(stock.getProducto());
        stockService.actualizarProducto(stock);
        return "redirect:/stock/lista";
    }


}

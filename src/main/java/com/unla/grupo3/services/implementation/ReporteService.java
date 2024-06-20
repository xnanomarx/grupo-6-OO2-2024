package com.unla.grupo3.services.implementation;

import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.entities.Stock;
import com.unla.grupo3.repositories.IProductoRepository;
import com.unla.grupo3.repositories.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reporteService")
public class ReporteService {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private StockService stockService;

    public Stock getProductoConMasStock(){
        List<Stock> productosConMasStock = stockService.encontrarProductoConMasStock();
        return productosConMasStock.isEmpty() ? null : productosConMasStock.get(0);
    }

    public int getTotalVentas(){
        return productoService.contarVentas();
    }
}

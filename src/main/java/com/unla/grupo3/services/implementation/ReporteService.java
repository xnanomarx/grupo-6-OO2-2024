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
    private IProductoRepository productoRepository;

    @Autowired
    private IStockRepository stockRepository;

    public Stock getProductoConMasStock(){
        //List<Producto> productosConMasStock = productoRepository.findProductoConMasStock();
        List<Stock> productosConMasStock = stockRepository.findProductoConMasStock();
        return productosConMasStock.isEmpty() ? null : productosConMasStock.get(0);
    }

    public int getTotalVentas(){
        return productoRepository.contarVentas();
    }
}

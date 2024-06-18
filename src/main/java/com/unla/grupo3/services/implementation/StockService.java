package com.unla.grupo3.services.implementation;

import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.entities.Stock;
import com.unla.grupo3.repositories.IProductoRepository;
import com.unla.grupo3.repositories.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stockService")
public class StockService {

    @Autowired
    private IStockRepository stockRepository;

    @Autowired
    private IProductoRepository productoRepository;

    public void actualizarCantidad(int id, int cantidad) {
        stockRepository.actualizarCantidadStock(id, cantidad);
    }

    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }



}
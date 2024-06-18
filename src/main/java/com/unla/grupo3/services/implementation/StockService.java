package com.unla.grupo3.services.implementation;

import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.entities.Stock;
import com.unla.grupo3.repositories.IProductoRepository;
import com.unla.grupo3.repositories.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stockService")
public class StockService {

    @Autowired
    private IStockRepository stockRepository;

    @Autowired
    private IProductoRepository productoRepository;

    public void actualizarStock(Producto producto, int cantidadVendida){
        Stock stock = stockRepository.findByProducto(producto);

        if(stock != null){
            int nuevaCantidad = stock.getCantExistente() - cantidadVendida;
            stock.setCantExistente(nuevaCantidad);
            stockRepository.save(stock);
        }
    }

}

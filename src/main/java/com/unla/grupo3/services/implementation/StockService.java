package com.unla.grupo3.services.implementation;

import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.entities.Stock;
import com.unla.grupo3.repositories.IProductoRepository;
import com.unla.grupo3.repositories.IStockRepository;
import jakarta.transaction.Transactional;
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

    private ProductoService productoService;

    public StockService(IStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> encontrarProductoConMasStock(){
        return stockRepository.findProductoConMasStock();
    }

    public Stock findByProducto(Producto producto){
        return stockRepository.findByProducto(producto);
    }

    public Stock guardarStock(Stock stock){
        return stockRepository.save(stock);
    }
}

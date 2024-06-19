package com.unla.grupo3.services.implementation;

import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.entities.Stock;
import com.unla.grupo3.repositories.IProductoRepository;
import com.unla.grupo3.repositories.IStockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("stockService")
public class StockService {

    @Autowired
    private IStockRepository stockRepository;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private IProductoRepository productoRepository;

    public StockService(IStockRepository stockRepository) {this.stockRepository = stockRepository;}

    public void actualizarCantidad(int id, int cantidad) {
        stockRepository.actualizarCantidadStock(id, cantidad);
    }
    public List<Stock> getStocks() {
        return stockRepository.findAll();
    }

    public List<Stock> traerStocksOrdenados() {
    private ProductoService productoService;

    public StockService(IStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

   /* public List<Stock> traerStocksOrdenados() {
        List<Stock> stocks = stockRepository.traerStocksConProducto();
        List<Stock> lowStock = new ArrayList<>();
        List<Stock> normalStock = new ArrayList<>();

        for (Stock stock : stocks) {
            if (stock.getCantExistente() < stock.getCantMinima()) {
                lowStock.add(stock);
            } else {
                normalStock.add(stock);
            }
        }

        lowStock.addAll(normalStock);
        return lowStock;
    }

    public void eliminarStock(int id) {
        stockRepository.deleteById(id);
    }

    public Stock traerPorId(int id) {
        return stockRepository.traerPorId(id);
    }

    public void guardarStock(Producto producto, int cantMinima){
        Stock stock = new Stock();
        stock.setProducto(producto);
        stock.setCantExistente(0);
        stock.setCantMinima(cantMinima);
        stockRepository.save(stock);
    }

    @Transactional
    public void actualizarProducto(Stock stock) {
        stockRepository.actualizarStock(stock.getId(), stock.getCantExistente(), stock.getCantMinima());
    }*/

   /* @Transactional
    public void actualizarProducto(Stock stock) {
        stockRepository.actualizarStock(stock.getId(), stock.getCantExistente(), stock.getCantMinima());
    }*/

    public List<Stock> encontrarProductoConMasStock(){
        return stockRepository.findProductoConMasStock();
    }

    public Stock findByProducto(Producto producto){
        return stockRepository.findByProducto(producto);
    }
}

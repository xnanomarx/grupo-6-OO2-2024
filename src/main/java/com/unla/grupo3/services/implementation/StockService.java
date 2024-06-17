package com.unla.grupo3.services.implementation;

import com.unla.grupo3.entities.Stock;
import com.unla.grupo3.repositories.IStockRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("stockService")
public class StockService {

    private com.unla.grupo3.repositories.IStockRepository stockRepository;

    public StockService(IStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock traerPorCodigo(String codigo){
        return stockRepository.findByCodigoAndFetchProductoEagerly(codigo);
    }

    public List<Stock> traerStocksConProducto(){
        return stockRepository.traerStocksConProducto();
    }

    public List<Stock> traerStocksOrdenados() {
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

}

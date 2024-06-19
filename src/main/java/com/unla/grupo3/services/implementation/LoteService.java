package com.unla.grupo3.services.implementation;

import com.unla.grupo3.entities.Lote;
import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.entities.Stock;
import com.unla.grupo3.repositories.ILoteRepository;
import com.unla.grupo3.repositories.IProductoRepository;
import com.unla.grupo3.repositories.IStockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("loteService")
public class LoteService {

    @Autowired
    private ILoteRepository loteRepository;

    @Autowired
    private IStockRepository stockRepository;

    @Autowired
    private StockService stockService;

    public List<Lote> getAllLotes(){
        return loteRepository.findAll();
    }

    public void actualizarStock(int loteId){
        Lote lote = loteRepository.findById(loteId).orElseThrow(() -> new RuntimeException("Lote no encontrado"));
        Stock stock = stockService.findByProducto(lote.getProducto());

        if (stock == null) {
            stock = new Stock();
            stock.setProducto(lote.getProducto());
            stock.setCantExistente(0);
            stock.setCantMinima(0);
        }

        stock.setCantExistente(stock.getCantExistente() + lote.getCantidad());
        stockService.guardarStock(stock);
    }

    public void borrarLoteActualizado(int loteId){
        loteRepository.deleteById(loteId);
    }


}

package com.unla.grupo3.services.implementation;

import com.unla.grupo3.entities.Lote;
import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.entities.Stock;
import com.unla.grupo3.repositories.ILoteRepository;
import com.unla.grupo3.repositories.IProductoRepository;
import com.unla.grupo3.repositories.IStockRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service("loteService")
public class LoteService {

    /*private ILoteRepository loteRepository;
    private IProductoRepository productoRepository;
    private IStockRepository stockRepository;

    public LoteService(ILoteRepository loteRepository) {this.loteRepository = loteRepository;}

    @Transactional
    public Lote registrarLote(Lote lote) {
        Producto producto = lote.getProducto();

        Stock stock = stockRepository.findByProducto(producto)
                .orElseThrow(() -> new IllegalArgumentException("Stock no encontrado para el producto"));

        stock.setCantExistente(stock.getCantExistente() + lote.getCantidad());
        stockRepository.save(stock);

        return loteRepository.save(lote);
    }*/
}

package com.unla.grupo3.services.implementation;

import com.unla.grupo3.entities.Item;
import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.entities.Stock;
import com.unla.grupo3.entities.Venta;
import com.unla.grupo3.repositories.IItemRepository;
import com.unla.grupo3.repositories.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ventaService")
public class VentaService {

    @Autowired
    private IVentaRepository ventaRepository;

    @Autowired
    private IItemRepository itemRepository;

    @Autowired
    private StockService stockService;

    public void registrarVenta(Venta venta, Stock stock, int cantidad) {
        //Validacion


        //Crear item de la venta y setearle los atributos
        Item item = new Item();
        item.setVenta(venta);
        item.setCantidad(cantidad);
        venta.setItem(item);

        //Actualizar stock del producto comprado por el usuario
        stockService.actualizarCantidad(stock.getId(), stock.getCantExistente()-cantidad);

        //Guardar la venta y el item
        ventaRepository.save(venta);
        itemRepository.save(item);
    }
}
package com.unla.grupo3.services.implementation;

import com.unla.grupo3.entities.*;
import com.unla.grupo3.repositories.IItemRepository;
import com.unla.grupo3.repositories.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("ventaService")
public class VentaService {

    @Autowired
    private IVentaRepository ventaRepository;

    @Autowired
    private IItemRepository itemRepository;

    @Autowired
    private StockService stockService;

    @Autowired
    private UserService userService;

    public void registrarVenta(Venta venta, Stock stock, int cantidad) {
        //Crear item de la venta y setearle los atributos
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        venta.setUser(userService.findByUsername(user.getUsername()));
        venta.setFechaCompra(LocalDate.now());
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
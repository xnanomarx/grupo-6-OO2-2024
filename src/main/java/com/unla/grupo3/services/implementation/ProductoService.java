package com.unla.grupo3.services.implementation;

import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.entities.Stock;
import com.unla.grupo3.repositories.IProductoRepository;
import com.unla.grupo3.repositories.IStockRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("prpductoService")
public class ProductoService {

    private IProductoRepository productoRepository;
    private IStockRepository stockRepository;

    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto loadProductoByCodigo(String codigo) {
        Producto producto = productoRepository.findByCodigo(codigo);
        return buildProducto(producto);
    }

    private Producto buildProducto(Producto p) {
        return new Producto(generarCodigoUnico(), p.getNombre(), p.getDescripcion(), p.getCosto(), p.getPrecioVenta());
    }

    public String generarCodigoUnico() {
        String nuevoCodigo;
        do {
            nuevoCodigo = "PROD-" + UUID.randomUUID().toString().substring(0, 8);
        } while (!esCodigoUnico(nuevoCodigo));

        return nuevoCodigo;
    }

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public List<Producto> getProductosConStock(){
        return stockRepository.findAll().stream()
                .filter(stock -> stock.getCantExistente() > 0)
                .map(Stock::getProducto)
                .distinct()
                .collect(Collectors.toList());

    }

    private boolean esCodigoUnico(String codigo) {

        return productoRepository.findByCodigo(codigo) == null;
    }

    public void guardarProducto(Producto producto) {
        productoRepository.save(producto);
    }
}

package com.unla.grupo3.services.implementation;

import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.repositories.IProductoRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("productoService")
public class ProductoService {

    private IProductoRepository productoRepository;

    public ProductoService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
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

    private boolean esCodigoUnico(String codigo) {

        return productoRepository.findByCodigo(codigo) == null;
    }

    public void guardarProducto(String nombre, String descripcion, double costo, double precioVenta) {
        Producto producto = new Producto();
        producto.setCodigo(generarCodigoUnico());
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setCosto(costo);
        producto.setPrecioVenta(precioVenta);

        productoRepository.save(producto);
    }


    public void actualizarProducto(Producto producto){
        System.out.println(producto.getId());
        productoRepository.actualizarProducto(producto.getId(), producto.getCodigo(), producto.getNombre(), producto.getDescripcion(), producto.getCosto(), producto.getPrecioVenta());
    }

}

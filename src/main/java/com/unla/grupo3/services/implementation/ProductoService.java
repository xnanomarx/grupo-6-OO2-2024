package com.unla.grupo3.services.implementation;

import com.unla.grupo3.entities.Producto;
import com.unla.grupo3.entities.Stock;
import com.unla.grupo3.repositories.IProductoRepository;
import com.unla.grupo3.repositories.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("productoService")
public class ProductoService {

    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    private IStockRepository stockRepository;

    public ProductoService() {
        this.productoRepository = productoRepository;
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

    public String generarCodigoUnico() {
        String nuevoCodigo;
        do {
            nuevoCodigo = "PROD-" + UUID.randomUUID().toString().substring(0, 8);
        } while (!esCodigoUnico(nuevoCodigo));

        return nuevoCodigo;
    }
    private boolean esCodigoUnico(String codigo) {return productoRepository.findByCodigo(codigo) == null;}

    public Producto guardarProducto(String nombre, String descripcion, double costo, double precioVenta) {
        Producto producto = new Producto();
        producto.setCodigo(generarCodigoUnico());
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setCosto(costo);
        producto.setPrecioVenta(precioVenta);
        return productoRepository.save(producto);
    }


    public void actualizarProducto(Producto producto){
        System.out.println(producto.getId());
        productoRepository.actualizarProducto(producto.getId(), producto.getCodigo(), producto.getNombre(), producto.getDescripcion(), producto.getCosto(), producto.getPrecioVenta());
    }

    public Producto encontrarPorId(int id){
        return productoRepository.findById(id);
    }

    public int contarVentas(){
        return productoRepository.contarVentas();
    }

}

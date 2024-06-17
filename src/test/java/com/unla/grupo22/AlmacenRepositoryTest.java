package com.unla.grupo22;

import com.unla.grupo3.entities.Almacen;
import com.unla.grupo3.entities.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AlmacenRepositoryTest {

    @Autowired
    private IAlmacenRepository almacenRepository;

    @Test
    @Transactional
    public void testAgregarAlmacenesConProductos() {
        // Crear productos
        Producto producto1 = new Producto("001", "Producto 1", "Descripción del Producto 1", 100.0, 150.0);
        Producto producto2 = new Producto("002", "Producto 2", "Descripción del Producto 2", 200.0, 250.0);
        Producto producto3 = new Producto("003", "Producto 3", "Descripción del Producto 3", 300.0, 350.0);

        // Crear almacenes
        Almacen almacen1 = new Almacen();
        almacen1.setProducto(producto1);
        almacen1.setCantidadExistente(50);
        almacen1.setCantMinima(20);

        Almacen almacen2 = new Almacen();
        almacen2.setProducto(producto2);
        almacen2.setCantidadExistente(10);
        almacen2.setCantMinima(15);

        Almacen almacen3 = new Almacen();
        almacen3.setProducto(producto3);
        almacen3.setCantidadExistente(5);
        almacen3.setCantMinima(10);

        // Guardar almacenes
        almacenRepository.save(almacen1);
        almacenRepository.save(almacen2);
        almacenRepository.save(almacen3);


        assertEquals(3, almacenRepository.findAll().size());
    }
}

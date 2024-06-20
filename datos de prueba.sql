-- create schema sistemaStock;

INSERT INTO almacen(id) values (1);

INSERT INTO producto (id, codigo, nombre, descripcion, costo, precio_venta) VALUES
(1, 'PROD-4d6bb46c', 'Harina', 'Harina de trigo 000', 800, 1000),
(2, 'PROD-d3737cf8', 'Lata de tomate', 'Late de tomate S&P', 500, 800),
(3, 'PROD-75705d00', 'Fideos largos', 'Fideos largos Matarazzo', 1300, 1700);

INSERT INTO stock (id, id_producto, cant_existente, cant_minima) VALUES
(1, 1, 40, 20),
(2, 2, 80, 60),
(3, 3, 10, 30);

INSERT INTO lote (id, cantidad, fecha_recepcion, precio_compra, proveedor, almacen_id, id_producto) VALUES
(1, 200, now(), 160000, "Oscar", 1, 1),
(2, 100, now(), 50000, "Gustavo", 1, 2);


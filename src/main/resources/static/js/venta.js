document.addEventListener('DOMContentLoaded', function () {
    // Cargar los productos disponibles con stock
    fetch('/productos-con-stock')
        .then(response => response.json())
        .then(data => {
            const productoSelect = document.getElementById('producto');
            data.forEach(producto => {
                const option = document.createElement('option');
                option.value = producto.idProducto;
                option.textContent = producto.nombre;
                productoSelect.appendChild(option);
            });
        });

    // Manejar el envío del formulario
    document.getElementById('ventaForm').addEventListener('submit', function (e) {
        e.preventDefault();

        const productoId = document.getElementById('producto').value;
        const cantidad = document.getElementById('cantidad').value;

        const ventaRequest = {
            user: {
                // Suponiendo que el usuario está autenticado y su información está disponible
                idUser: 1 // Cambia esto según sea necesario
            },
            fechaCompra: new Date().toISOString().split('T')[0],
            producto: {
                idProducto: productoId
            },
            cantidad: cantidad
        };

        fetch('/ventas', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(ventaRequest)
        })
            .then(response => {
                if (response.ok) {
                    alert('Venta registrada con éxito');
                } else {
                    alert('Error al registrar la venta');
                }
            });
    });
});
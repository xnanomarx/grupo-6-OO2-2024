document.addEventListener('DOMContentLoaded', function () {
    // Cargar lista de productos desde el backend
    fetch('/api/productos')
        .then(response => response.json())
        .then(data => {
            const productoSelect = document.getElementById('producto');
            data.forEach(producto => {
                const option = document.createElement('option');
                option.value = producto.idProducto;
                option.text = producto.nombre;
                productoSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error al cargar los productos:', error);
        });

    // Manejar el envío del formulario
    document.getElementById('loteForm').addEventListener('submit', function (event) {
        event.preventDefault();

        const productoId = document.getElementById('producto').value;
        const cantidad = document.getElementById('cantidad').value;
        const proveedor = document.getElementById('proveedor').value;

        fetch('/api/lotes', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                producto: { idProducto: productoId },
                cantidad: cantidad,
                proveedor: proveedor,
                fechaRecepcion: new Date().toISOString().split('T')[0] // Fecha actual en formato YYYY-MM-DD
            })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la respuesta del servidor');
                }
                return response.json();
            })
            .then(data => {
                alert('Lote registrado con éxito');
                document.getElementById('loteForm').reset();
            })
            .catch(error => {
                alert('Error al registrar el lote: ' + error.message);
            });
    });
});
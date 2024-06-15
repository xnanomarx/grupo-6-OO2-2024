document.addEventListener('DOMContentLoaded', function() {

    const form = document.getElementById('productForm');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        // Obtener los valores del formulario
        const nombre = document.getElementById('nombre').value;
        const descripcion = document.getElementById('descripcion').value;
        const costo = parseFloat(document.getElementById('costo').value);
        const precioVenta = parseFloat(document.getElementById('precioVenta').value);

        // Validar que los campos no estén vacíos y sean válidos
        if (nombre.trim() === '' || descripcion.trim() === '' || isNaN(costo) || isNaN(precioVenta)) {
            alert('Por favor completa todos los campos correctamente.');
            return;
        }

        // Crear objeto con los datos del producto (sin el campo código)
        const producto = {
            nombre: nombre,
            descripcion: descripcion,
            costo: costo,
            precioVenta: precioVenta
        };

        // Enviar los datos del producto al backend mediante una solicitud fetch
        fetch('/api/productos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(producto)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al guardar el producto.');
            }
            return response.json(); // Opcional: puedes manejar la respuesta del servidor si es necesario
        })
        .then(data => {
            console.log('Producto guardado exitosamente:', data); // Opcional: puedes manejar la respuesta del servidor si es necesario
            alert('Producto guardado exitosamente.');
            form.reset(); // Limpiar el formulario después de enviar
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Hubo un error al guardar el producto. Por favor intenta nuevamente.');
        });
    });
});

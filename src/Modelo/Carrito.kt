package Modelo

// Clase que representa un carrito de compras
class Carrito {
    // Lista interna de items en el carrito
    private val items = mutableListOf<CarritoItem>()

    // ───────────── Agregar un producto al carrito ─────────────
    fun agregarProducto(producto: Producto, cantidad: Int) {
        if (producto.cantidadDisponible >= cantidad) {
            val carritoItem = items.find { it.producto.id == producto.id }
            if (carritoItem != null) {
                // Si el producto ya está en el carrito, suma la cantidad
                carritoItem.cantidad += cantidad
            } else {
                // Si no está, lo agrega como nuevo item
                items.add(CarritoItem(producto, cantidad))
            }
            // Reducir la cantidad disponible del producto
            producto.cantidadDisponible -= cantidad
            println("Producto agregado al carrito: ${producto.nombre}, Cantidad: $cantidad")
        } else {
            throw IllegalArgumentException("Cantidad solicitada no disponible para el producto: ${producto.nombre}")
        }
    }

    // ───────────── Eliminar un producto del carrito por ID ─────────────
    fun eliminarProductoPorId(id: Int) {
        val item = items.find { it.producto.id == id }
        if (item != null) {
            items.remove(item)
            // Restaurar la cantidad disponible del producto
            item.producto.cantidadDisponible += item.cantidad
            println("Producto eliminado del carrito: ${item.producto.nombre}")
        } else {
            throw IllegalArgumentException("Producto no encontrado en el carrito con ID: $id")
        }
    }

    // ───────────── Obtener la lista de productos en el carrito ─────────────
    fun mostrarCarrito(): List<CarritoItem> {
        return items
    }

    // ───────────── Generar factura a partir del carrito ─────────────
    fun generarFactura(): Factura {
        val subtotal = items.sumOf { it.calcularPrecioTotal() }
        val porcentajeImpuesto = 0.13
        val impuestos = subtotal * porcentajeImpuesto
        val totalGeneral = subtotal + impuestos
        val fecha = java.time.LocalDate.now().toString()
        return Factura(
            items = items,
            subtotal = subtotal,
            porcentajeImpuesto = porcentajeImpuesto,
            impuestos = impuestos,
            total = totalGeneral,
            fecha = fecha
        )
    }

    // ───────────── Limpiar todos los productos del carrito ─────────────
    fun limpiarCarrito() {
        items.clear()
        // println("El carrito ha sido limpiado.") // opcional para depuración
    }
}


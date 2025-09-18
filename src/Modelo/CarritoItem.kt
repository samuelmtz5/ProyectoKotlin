package Modelo

data class CarritoItem(val producto: Producto, var cantidad: Int) {
    fun calcularPrecioTotal(): Double {
        return producto.precio * cantidad
    }

    fun disminuirCantidad(cantidadARestar: Int) {
        if (cantidadARestar <= cantidad) {
            cantidad -= cantidadARestar
        } else {
            throw IllegalArgumentException("No se puede seguir eliminando.")
        }
    }

    fun aplicarDescuento(descuentoPorcentaje: Double) {
        val descuento = producto.precio * (descuentoPorcentaje / 100)
        val precioConDescuento = producto.precio - descuento
        println("Precio con el descuento: $precioConDescuento")
    }
}


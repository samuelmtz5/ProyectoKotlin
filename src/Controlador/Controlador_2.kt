package Controlador

import Modelo.Carrito
import Vista.Vista
import Modelo.obtenerProductos

object Controlador_2 {
    private val carrito = Carrito()
    private val productos = obtenerProductos()

    fun iniciar() = mostrarMenuPrincipal()

    //  Menú principal
    private fun mostrarMenuPrincipal() {
        while (true) {
            Vista.mostrarMensaje(
                """
                ╔══════════════════════════════════════════════╗
                ║        💻 Venta de Compus S.A de C.V 💻      ║
                ╠══════════════════════════════════════════════╣
                ║ [1] Ver Productos                            ║
                ║ [2] Agregar Producto                         ║
                ║ [3] Eliminar Producto                        ║
                ║ [4] Ver Carrito                              ║
                ║ [5] Salir                                    ║
                ╚══════════════════════════════════════════════╝
                Opción: 
                """.trimIndent()
            )
            when (readLine()) {
                "1" -> mostrarProductos()
                "2" -> agregarProducto()
                "3" -> eliminarProducto()
                "4" -> verCarrito()
                "5" -> return Vista.mostrarMensaje("👋 Saliendo de la aplicación...")
                else -> Vista.mostrarMensaje("⚠️ Opción no válida.\n")
            }
        }
    }

    //  Mostrar productos como tabla
    private fun mostrarProductos() {
        Vista.mostrarMensaje("\n📦 Catálogo de Productos\n")
        Vista.mostrarMensaje("┌─────┬──────────────────────────────┬───────────┬───────┐")
        Vista.mostrarMensaje("│ ID  │ Producto                     │ Precio    │ Stock │")
        Vista.mostrarMensaje("├─────┼──────────────────────────────┼───────────┼───────┤")
        productos.forEach {
            Vista.mostrarMensaje(
                String.format("│ %-3d │ %-28s │ $%-8.2f │ %-5d │",
                    it.id, it.nombre, it.precio, it.cantidadDisponible)
            )
        }
        Vista.mostrarMensaje("└─────┴──────────────────────────────┴───────────┴───────┘")
        Vista.mostrarMensaje("\n(Enter para regresar al menú principal)")
        readLine()
    }

    //  Agregar producto
    private fun agregarProducto() {
        val id = pedirNumero("Ingrese ID del producto:")
        val cantidad = pedirNumero("Ingrese cantidad:")
        val producto = productos.find { it.id == id }

        if (producto != null && cantidad > 0) {
            try {
                carrito.agregarProducto(producto, cantidad)
                Vista.mostrarMensaje("✔ Producto agregado al carrito.\n")
            } catch (e: IllegalArgumentException) {
                Vista.mostrarMensaje("❌ Error: ${e.message}\n")
            }
        } else Vista.mostrarMensaje("⚠️ Producto no válido.\n")
    }

    // Eliminar producto
    private fun eliminarProducto() {
        val id = pedirNumero("Ingrese ID del producto a eliminar:")
        try {
            carrito.eliminarProductoPorId(id)
            Vista.mostrarMensaje("✔ Producto eliminado.\n")
        } catch (e: IllegalArgumentException) {
            Vista.mostrarMensaje("❌ Error: ${e.message}\n")
        }
    }

    //  Ver carrito
    private fun verCarrito() {
        val items = carrito.mostrarCarrito()
        if (items.isEmpty()) {
            Vista.mostrarMensaje("🛒 El carrito está vacío.\n")
            return
        }
        Vista.mostrarProductos(items)

        Vista.mostrarMensaje("1. Seguir comprando\n2. Confirmar compra")
        when (readLine()) {
            "2" -> {
                val factura = carrito.generarFactura()
                Vista.mostrarFactura(factura)
                carrito.limpiarCarrito()
            }
        }
    }

    //  Auxiliar
    private fun pedirNumero(msg: String): Int {
        Vista.mostrarMensaje(msg)
        return readLine()?.toIntOrNull() ?: -1
    }
}

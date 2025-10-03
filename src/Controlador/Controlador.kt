package Controlador

import Modelo.Carrito
import Modelo.Producto
import Vista.Vista
import Modelo.obtenerProductos

object Controlador {
    private val carrito = Carrito()
    private val productos = obtenerProductos()
// Método principal que inicia la aplicación
    fun iniciar() {
        mostrarMenuPrincipal()
    }
    // Menú principal de la aplicación
    private fun mostrarMenuPrincipal() {
        while (true) {
            // Se muestra el menú con opciones
            Vista.mostrarMensaje(
                """
╔══════════════════════════════════════════════════════════════╗
║             💻 Venta loka de Compus S.A de C.V 💻            ║
╠══════════════════════════════════════════════════════════════╣
║   [1] 🖥️ Ver Productos                                       ║
║   [2] 🖱️ Agregar Producto al Carrito                         ║
║   [3] ⌨️ Eliminar Producto del Carrito                       ║
║   [4] 🛒 Ver Carrito                                         ║
║   [5] ❌ Salir                                               ║
╚══════════════════════════════════════════════════════════════╝
==> Ponga una opción:
                """.trimIndent()
            )

            when (readLine()) {
                "1" -> mostrarProductos()
                "2" -> agregarProducto()
                "3" -> eliminarProducto()
                "4" -> verCarrito()
                "5" -> {
                    Vista.mostrarMensaje("Saliendo de la aplicación.")
                    System.exit(0)
                }
                else -> Vista.mostrarMensaje("Opción no válida. Intente de nuevo.\n")
            }
        }
    }
 // Muestra la lista de productos disponibles en formato de tabla
    fun mostrarProductos() {
        while (true) {
            Vista.mostrarMensaje("\n📦 Catálogo de Productos\n")

            // Encabezado de tabla
            Vista.mostrarMensaje("┌─────┬──────────────────────────────┬───────────┬───────┐")
            Vista.mostrarMensaje("│ ID  │ Producto                     │ Precio    │ Stock │")
            Vista.mostrarMensaje("├─────┼──────────────────────────────┼───────────┼───────┤")

            // Filas de productos
            for (producto in productos) {
                Vista.mostrarMensaje(
                    String.format(
                        "│ %-3d │ %-28s │ $%-8.2f │ %-5d │",
                        producto.id, producto.nombre, producto.precio, producto.cantidadDisponible
                    )
                )
            }

            // Cierre de tabla
            Vista.mostrarMensaje("└─────┴──────────────────────────────┴───────────┴───────┘")

            // Nueva interacción
            Vista.mostrarMensaje(
                """
¿Qué desea hacer?
1. Ingresar ID de producto para comprar
2. Regresar al menú principal
            """.trimIndent()
            )

            when (readLine()) {
                "1" -> {
                    Vista.mostrarMensaje("Ingrese el ID del producto que desea comprar:")
                    val id = readLine()?.toIntOrNull()
                    val producto = productos.find { it.id == id }

                    if (producto != null) {
                        Vista.mostrarMensaje("Ingrese la cantidad que desea comprar:")
                        val cantidad = readLine()?.toIntOrNull()

                        if (cantidad != null && cantidad > 0) {
                            try {
                                carrito.agregarProducto(producto, cantidad)
                                Vista.mostrarMensaje("✔ Producto agregado al carrito.")

                                // Preguntar qué hacer después
                                Vista.mostrarMensaje(
                                    """
Seleccione una opción:
1. Seguir comprando
2. Confirmar compra ahora
3. Regresar al menú
                                """.trimIndent()
                                )
                                when (readLine()) {
                                    "1" -> continue
                                    "2" -> {
                                        val factura = carrito.generarFactura()
                                        Vista.mostrarFactura(factura)
                                        carrito.limpiarCarrito()
                                        return
                                    }
                                    "3" -> return
                                    else -> Vista.mostrarMensaje("⚠️ Opción no válida. Regresando al menú.")
                                }
                            } catch (e: IllegalArgumentException) {
                                Vista.mostrarMensaje("❌ Error: ${e.message}")
                            }
                        } else {
                            Vista.mostrarMensaje("⚠️ Cantidad inválida.")
                        }
                    } else {
                        Vista.mostrarMensaje("❌ Producto no encontrado.")
                    }
                }
                "2" -> return
                else -> Vista.mostrarMensaje("⚠️ Opción no válida.")
            }
        }
    }


    fun agregarProducto() {
        while (true) {
            Vista.mostrarMensaje("Ingrese el ID del producto que desea agregar:")
            val id = readLine()?.toIntOrNull()
            Vista.mostrarMensaje("Ingrese la cantidad que desea agregar:")
            val cantidad = readLine()?.toIntOrNull()

            if (id != null && cantidad != null) {
                val producto = productos.find { it.id == id }
                if (producto != null) {
                    try {
                        carrito.agregarProducto(producto, cantidad)
                        Vista.mostrarMensaje("✔ Producto agregado correctamente.\n")
                    } catch (e: IllegalArgumentException) {
                        Vista.mostrarMensaje("❌ Error: ${e.message}\n")
                    }
                } else {
                    Vista.mostrarMensaje("❌ Producto no encontrado.\n")
                }
            } else {
                Vista.mostrarMensaje("⚠️ Entrada no válida.\n")
            }

            Vista.mostrarMensaje(
                """
¿Desea continuar?
1. Agregar otro producto
2. Regresar al menú principal
                """.trimIndent()
            )

            when (readLine()) {
                "1" -> continue
                "2" -> {
                    Vista.mostrarMensaje("↩ Regresando al menú principal...\n")
                    return
                }
                else -> Vista.mostrarMensaje("⚠️ Opción no válida. Regresando al menú principal.\n")
            }
            return
        }
    }

    fun eliminarProducto() {
        Vista.mostrarMensaje("Ingrese el ID del producto que desea eliminar del carrito:")
        val id = readLine()?.toIntOrNull()
        if (id != null) {
            val producto = productos.find { it.id == id }
            if (producto != null) {
                try {
                    carrito.eliminarProductoPorId(producto.id)
                    Vista.mostrarMensaje("✔ Producto eliminado correctamente.\n")
                } catch (e: IllegalArgumentException) {
                    Vista.mostrarMensaje("❌ Error: ${e.message}\n")
                }
            } else {
                Vista.mostrarMensaje("❌ Producto no encontrado.\n")
            }
        } else {
            Vista.mostrarMensaje("⚠️ Entrada no válida.\n")
        }
        Vista.mostrarMensaje("🔙 Presione 'Enter' para regresar al menú principal")
        readLine()
    }

    fun verCarrito() {
        val items = carrito.mostrarCarrito()
        if (items.isEmpty()) {
            Vista.mostrarMensaje("🛒 El carrito está vacío.\n")
        } else {
            Vista.mostrarProductos(items)
        }
        while (true) {
            Vista.mostrarMensaje(
                """
Seleccione una opción:
1. Seguir comprando
2. Confirmar compra
                """.trimIndent()
            )
            when (readLine()) {
                "1" -> return
                "2" -> {
                    val factura = carrito.generarFactura()
                    Vista.mostrarFactura(factura)
                    carrito.limpiarCarrito()
                    return
                }
                else -> Vista.mostrarMensaje("⚠️ Opción no válida.\n")
            }
        }
    }
}

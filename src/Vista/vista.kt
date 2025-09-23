package Vista

import Modelo.CarritoItem
import Modelo.Carrito
import Modelo.Factura
import kotlin.system.exitProcess

object Vista {

    // Constante para líneas divisorias
    private const val LINEA = "════════════════════════════════════════════════════════════════"

    // ───────────── Mostrar productos en el carrito ─────────────
    fun mostrarProductos(productos: List<CarritoItem>) {
        var totalGeneral = 0.0
        println("════════════════ 🛒 Productos en el Carrito 🛒 ═══════════════════")
        println("Producto              | Cantidad | Precio Unitario | Precio Total")
        println(LINEA)

        for (item in productos) {
            imprimirFila(item)
            totalGeneral += item.calcularPrecioTotal()
        }

        println(LINEA)
        println("Total General del Carrito: $${"%.2f".format(totalGeneral)}")
        println(LINEA)
    }

    // ───────────── Mostrar factura ─────────────
    fun mostrarFactura(factura: Factura) {
        println("═════════════════════════  FACTURA  ════════════════════════════")
        println("Fecha de la Factura: ${factura.fecha}")
        println(LINEA)
        println("Producto              | Cantidad | Precio Unitario | Precio Total")
        println(LINEA)

        for (item in factura.items) {
            imprimirFila(item)
        }

        println(LINEA)
        println("Subtotal:              $${"%.2f".format(factura.subtotal)}")
        println("Impuestos (${factura.porcentajeImpuesto * 100} %): $${"%.2f".format(factura.impuestos)}")
        println(LINEA)
        println("Total General:         $${"%.2f".format(factura.total)}")
        println(LINEA)
        println("¡Gracias por su compra en Compus S.A de C.V!")

        mostrarOpcionesPostFactura()
    }

    // ───────────── Mostrar menú post-factura ─────────────
    private tailrec fun mostrarOpcionesPostFactura() {
        println("\nSeleccione una opción:")
        println("1. Realizar nueva compra")
        println("2. Salir de la aplicación")

        when (readLine()) {
            "1" -> println("Regresando al menú principal...\n")
            "2" -> {
                println("Gracias por su compra. ¡Hasta pronto!")
                exitProcess(0)
            }
            else -> {
                println("Opción no válida. Por favor, intente nuevamente.")
                mostrarOpcionesPostFactura() // llamada recursiva para repetir menú
            }
        }
    }

    // ───────────── Función auxiliar para imprimir una fila de producto ─────────────
    private fun imprimirFila(item: CarritoItem) {
        println(
            String.format(
                "%-22s| %-8d| $%-13.2f| $%-10.2f",
                item.producto.nombre,
                item.cantidad,
                item.producto.precio,
                item.calcularPrecioTotal()
            )
        )
    }

    // ───────────── Mostrar un mensaje genérico ─────────────
    fun mostrarMensaje(mensaje: String) {
        println(mensaje)
    }
}

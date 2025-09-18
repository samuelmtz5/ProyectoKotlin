package Modelo

data class Factura(
    val items: List<CarritoItem>,      // Poductos
    val subtotal: Double,              // Suma de productos sin IVA
    val porcentajeImpuesto: Double,    // IVA
    val impuestos: Double,             // Valor calculado de los impuestos
    val total: Double,                 // Total general incluyendo impuestos
    val fecha: String
)

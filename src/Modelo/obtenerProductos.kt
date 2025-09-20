package Modelo

fun obtenerProductos(): List<Producto> {
    return listOf(
        Producto(id = 1, nombre = "Laptop ASUS ROG", precio = 1200.00, cantidadDisponible = 10),
        Producto(id = 2, nombre = "Mouse Gamer Logitech G502", precio = 45.99, cantidadDisponible = 25),
        Producto(id = 3, nombre = "Teclado Mecánico Redragon", precio = 65.00, cantidadDisponible = 30),
        Producto(id = 4, nombre = "Monitor LG 27'' 144Hz", precio = 250.00, cantidadDisponible = 15),
        Producto(id = 5, nombre = "SSD Kingston 1TB", precio = 90.00, cantidadDisponible = 40),
        Producto(id = 6, nombre = "Memoria RAM Corsair 16GB", precio = 70.00, cantidadDisponible = 35),
        Producto(id = 7, nombre = "Gráfica NVIDIA RTX 4060", precio = 450.00, cantidadDisponible = 8),
        Producto(id = 8, nombre = "Audífonos HyperX Cloud II", precio = 85.00, cantidadDisponible = 20),
        Producto(id = 9, nombre = "Silla Gamer Cougar", precio = 199.99, cantidadDisponible = 12),
        Producto(id = 10, nombre = "Fuente de Poder EVGA 650W", precio = 75.00, cantidadDisponible = 25),

        // 🔹 Nuevos artículos añadidos
        Producto(id = 11, nombre = "Gabinete Cooler Master ATX", precio = 110.00, cantidadDisponible = 14),
        Producto(id = 12, nombre = "Disco Duro Seagate 2TB", precio = 65.00, cantidadDisponible = 18),
        Producto(id = 13, nombre = "Router Archer AX50 WiFi 6", precio = 135.00, cantidadDisponible = 10),
        Producto(id = 14, nombre = "Cámara Web Logitech C920 HD", precio = 89.99, cantidadDisponible = 20),
        Producto(id = 15, nombre = "Micrófono Blue Yeti USB", precio = 129.00, cantidadDisponible = 15),
        Producto(id = 16, nombre = "Base Enfriadora para Laptop", precio = 35.00, cantidadDisponible = 22),
        Producto(id = 17, nombre = "Ventiladores RGB Corsair", precio = 75.00, cantidadDisponible = 16),
        Producto(id = 18, nombre = "Alfombrilla Gamer Razer", precio = 25.00, cantidadDisponible = 30),
        Producto(id = 19, nombre = "Cargador Universal", precio = 40.00, cantidadDisponible = 25),
        Producto(id = 20, nombre = "Docking USB-C 10 en 1", precio = 99.00, cantidadDisponible = 12),

        Producto(id = 21, nombre = "Laptop HO Envy x360", precio = 999.00, cantidadDisponible = 10),
        Producto(id = 22, nombre = "Teclado Mecanico AULA F75", precio = 69.00, cantidadDisponible = 15),
        Producto(id = 23, nombre = "Mouse Inalambrico Razer", precio = 25.00, cantidadDisponible = 16),
        Producto(id = 24, nombre = "Audifonos Sony para PS5", precio = 50.00, cantidadDisponible = 12),
        Producto(id = 25, nombre = "Dualshock 5 para Sony PS5", precio = 80.00, cantidadDisponible = 25),
    )
}

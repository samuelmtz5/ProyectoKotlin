# Poyecto Kotlin Fase 1

Aplicación de consola para una tienda simple: **muestra productos**, **agrega/elimina** del carrito y **genera factura** con IVA. El punto de entrada (`main`) invoca el **Controlador** que despliega un menú y orquesta la interacción entre **Modelo**, **Vista** y **Controlador** (patrón **MVC**).

---

## 🧱 Estructura del proyecto (MVC + POO)

```
/Modelo
  Producto.kt
  CarritoItem.kt
  Carrito.kt
  Factura.kt
  obtenerProductos.kt
/Controlador
  Controlador.kt
/Vista
  vista.kt
Main.kt
Excepciones.kt
```

### Rol de cada capa
- **Modelo**
  - `Producto` (**data class**): entidad con `id`, `nombre`, `precio`, `cantidadDisponible`.
  - `CarritoItem` (**data class**): relación `Producto` + `cantidad`; método `calcularPrecioTotal()`.
  - `Carrito` (**class**): mantiene una **lista privada** de ítems (encapsulación), valida stock, **agrega/quita** y **genera Factura**.
  - `Factura` (**data class**): `items`, `subtotal`, `impuesto`, `total`, `fecha`.
  - `obtenerProductos()` (**función**): catálogo inicial editable.
- **Vista**
  - `Vista` (**object**): imprime menús, carrito y factura con formato.
- **Controlador**
  - `Controlador` (**object / singleton**): muestra menú, lee entradas, coordina casos de uso.

---

## 🔑 Enfoque de Programación Orientada a Objetos (POO)

- **Abstracción de dominio**: `Producto`, `CarritoItem`, `Factura` modelan conceptos del negocio.
- **Encapsulación**: `Carrito` oculta su estructura interna (`private val items`) y expone operaciones controladas.
- **SRP / Responsabilidad Única**:
  - `Carrito`: reglas de negocio (stock, sumatorias, IVA, generación de `Factura`).
  - `Vista`: salida/impresión y formato.
  - `Controlador`: orquestación del flujo.
- **Sustitución/Polimorfismo (preparado)**: separación MVC facilita reemplazar `Vista` por GUI o Web sin tocar la lógica.
- **Singletons**: `Controlador` y `Vista` son `object` (una instancia global).

---

## 🧭 Flujo principal

1. `main()` → `Controlador.iniciar()` arranca la app.
2. **Menú**: ver productos, agregar, eliminar, ver carrito, salir.
3. **Agregar**: pide `id` y `cantidad`, valida stock, descuenta del inventario y añade al carrito.
4. **Eliminar**: quita por `id` y **restaura stock**.
5. **Ver carrito / Confirmar**: muestra totales y genera `Factura` (IVA 13%, fecha actual).

---

## 🛒 Catálogo y branding

- Editá `Modelo/obtenerProductos.kt` para cambiar los ítems del catálogo.
- Textos de menú/branding se ajustan en `Controlador` y `Vista`.

---

## 🧪 Errores y excepciones

- Validaciones de stock lanzan `IllegalArgumentException` (listo para migrar a excepciones custom).
- `Excepciones.kt` incluye `CantidadNoDisponibleException` y `ProductoNoEncontradoException` para uso futuro.

---

## ⚙️ Compilar y ejecutar (local)

Con Kotlin instalado:
```bash
# Compilar JAR ejecutable
kotlinc Main.kt Controlador/*.kt Modelo/*.kt Vista/*.kt -include-runtime -d tienda.jar

# Ejecutar
java -jar tienda.jar
```

> Si se usa Gradle/IntelliJ, se puede crear un proyecto Kotlin/JVM y añadir estos archivos en `src/main/kotlin` manteniendo el mismo paquete.

---

## 🧮 IVA, totales y fecha

- IVA por defecto: **13%** (parametrizable si lo extraés a constante/config).
- Fecha de la factura: `LocalDate.now()`.

---

## 📂 Archivos incluidos (resumen)

- `Main.kt`: punto de entrada; llama a `Controlador.iniciar()`.
- `Controlador.kt`: bucle de menú y coordinación MVC.
- `vista.kt`: utilidades de impresión/formato (consola).
- `Producto.kt`, `CarritoItem.kt`, `Carrito.kt`, `Factura.kt`: dominio y lógica.
- `obtenerProductos.kt`: catálogo de ejemplo.
- `Excepciones.kt`: excepciones personalizadas (extensibles).

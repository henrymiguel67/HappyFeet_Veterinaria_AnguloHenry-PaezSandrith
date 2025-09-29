package org.example.View;

import org.example.controller.InventarioController;
import org.example.model.entities.Inventario;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class InventarioView {
    private static final Logger logger = Logger.getLogger(InventarioView.class.getName());
    private final InventarioController inventarioController;
    private final Scanner scanner;

    public InventarioView() {
        this.inventarioController = new InventarioController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuInventario() {
        int opcion;
        do {
            System.out.println("\n=== 📦 GESTIÓN DE INVENTARIO ===");
            System.out.println("1. ➕ Agregar Producto");
            System.out.println("2. 📋 Listar Todos los Productos");
            System.out.println("3. 🔍 Buscar Producto por ID");
            System.out.println("4. 🔎 Buscar Productos por Nombre");
            System.out.println("5. ⚠️  Ver Productos con Stock Bajo");
            System.out.println("6. 📅 Ver Productos Próximos a Vencer");
            System.out.println("7. 🚫 Ver Productos Vencidos");
            System.out.println("8. ✏️  Actualizar Stock");
            System.out.println("9. ➖ Deducir Stock");
            System.out.println("10. ➕ Reponer Stock");
            System.out.println("11. 📊 Resumen de Inventario");
            System.out.println("12. 🔔 Generar Alertas");
            System.out.println("13. 💰 Valor Total del Inventario");
            System.out.println("0. ↩️ Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1 -> agregarProducto();
                    case 2 -> listarTodosLosProductos();
                    case 3 -> buscarProductoPorId();
                    case 4 -> buscarProductosPorNombre();
                    case 5 -> verProductosStockBajo();
                    case 6 -> verProductosProximosAVencer();
                    case 7 -> verProductosVencidos();
                    case 8 -> actualizarStock();
                    case 9 -> deducirStock();
                    case 10 -> reponerStock();
                    case 11 -> generarResumenInventario();
                    case 12 -> generarAlertas();
                    case 13 -> mostrarValorTotalInventario();
                    case 0 -> System.out.println("Volviendo al menú principal...");
                    default -> System.out.println("❌ Opción inválida. Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: Ingrese un número válido.");
                scanner.nextLine(); // Limpiar buffer en caso de error
                opcion = -1;
            }
        } while (opcion != 0);
    }

    private void agregarProducto() {
        System.out.println("\n--- ➕ AGREGAR NUEVO PRODUCTO ---");
        
        try {
            System.out.print("Nombre del Producto: ");
            String nombreProducto = scanner.nextLine();

            System.out.print("ID del Tipo de Producto: ");
            Integer productoTipoId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Descripción: ");
            String descripcion = scanner.nextLine();

            System.out.print("Fabricante: ");
            String fabricante = scanner.nextLine();

            System.out.print("Número de Lote: ");
            String lote = scanner.nextLine();

            System.out.print("Cantidad en Stock: ");
            Integer cantidadStock = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Stock Mínimo: ");
            Integer stockMinimo = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Fecha de Vencimiento (YYYY-MM-DD) o Enter para omitir: ");
            String fechaVencimientoStr = scanner.nextLine();
            java.util.Date fechaVencimiento = null;
            if (!fechaVencimientoStr.trim().isEmpty()) {
                fechaVencimiento = java.sql.Date.valueOf(fechaVencimientoStr);
            }

            System.out.print("Precio de Venta: ");
            Double precioVenta = scanner.nextDouble();
            scanner.nextLine();

            // Validar datos antes de enviar al controller
            if (!inventarioController.validarDatosProducto(nombreProducto, productoTipoId, cantidadStock, stockMinimo, precioVenta)) {
                System.out.println("❌ Datos inválidos. Verifique la información.");
                return;
            }

            boolean exito = inventarioController.agregarProducto(nombreProducto, productoTipoId, descripcion, 
                                                                fabricante, lote, cantidadStock, stockMinimo, 
                                                                fechaVencimiento, precioVenta);
            if (exito) {
                System.out.println("✅ Producto agregado exitosamente!");
            } else {
                System.out.println("❌ Error al agregar el producto.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            logger.log(Level.SEVERE, "Error en agregarProducto: " + e.getMessage(), e);
        }
    }

    private void listarTodosLosProductos() {
        System.out.println("\n--- 📋 LISTA DE TODOS LOS PRODUCTOS ---");
        
        List<Inventario> productos = inventarioController.listarTodosLosProductos();
        
        if (productos.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
        } else {
            System.out.printf("%-4s %-25s %-15s %-8s %-8s %-12s %-10s%n", 
                "ID", "Nombre", "Fabricante", "Stock", "Mínimo", "Vencimiento", "Precio");
            System.out.println("--------------------------------------------------------------------------------------------");
            
            for (Inventario producto : productos) {
                String fechaVencimiento = producto.getFechaVencimiento() != null ? 
                    producto.getFechaVencimiento().toString() : "N/A";
                
                // Resaltar productos con stock bajo
                String stockDisplay = producto.getCantidadStock() <= producto.getStockMinimo() ? 
                    "⚠️" + producto.getCantidadStock() : String.valueOf(producto.getCantidadStock());
                
                System.out.printf("%-4d %-25s %-15s %-8s %-8d %-12s $%-9.2f%n",
                    producto.getId(),
                    producto.getNombreProducto(),
                    producto.getFabricante(),
                    stockDisplay,
                    producto.getStockMinimo(),
                    fechaVencimiento.substring(0, Math.min(10, fechaVencimiento.length())),
                    producto.getPrecioVenta()
                );
            }
        }
    }

    private void buscarProductoPorId() {
        System.out.println("\n--- 🔍 BUSCAR PRODUCTO POR ID ---");
        
        try {
            System.out.print("Ingrese ID del producto: ");
            Integer id = scanner.nextInt();
            scanner.nextLine();

            var productoOpt = inventarioController.buscarProductoPorId(id);
            
            if (productoOpt.isPresent()) {
                Inventario producto = productoOpt.get();
                System.out.println("✅ Producto encontrado:");
                System.out.println("   ID: " + producto.getId());
                System.out.println("   Nombre: " + producto.getNombreProducto());
                System.out.println("   Tipo ID: " + producto.getProductoTipoId());
                System.out.println("   Descripción: " + producto.getDescripcion());
                System.out.println("   Fabricante: " + producto.getFabricante());
                System.out.println("   Lote: " + producto.getLote());
                System.out.println("   Stock: " + producto.getCantidadStock());
                System.out.println("   Stock Mínimo: " + producto.getStockMinimo());
                System.out.println("   Fecha Vencimiento: " + 
                    (producto.getFechaVencimiento() != null ? producto.getFechaVencimiento() : "N/A"));
                System.out.println("   Precio: $" + producto.getPrecioVenta());
                
                // Mostrar estado del stock
                if (producto.getCantidadStock() <= producto.getStockMinimo()) {
                    System.out.println("   ⚠️  ALERTA: Stock bajo!");
                }
            } else {
                System.out.println("❌ No se encontró producto con ID: " + id);
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void buscarProductosPorNombre() {
        System.out.println("\n--- 🔎 BUSCAR PRODUCTOS POR NOMBRE ---");
        
        try {
            System.out.print("Ingrese nombre o parte del nombre: ");
            String nombre = scanner.nextLine();

            List<Inventario> productos = inventarioController.buscarProductosPorNombre(nombre);
            
            if (productos.isEmpty()) {
                System.out.println("No se encontraron productos con nombre: " + nombre);
            } else {
                System.out.println("📋 Productos encontrados (" + productos.size() + "):");
                for (Inventario producto : productos) {
                    String estadoStock = producto.getCantidadStock() <= producto.getStockMinimo() ? 
                        " ⚠️" : "";
                    System.out.println("   📦 " + producto.getNombreProducto() + 
                                     " (ID: " + producto.getId() + 
                                     ", Stock: " + producto.getCantidadStock() + 
                                     ", Precio: $" + producto.getPrecioVenta() + ")" + estadoStock);
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void verProductosStockBajo() {
        System.out.println("\n--- ⚠️ PRODUCTOS CON STOCK BAJO ---");
        
        List<Inventario> productos = inventarioController.buscarProductosConStockBajo();
        
        if (productos.isEmpty()) {
            System.out.println("✅ No hay productos con stock bajo.");
        } else {
            System.out.println("📋 Productos que necesitan reposición (" + productos.size() + "):");
            for (Inventario producto : productos) {
                System.out.println("   ⚠️  " + producto.getNombreProducto() + 
                                 " - Stock: " + producto.getCantidadStock() + 
                                 " (Mínimo: " + producto.getStockMinimo() + ")" +
                                 " - ID: " + producto.getId());
            }
        }
    }

    private void verProductosProximosAVencer() {
        System.out.println("\n--- 📅 PRODUCTOS PRÓXIMOS A VENCER ---");
        
        try {
            System.out.print("Ingrese número de días para alerta: ");
            int dias = scanner.nextInt();
            scanner.nextLine();

            List<Inventario> productos = inventarioController.buscarProductosProximosAVencer(dias);
            
            if (productos.isEmpty()) {
                System.out.println("✅ No hay productos próximos a vencer en los próximos " + dias + " días.");
            } else {
                System.out.println("📋 Productos próximos a vencer (" + productos.size() + "):");
                for (Inventario producto : productos) {
                    System.out.println("   📅 " + producto.getNombreProducto() + 
                                     " - Vence: " + producto.getFechaVencimiento() +
                                     " - ID: " + producto.getId());
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void verProductosVencidos() {
        System.out.println("\n--- 🚫 PRODUCTOS VENCIDOS ---");
        
        List<Inventario> productos = inventarioController.buscarProductosVencidos();
        
        if (productos.isEmpty()) {
            System.out.println("✅ No hay productos vencidos.");
        } else {
            System.out.println("📋 Productos vencidos (" + productos.size() + "):");
            for (Inventario producto : productos) {
                System.out.println("   🚫 " + producto.getNombreProducto() + 
                                 " - Vencido: " + producto.getFechaVencimiento() +
                                 " - ID: " + producto.getId());
            }
        }
    }

    private void actualizarStock() {
        System.out.println("\n--- ✏️ ACTUALIZAR STOCK ---");
        
        try {
            System.out.print("ID del producto: ");
            Integer id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nueva cantidad de stock: ");
            Integer nuevaCantidad = scanner.nextInt();
            scanner.nextLine();

            boolean exito = inventarioController.actualizarStock(id, nuevaCantidad);
            if (exito) {
                System.out.println("✅ Stock actualizado exitosamente!");
            } else {
                System.out.println("❌ Error al actualizar el stock.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void deducirStock() {
        System.out.println("\n--- ➖ DEDUCIR STOCK ---");
        
        try {
            System.out.print("ID del producto: ");
            Integer id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Cantidad a deducir: ");
            Integer cantidad = scanner.nextInt();
            scanner.nextLine();

            // Verificar stock suficiente primero
            boolean stockSuficiente = inventarioController.verificarStockSuficiente(id, cantidad);
            if (!stockSuficiente) {
                System.out.println("❌ Stock insuficiente para realizar la deducción.");
                return;
            }

            boolean exito = inventarioController.deducirStock(id, cantidad);
            if (exito) {
                System.out.println("✅ Stock deducido exitosamente!");
            } else {
                System.out.println("❌ Error al deducir el stock.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void reponerStock() {
        System.out.println("\n--- ➕ REPONER STOCK ---");
        
        try {
            System.out.print("ID del producto: ");
            Integer id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Cantidad a reponer: ");
            Integer cantidad = scanner.nextInt();
            scanner.nextLine();

            boolean exito = inventarioController.reponerStock(id, cantidad);
            if (exito) {
                System.out.println("✅ Stock repuesto exitosamente!");
            } else {
                System.out.println("❌ Error al reponer el stock.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void generarResumenInventario() {
        System.out.println("\n--- 📊 RESUMEN DE INVENTARIO ---");
        inventarioController.generarResumenInventario();
    }

    private void generarAlertas() {
        System.out.println("\n--- 🔔 ALERTAS DE INVENTARIO ---");
        inventarioController.generarAlertasInventario();
    }

    private void mostrarValorTotalInventario() {
        System.out.println("\n--- 💰 VALOR TOTAL DEL INVENTARIO ---");
        
        double valorTotal = inventarioController.obtenerValorTotalInventario();
        System.out.println("💰 Valor total del inventario: $" + valorTotal);
    }

    // Método para mostrar resumen rápido en el menú principal
    public void mostrarResumenRapido() {
        try {
            List<Inventario> stockBajo = inventarioController.buscarProductosConStockBajo();
            List<Inventario> proximosVencer = inventarioController.buscarProductosProximosAVencer(7);
            List<Inventario> vencidos = inventarioController.buscarProductosVencidos();
            
            System.out.println("\n=== 📦 RESUMEN RÁPIDO INVENTARIO ===");
            System.out.println("Productos con Stock Bajo: " + stockBajo.size());
            System.out.println("Productos Próximos a Vencer (7 días): " + proximosVencer.size());
            System.out.println("Productos Vencidos: " + vencidos.size());
            
            if (!stockBajo.isEmpty() || !proximosVencer.isEmpty() || !vencidos.isEmpty()) {
                System.out.println("⚠️  Hay alertas pendientes. Use la opción 12 para ver detalles.");
            } else {
                System.out.println("✅ No hay alertas pendientes.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error al generar resumen rápido: " + e.getMessage());
        }
    }
}
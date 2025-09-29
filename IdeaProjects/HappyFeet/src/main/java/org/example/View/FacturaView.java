package org.example.View;

import org.example.controller.FacturaController;
import org.example.model.entities.Factura;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class FacturaView {
    private static final Logger logger = Logger.getLogger(FacturaView.class.getName());
    private final FacturaController facturaController;
    private final Scanner scanner;

    public FacturaView() {
        this.facturaController = new FacturaController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuFacturas() {
        int opcion;
        do {
            System.out.println("\n=== 🧾 GESTIÓN DE FACTURAS ===");
            System.out.println("1. 🧾 Generar Factura");
            System.out.println("2. 📋 Listar Todas las Facturas");
            System.out.println("3. 🔍 Buscar Factura por ID");
            System.out.println("4. 🏠 Buscar Facturas por Dueño");
            System.out.println("5. 📅 Buscar Facturas por Fecha");
            System.out.println("6. 📊 Reporte de Facturación");
            System.out.println("7. 🗑️ Eliminar Factura");
            System.out.println("8. 🔢 Generar Número de Factura");
            System.out.println("0. ↩️ Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1 -> generarFactura();
                    case 2 -> listarTodasLasFacturas();
                    case 3 -> buscarFacturaPorId();
                    case 4 -> buscarFacturasPorDueno();
                    case 5 -> buscarFacturasPorFecha();
                    case 6 -> generarReporteFacturacion();
                    case 7 -> eliminarFactura();
                    case 8 -> generarNumeroFactura();
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

    private void generarFactura() {
        System.out.println("\n--- 🧾 GENERAR NUEVA FACTURA ---");
        
        try {
            System.out.print("ID del Dueño: ");
            Integer duenoId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Total de la Factura: ");
            Double total = scanner.nextDouble();
            scanner.nextLine();

            // Validar datos antes de enviar al controller
            if (!facturaController.validarDatosFactura(duenoId, total)) {
                System.out.println("❌ Datos inválidos. Verifique la información.");
                return;
            }

            boolean exito = facturaController.generarFactura(duenoId, total);
            if (exito) {
                System.out.println("✅ Factura generada exitosamente!");
            } else {
                System.out.println("❌ Error al generar la factura.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            logger.log(Level.SEVERE, "Error en generarFactura: " + e.getMessage(), e);
        }
    }

    private void listarTodasLasFacturas() {
        System.out.println("\n--- 📋 LISTA DE TODAS LAS FACTURAS ---");
        
        List<Factura> facturas = facturaController.listarTodasLasFacturas();
        
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas registradas.");
        } else {
            System.out.printf("%-6s %-10s %-20s %-10s%n", 
                "ID", "Dueño ID", "Fecha Emisión", "Total");
            System.out.println("----------------------------------------------------");
            
            for (Factura factura : facturas) {
                System.out.printf("%-6d %-10d %-20s $%-9.2f%n",
                    factura.getId(),
                    factura.getDuenoId(),
                    factura.getFechaEmision().toString().substring(0, 16),
                    factura.getTotal()
                );
            }
        }
    }

    private void buscarFacturaPorId() {
        System.out.println("\n--- 🔍 BUSCAR FACTURA POR ID ---");
        
        try {
            System.out.print("Ingrese ID de la factura: ");
            Integer id = scanner.nextInt();
            scanner.nextLine();

            var facturaOpt = facturaController.buscarFacturaPorId(id);
            
            if (facturaOpt.isPresent()) {
                Factura factura = facturaOpt.get();
                System.out.println("✅ Factura encontrada:");
                System.out.println("   ID: " + factura.getId());
                System.out.println("   Dueño ID: " + factura.getDuenoId());
                System.out.println("   Fecha Emisión: " + factura.getFechaEmision());
                System.out.println("   Total: $" + factura.getTotal());
            } else {
                System.out.println("❌ No se encontró factura con ID: " + id);
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void buscarFacturasPorDueno() {
        System.out.println("\n--- 🏠 BUSCAR FACTURAS POR DUEÑO ---");
        
        try {
            System.out.print("Ingrese ID del dueño: ");
            Integer duenoId = scanner.nextInt();
            scanner.nextLine();

            List<Factura> facturas = facturaController.buscarFacturasPorDueno(duenoId);
            
            if (facturas.isEmpty()) {
                System.out.println("No se encontraron facturas para el dueño ID: " + duenoId);
            } else {
                System.out.println("📋 Facturas del dueño ID " + duenoId + ":");
                double totalDueno = 0.0;
                for (Factura factura : facturas) {
                    System.out.println("   🧾 ID: " + factura.getId() + 
                                     " - Fecha: " + factura.getFechaEmision().toString().substring(0, 10) +
                                     " - Total: $" + factura.getTotal());
                    totalDueno += factura.getTotal();
                }
                System.out.println("💰 Total gastado por el dueño: $" + totalDueno);
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void buscarFacturasPorFecha() {
        System.out.println("\n--- 📅 BUSCAR FACTURAS POR FECHA ---");
        
        try {
            System.out.print("Fecha inicio (YYYY-MM-DD): ");
            String fechaInicioStr = scanner.nextLine();
            java.util.Date fechaInicio = java.sql.Date.valueOf(fechaInicioStr);

            System.out.print("Fecha fin (YYYY-MM-DD): ");
            String fechaFinStr = scanner.nextLine();
            java.util.Date fechaFin = java.sql.Date.valueOf(fechaFinStr);

            List<Factura> facturas = facturaController.buscarFacturasPorRangoFechas(fechaInicio, fechaFin);
            
            if (facturas.isEmpty()) {
                System.out.println("No se encontraron facturas en el rango de fechas.");
            } else {
                System.out.println("📋 Facturas encontradas (" + facturas.size() + "):");
                for (Factura factura : facturas) {
                    System.out.println("   🧾 ID: " + factura.getId() + 
                                     " - Dueño: " + factura.getDuenoId() +
                                     " - Fecha: " + factura.getFechaEmision().toString().substring(0, 10) +
                                     " - Total: $" + factura.getTotal());
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void generarReporteFacturacion() {
        System.out.println("\n--- 📊 REPORTE DE FACTURACIÓN ---");
        
        try {
            System.out.print("Fecha inicio del reporte (YYYY-MM-DD): ");
            String fechaInicioStr = scanner.nextLine();
            java.util.Date fechaInicio = java.sql.Date.valueOf(fechaInicioStr);

            System.out.print("Fecha fin del reporte (YYYY-MM-DD): ");
            String fechaFinStr = scanner.nextLine();
            java.util.Date fechaFin = java.sql.Date.valueOf(fechaFinStr);

            // Obtener estadísticas
            Double totalFacturado = facturaController.obtenerTotalFacturadoPorPeriodo(fechaInicio, fechaFin);
            List<Factura> facturas = facturaController.buscarFacturasPorRangoFechas(fechaInicio, fechaFin);
            
            System.out.println("\n=== 📊 REPORTE DE FACTURACIÓN ===");
            System.out.println("Período: " + fechaInicioStr + " a " + fechaFinStr);
            System.out.println("----------------------------------------");
            System.out.println("Total Facturado: $" + totalFacturado);
            System.out.println("Cantidad de Facturas: " + facturas.size());
            System.out.println("Promedio por Factura: $" + (facturas.isEmpty() ? 0 : totalFacturado / facturas.size()));
            
            if (!facturas.isEmpty()) {
                // Encontrar factura con mayor monto
                Factura mayorFactura = facturas.get(0);
                for (Factura factura : facturas) {
                    if (factura.getTotal() > mayorFactura.getTotal()) {
                        mayorFactura = factura;
                    }
                }
                System.out.println("Factura Mayor - ID: " + mayorFactura.getId() + " - Monto: $" + mayorFactura.getTotal());
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void eliminarFactura() {
        System.out.println("\n--- 🗑️ ELIMINAR FACTURA ---");
        
        try {
            System.out.print("ID de la factura a eliminar: ");
            Integer id = scanner.nextInt();
            scanner.nextLine();

            // Primero mostrar la factura
            var facturaOpt = facturaController.buscarFacturaPorId(id);
            if (facturaOpt.isEmpty()) {
                System.out.println("❌ No se encontró factura con ID: " + id);
                return;
            }

            Factura factura = facturaOpt.get();
            System.out.println("Factura a eliminar:");
            System.out.println("   ID: " + factura.getId());
            System.out.println("   Dueño ID: " + factura.getDuenoId());
            System.out.println("   Total: $" + factura.getTotal());

            System.out.print("¿Está seguro de eliminar esta factura? (s/n): ");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                // Aquí se llamaría al método de eliminación cuando esté implementado
                System.out.println("⚠️  Función de eliminación en desarrollo...");
                // facturaController.eliminarFactura(id);
                System.out.println("✅ Eliminación programada para futura implementación.");
            } else {
                System.out.println("❌ Eliminación cancelada.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void generarNumeroFactura() {
        System.out.println("\n--- 🔢 GENERAR NÚMERO DE FACTURA ---");
        
        try {
            String numeroFactura = facturaController.generarNumeroFactura();
            System.out.println("🔢 Número de factura generado: " + numeroFactura);
            System.out.println("💡 Este número puede ser usado para la próxima factura.");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // Método para mostrar resumen rápido
    public void mostrarResumenFacturacion() {
        try {
            // Últimos 30 días
            java.util.Date fechaFin = new java.util.Date();
            java.util.Date fechaInicio = new java.util.Date(fechaFin.getTime() - (30L * 24 * 60 * 60 * 1000));
            
            Double totalMensual = facturaController.obtenerTotalFacturadoPorPeriodo(fechaInicio, fechaFin);
            List<Factura> facturasRecientes = facturaController.buscarFacturasPorRangoFechas(fechaInicio, fechaFin);
            
            System.out.println("\n=== 📈 RESUMEN FACTURACIÓN (Últimos 30 días) ===");
            System.out.println("Total Facturado: $" + totalMensual);
            System.out.println("Facturas Emitidas: " + facturasRecientes.size());
            System.out.println("Promedio Diario: $" + (totalMensual / 30));

        } catch (Exception e) {
            System.out.println("❌ Error al generar resumen: " + e.getMessage());
        }
    }
}
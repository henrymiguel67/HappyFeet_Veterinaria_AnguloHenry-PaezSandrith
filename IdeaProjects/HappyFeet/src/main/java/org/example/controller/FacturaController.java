package org.example.controller;

import org.example.model.entities.Factura;
import org.example.Service.FacturaService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;

public class FacturaController {
    private static final Logger logger = Logger.getLogger(FacturaController.class.getName());
    private final FacturaService facturaService;

    public FacturaController() {
        this.facturaService = facturaService;
    }

    // CREATE - Generar nueva factura
    public boolean generarFactura(Factura factura) {
        try {
            facturaService.generarFactura(factura);
            logger.info("✅ Factura generada exitosamente - ID: " + factura.getId() + " - Total: $" + factura.getTotal());
            return true;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al generar factura: " + e.getMessage(), e);
            return false;
        }
    }

    // READ - Listar todas las facturas
    public List<Factura> listarTodasLasFacturas() {
        try {
            List<Factura> facturas = facturaService.listarTodasLasFacturas();
            logger.info("📋 Se listaron " + facturas.size() + " facturas");
            return facturas;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al listar facturas: " + e.getMessage(), e);
            return List.of();
        }
    }

    // READ - Buscar factura por ID
    public Optional<Factura> buscarFacturaPorId(Integer id) {
        try {
            Optional<Factura> factura = facturaService.buscarFacturaPorId(id);
            if (factura.isPresent()) {
                logger.info("🔍 Factura encontrada - ID: " + factura.get().getId());
            } else {
                logger.info("🔍 No se encontró factura con ID: " + id);
            }
            return factura;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al buscar factura por ID: " + e.getMessage(), e);
            return Optional.empty();
        }
    }

    // READ - Buscar facturas por dueño
    public List<Factura> buscarFacturasPorDueno(Integer duenoId) {
        try {
            List<Factura> facturas = facturaService.buscarFacturasPorDueno(duenoId);
            logger.info("🏠 Se encontraron " + facturas.size() + " facturas para el dueño ID: " + duenoId);
            return facturas;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al buscar facturas por dueño: " + e.getMessage(), e);
            return List.of();
        }
    }

    // READ - Buscar facturas por rango de fechas
    public List<Factura> buscarFacturasPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        try {
            List<Factura> facturas = facturaService.buscarFacturasPorRangoFechas(fechaInicio, fechaFin);
            logger.info("📅 Se encontraron " + facturas.size() + " facturas en el rango de fechas");
            return facturas;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al buscar facturas por rango de fechas: " + e.getMessage(), e);
            return List.of();
        }
    }

    // READ - Obtener última factura generada
    public Optional<Factura> obtenerUltimaFactura() {
        try {
            Optional<Factura> factura = facturaService.obtenerUltimaFactura();
            if (factura.isPresent()) {
                logger.info("⏱️ Última factura obtenida - ID: " + factura.get().getId());
            } else {
                logger.info("⏱️ No hay facturas registradas");
            }
            return factura;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al obtener última factura: " + e.getMessage(), e);
            return Optional.empty();
        }
    }

    // UPDATE - Actualizar factura
    public boolean actualizarFactura(Factura factura) {
        try {
            facturaService.actualizarFactura(factura);
            logger.info("🔄 Factura actualizada exitosamente - ID: " + factura.getId());
            return true;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al actualizar factura: " + e.getMessage(), e);
            return false;
        }
    }

    // DELETE - Eliminar factura
    public boolean eliminarFactura(Integer id) {
        try {
            facturaService.eliminarFactura(id);
            logger.info("🗑️ Factura eliminada exitosamente - ID: " + id);
            return true;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al eliminar factura: " + e.getMessage(), e);
            return false;
        }
    }

    // REPORTES - Total facturado por período
    public Double obtenerTotalFacturadoPorPeriodo(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        try {
            Double total = facturaService.obtenerTotalFacturadoPorPeriodo(fechaInicio, fechaFin);
            logger.info("💰 Total facturado en el período: $" + total);
            return total;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al obtener total facturado: " + e.getMessage(), e);
            return 0.0;
        }
    }

    // REPORTES - Estadísticas completas por período
    public void generarReporteEstadisticas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        try {
            Double totalFacturado = facturaService.obtenerTotalFacturadoPorPeriodo(fechaInicio, fechaFin);
            Integer cantidadFacturas = facturaService.contarFacturasPorPeriodo(fechaInicio, fechaFin);
            Double promedioFacturacion = facturaService.obtenerPromedioFacturacionPorPeriodo(fechaInicio, fechaFin);
            Optional<Factura> facturaMayor = facturaService.obtenerFacturaMayorMontoPorPeriodo(fechaInicio, fechaFin);
            
            logger.info("📊 === REPORTE ESTADÍSTICAS === ");
            logger.info("📊 Período: " + fechaInicio + " a " + fechaFin);
            logger.info("📊 Total Facturado: $" + totalFacturado);
            logger.info("📊 Cantidad de Facturas: " + cantidadFacturas);
            logger.info("📊 Promedio por Factura: $" + promedioFacturacion);
            
            if (facturaMayor.isPresent()) {
                logger.info("📊 Factura Mayor - ID: " + facturaMayor.get().getId() + 
                           " - Monto: $" + facturaMayor.get().getTotal());
            }
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al generar reporte estadístico: " + e.getMessage(), e);
        }
    }

    // Validación de datos antes de operaciones
    public boolean validarDatosFactura(Integer duenoId, Double total) {
        try {
            if (duenoId == null || duenoId <= 0) {
                return false;
            }
            if (total == null || total < 0) {
                return false;
            }
            return true;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error en validación de datos de factura: " + e.getMessage(), e);
            return false;
        }
    }

    public Integer contarFacturasPorPeriodo(LocalDateTime fechaInicioTime, LocalDateTime fechaFinTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contarFacturasPorPeriodo'");
    }
}

            switch (opcion) {
                case 1:
                    controller.crear();
                    break;
                case 2:
                    controller.listar();
                    break;
                case 3:
                    controller.actualizar();
                    break;
                case 4:
                    controller.eliminar();
                    break;
                case 0:
                    System.out.println("🔙 Regresando al menú principal...");
                    break;
                default:
                    System.out.println("❌ Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 0);
    }

    private static void gestionarItems(Scanner scanner, ItemsController controller, View view, ItemsDAO dao) {
        int opcion;
        do {
            view.mostrarMenu();
            opcion = leerOpcion(scanner);

            switch (opcion) {
                case 1:
                    controller.crear();
                    break;
                case 2:
                    controller.listar();
                    break;
                case 3:
                    controller.actualizar();
                    break;
                case 4:
                    controller.eliminar();
                    break;
                case 0:
                    System.out.println("🔙 Regresando al menú principal...");
                    break;
                default:
                    System.out.println("❌ Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 0);
    }

private static void gestionarDuenos(Scanner scanner, DuenoController controller, DuenoView view, DuenoDAO dao) {
    int opcion;
    do {
        view.mostrarMenu();
        opcion = leerOpcion(scanner);

        switch (opcion) {
            case 1:
                controller.crear();
                break;
            case 2:
                controller.listar();
                break;
            case 3:
                controller.actualizar();
                break;
            case 4:
                controller.eliminar();
                break;
            case 5:
                // Opción adicional para búsquedas específicas
                controller.buscarPorCriterio();
                break;
            case 0:
                System.out.println("🔙 Regresando al menú principal...");
                break;
            default:
                System.out.println("❌ Opción inválida. Intente de nuevo.");
        }
    } while (opcion != 0);
}
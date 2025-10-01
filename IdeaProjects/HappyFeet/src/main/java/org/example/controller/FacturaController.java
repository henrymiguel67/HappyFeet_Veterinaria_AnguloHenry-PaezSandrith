
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

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    public FacturaController() {
        this.facturaService = new FacturaService(null);
    }

    // CREATE - Generar nueva factura

    /**
     *
     * @param factura
     * @return
     */
    public boolean generarFactura(Factura factura) {
        try {
            facturaService.generarFactura(factura);
            logger.log(Level.INFO, "\u2705 Factura generada exitosamente - ID: {0} - Total: ${1}", new Object[]{factura.getId(), factura.getTotal()});
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
            logger.log(Level.INFO, "\ud83d\udccb Se listaron {0} facturas", facturas.size());
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
                logger.log(Level.INFO, "\ud83d\udd0d Factura encontrada - ID: {0}", factura.get().getId());
            } else {
                logger.log(Level.INFO, "\ud83d\udd0d No se encontr\u00f3 factura con ID: {0}", id);
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
            logger.log(Level.INFO, "\ud83c\udfe0 Se encontraron {0} facturas para el due\u00f1o ID: {1}", new Object[]{facturas.size(), duenoId});
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
            logger.log(Level.INFO, "\ud83d\udcc5 Se encontraron {0} facturas en el rango de fechas", facturas.size());
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
                logger.log(Level.INFO, "\u23f1\ufe0f \u00daltima factura obtenida - ID: {0}", factura.get().getId());
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
            logger.log(Level.INFO, "\ud83d\udd04 Factura actualizada exitosamente - ID: {0}", factura.getId());
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
            logger.log(Level.INFO, "\ud83d\uddd1\ufe0f Factura eliminada exitosamente - ID: {0}", id);
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
            logger.log(Level.INFO, "\ud83d\udcb0 Total facturado en el per\u00edodo: ${0}", total);
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
            logger.log(Level.INFO, "\ud83d\udcca Per\u00edodo: {0} a {1}", new Object[]{fechaInicio, fechaFin});
            logger.log(Level.INFO, "\ud83d\udcca Total Facturado: ${0}", totalFacturado);
            logger.log(Level.INFO, "\ud83d\udcca Cantidad de Facturas: {0}", cantidadFacturas);
            logger.log(Level.INFO, "\ud83d\udcca Promedio por Factura: ${0}", promedioFacturacion);

            facturaMayor.ifPresent(f -> 
                logger.log(Level.INFO, "\ud83d\udcca Factura Mayor - ID: {0} - Monto: ${1}", new Object[]{f.getId(), f.getTotal()})
            );
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al generar reporte estadístico: " + e.getMessage(), e);
        }
    }

    // Validación de datos antes de operaciones
    public boolean validarDatosFactura(Integer duenoId, Double total) {
        return duenoId != null && duenoId > 0 && total != null && total >= 0;
    }

    // Delegación directa al service (no más UnsupportedOperation)
    public Integer contarFacturasPorPeriodo(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        try {
            return facturaService.contarFacturasPorPeriodo(fechaInicio, fechaFin);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al contar facturas por periodo: " + e.getMessage(), e);
            return 0;
        }
    }    }




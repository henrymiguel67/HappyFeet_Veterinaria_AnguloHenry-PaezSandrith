package org.example.controller;

import org.example.model.entities.Factura;
import org.example.service.FacturaService;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;

public class FacturaController {
    private static final Logger logger = Logger.getLogger(FacturaController.class.getName());
    private final FacturaService facturaService;

    public FacturaController() {
        this.facturaService = new FacturaService();
    }

    // CREATE - Generar nueva factura
    public boolean generarFactura(Integer duenoId, Double total) {
        try {
            Factura factura = new Factura();
            factura.setDuenoId(duenoId);
            factura.setFechaEmision(new java.util.Date());
            factura.setTotal(total);

            facturaService.generarFactura(factura);
            logger.info("✅ Factura generada exitosamente - ID: " + factura.getId() + " - Total: $" + total);
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
    public List<Factura> buscarFacturasPorRangoFechas(java.util.Date fechaInicio, java.util.Date fechaFin) {
        try {
            List<Factura> facturas = facturaService.buscarFacturasPorRangoFechas(fechaInicio, fechaFin);
            logger.info("📅 Se encontraron " + facturas.size() + " facturas en el rango de fechas");
            return facturas;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al buscar facturas por rango de fechas: " + e.getMessage(), e);
            return List.of();
        }
    }

    // REPORTES - Total facturado por período
    public Double obtenerTotalFacturadoPorPeriodo(java.util.Date fechaInicio, java.util.Date fechaFin) {
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
    public void generarReporteEstadisticas(java.util.Date fechaInicio, java.util.Date fechaFin) {
        try {
            Double totalFacturado = facturaService.obtenerTotalFacturadoPorPeriodo(fechaInicio, fechaFin);
            Integer cantidadFacturas = facturaService.contarFacturasPorPeriodo(fechaInicio, fechaFin);
            Double promedioFacturacion = facturaService.obtenerPromedioFacturacionPorPeriodo(fechaInicio, fechaFin);
            Optional<Factura> facturaMayor = facturaService.obtenerFacturaMayorMontoPorPeriodo(fechaInicio, fechaFin);
            
            logger.info("📊 === REPORTE ESTADÍSTICAS ===");
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

    // Método para generar número de factura
    public String generarNumeroFactura() {
        try {
            String numeroFactura = facturaService.generarNumeroFactura();
            logger.info("🔢 Número de factura generado: " + numeroFactura);
            return numeroFactura;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al generar número de factura: " + e.getMessage(), e);
            return "F-000001";
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
}
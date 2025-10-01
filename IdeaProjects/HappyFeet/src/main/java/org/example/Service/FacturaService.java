package org.example.Service;

import org.example.model.entities.Factura;
import org.example.Repository.FacturaDAO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class FacturaService {
    private final FacturaDAO facturaDAO;

    public FacturaService(FacturaDAO facturaDAO) {
        this.facturaDAO = facturaDAO;
    }

    // CREATE - Generar nueva factura
    public void generarFactura(Factura factura) {
        validarFactura(factura);
        facturaDAO.generarFactura(factura);
    }

    // READ - Listar todas las facturas
    public List<Factura> listarTodasLasFacturas() {
        return facturaDAO.listarTodasLasFacturas();
    }

    // READ - Buscar factura por ID
    public Optional<Factura> buscarFacturaPorId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de factura inválido");
        }
        return facturaDAO.buscarPorId(id);
    }

    // READ - Buscar facturas por dueño
    public List<Factura> buscarFacturasPorDueno(Integer duenoId) {
        if (duenoId == null || duenoId <= 0) {
            throw new IllegalArgumentException("ID de dueño inválido");
        }
        return facturaDAO.buscarPorDuenoId(duenoId);
    }

    // READ - Buscar facturas por rango de fechas
    public List<Factura> buscarFacturasPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        validarRangoFechas(fechaInicio, fechaFin);
        return facturaDAO.buscarPorRangoFechas(fechaInicio, fechaFin);
    }

    // READ - Obtener última factura generada
    public Optional<Factura> obtenerUltimaFactura() {
        return facturaDAO.obtenerUltimaFactura();
    }

    // UPDATE - Actualizar factura
    public void actualizarFactura(Factura factura) {
        validarFactura(factura);
        
        if (factura.getId() == null || factura.getId() <= 0) {
            throw new IllegalArgumentException("ID de factura inválido para actualizar");
        }
        
        if (!facturaDAO.existeFactura(factura.getId())) {
            throw new IllegalArgumentException("No se encontró la factura con ID: " + factura.getId());
        }
        
        facturaDAO.actualizarFactura(factura);
    }

    // DELETE - Eliminar factura
    public void eliminarFactura(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de factura inválido");
        }
        
        if (!facturaDAO.existeFactura(id)) {
            throw new IllegalArgumentException("No se encontró la factura con ID: " + id);
        }
        
        facturaDAO.eliminarFactura(id);
    }

    // REPORTES - Total facturado por período
    public Double obtenerTotalFacturadoPorPeriodo(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        validarRangoFechas(fechaInicio, fechaFin);
        return facturaDAO.obtenerTotalFacturadoPorPeriodo(fechaInicio, fechaFin);
    }

    // REPORTES - Cantidad de facturas por período
    public Integer contarFacturasPorPeriodo(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        validarRangoFechas(fechaInicio, fechaFin);
        return facturaDAO.contarFacturasPorPeriodo(fechaInicio, fechaFin);
    }

    // REPORTES - Promedio de facturación por período
    public Double obtenerPromedioFacturacionPorPeriodo(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        Integer cantidad = contarFacturasPorPeriodo(fechaInicio, fechaFin);
        if (cantidad == 0) {
            return 0.0;
        }
        return obtenerTotalFacturadoPorPeriodo(fechaInicio, fechaFin) / cantidad;
    }

    // REPORTES - Factura con mayor monto por período
    public Optional<Factura> obtenerFacturaMayorMontoPorPeriodo(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        validarRangoFechas(fechaInicio, fechaFin);
        
        // Implementación alternativa ya que el DAO no tiene este método directamente
        List<Factura> facturas = buscarFacturasPorRangoFechas(fechaInicio, fechaFin);
        return facturas.stream()
                .max((f1, f2) -> Double.compare(f1.getTotal(), f2.getTotal()));
    }

    // Validación de datos de la factura
    private void validarFactura(Factura factura) {
        if (factura == null) {
            throw new IllegalArgumentException("La factura no puede ser nula");
        }
        
        if (factura.getDuenoId() == null || factura.getDuenoId() <= 0) {
            throw new IllegalArgumentException("ID de dueño inválido");
        }
        
        if (factura.getFechaEmision() == null) {
            throw new IllegalArgumentException("Fecha de emisión es requerida");
        }
        
        if (factura.getTotal() < 0) {
            throw new IllegalArgumentException("Total de factura inválido");
        }
        
        if (factura.getFechaEmision().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha de emisión no puede ser futura");
        }
    }

    private void validarRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas no pueden ser nulas");
        }
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha fin");
        }
    }
}
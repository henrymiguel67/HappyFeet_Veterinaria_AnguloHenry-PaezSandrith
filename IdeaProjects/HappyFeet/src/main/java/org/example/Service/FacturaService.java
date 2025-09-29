package org.example.Service;

import org.example.model.entities.Factura;
import org.example.repository.FacturaDAO;
import java.util.List;
import java.util.Optional;

public class FacturaService {
    private final FacturaDAO facturaDAO;

    public FacturaService() {
        this.facturaDAO = new FacturaDAO();
    }

    // CREATE - Generar nueva factura
    public void generarFactura(Factura factura) {
        validarFactura(factura);
        facturaDAO.generarFactura(factura);
    }

    // READ - Listar todas las facturas
    public List<Factura> listarTodasLasFacturas() {
        return facturaDAO.listarTodas();
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
    public List<Factura> buscarFacturasPorRangoFechas(java.util.Date fechaInicio, java.util.Date fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas no pueden ser nulas");
        }
        if (fechaInicio.after(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha fin");
        }
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
        
        // Verificar que la factura existe
        Optional<Factura> facturaExistente = facturaDAO.buscarPorId(factura.getId());
        if (facturaExistente.isEmpty()) {
            throw new IllegalArgumentException("No se encontró la factura con ID: " + factura.getId());
        }
        
        facturaDAO.actualizarFactura(factura);
    }

    // DELETE - Eliminar factura
    public void eliminarFactura(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de factura inválido");
        }
        
        // Verificar que la factura existe antes de eliminar
        Optional<Factura> factura = facturaDAO.buscarPorId(id);
        if (factura.isEmpty()) {
            throw new IllegalArgumentException("No se encontró la factura con ID: " + id);
        }
        
        facturaDAO.eliminarFactura(id);
    }

    // REPORTES - Total facturado por período
    public Double obtenerTotalFacturadoPorPeriodo(java.util.Date fechaInicio, java.util.Date fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas no pueden ser nulas");
        }
        if (fechaInicio.after(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha fin");
        }
        
        Double total = facturaDAO.obtenerTotalFacturadoPorPeriodo(fechaInicio, fechaFin);
        return total != null ? total : 0.0;
    }

    // REPORTES - Cantidad de facturas por período
    public Integer contarFacturasPorPeriodo(java.util.Date fechaInicio, java.util.Date fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas no pueden ser nulas");
        }
        if (fechaInicio.after(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha fin");
        }
        
        return facturaDAO.contarFacturasPorPeriodo(fechaInicio, fechaFin);
    }

    // REPORTES - Promedio de facturación por período
    public Double obtenerPromedioFacturacionPorPeriodo(java.util.Date fechaInicio, java.util.Date fechaFin) {
        Integer cantidad = contarFacturasPorPeriodo(fechaInicio, fechaFin);
        Double total = obtenerTotalFacturadoPorPeriodo(fechaInicio, fechaFin);
        
        if (cantidad == 0) {
            return 0.0;
        }
        
        return total / cantidad;
    }

    // REPORTES - Factura con mayor monto por período
    public Optional<Factura> obtenerFacturaMayorMontoPorPeriodo(java.util.Date fechaInicio, java.util.Date fechaFin) {
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
        
        if (factura.getTotal() == null || factura.getTotal() < 0) {
            throw new IllegalArgumentException("Total de factura inválido");
        }
        
        // Validar que la fecha no sea futura
        if (factura.getFechaEmision().after(new java.util.Date())) {
            throw new IllegalArgumentException("La fecha de emisión no puede ser futura");
        }
    }

    // Método para calcular total automáticamente basado en items (se implementará después)
    public Double calcularTotalFactura(List<Object> items) {
        // Esto se integrará con ItemsFacturaService después
        double total = 0.0;
        // Lógica de cálculo basada en items
        return total;
    }

    // Método para generar número de factura consecutivo
    public String generarNumeroFactura() {
        Optional<Factura> ultimaFactura = facturaDAO.obtenerUltimaFactura();
        int siguienteNumero = ultimaFactura.map(f -> f.getId() + 1).orElse(1);
        return String.format("F-%06d", siguienteNumero);
    }
}
package org.example.model.entities;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Currency;
import java.util.List;
import java.util.Objects;

/**
 * Enhanced Invoice class with validation, builder pattern, and additional features
 */
public final class Factura {
    private Integer id;
    private Integer duenoId;
    private LocalDateTime fechaEmision;
    private double total;
    private EstadoFactura estado;
    private List<InvoiceItem> lineas;
    private String numeroFactura;
    private LocalDateTime fechaVencimiento;
    private Currency moneda;

    // Enums
    public enum EstadoFactura {
        PENDIENTE, PAGADA, CANCELADA
    }

    // Constructors
    public Factura() {
    }

    public Factura(Integer id, Integer duenoId, LocalDateTime fechaEmision, double total) {
        this.id = id;
        this.duenoId = duenoId;
        setFechaEmision(fechaEmision);
        setTotal(total);
    }

    // Builder pattern
    public static class Builder {
        private Integer id;
        private Integer duenoId;
        private LocalDateTime fechaEmision;
        private double total;

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withDuenoId(Integer duenoId) {
            this.duenoId = duenoId;
            return this;
        }

        public Builder withFechaEmision(LocalDateTime fechaEmision) {
            this.fechaEmision = fechaEmision;
            return this;
        }

        public Builder withTotal(double total) {
            this.total = total;
            return this;
        }

        public Factura build() {
            return new Factura(id, duenoId, fechaEmision, total);
        }
    }

    // Getters and Setters with validation
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDuenoId() {
        return duenoId;
    }

    public void setDuenoId(Integer duenoId) {
        this.duenoId = duenoId;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        if (fechaEmision == null) {
            throw new IllegalArgumentException("Issue date cannot be null");
        }
        this.fechaEmision = fechaEmision;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        if (total < 0) {
            throw new IllegalArgumentException("Total cannot be negative");
        }
        this.total = total;
    }

    public EstadoFactura getEstado() {
        return estado;
    }

    public void setEstado(EstadoFactura estado) {
        this.estado = estado;
    }

    public List<InvoiceItem> getLineas() {
        return lineas;
    }

    public void setLineas(List<InvoiceItem> lineas) {
        this.lineas = lineas;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public LocalDateTime getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDateTime fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Currency getMoneda() {
        return moneda;
    }

    public void setMoneda(Currency moneda) {
        this.moneda = moneda;
    }

    // Utility methods
    public boolean isFromYear(int year) {
        return fechaEmision.getYear() == year;
    }

    public boolean isFromMonth(Month month) {
        return fechaEmision.getMonth() == month;
    }

    public boolean isOwner(Integer ownerId) {
        return duenoId.equals(ownerId);
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return Objects.equals(id, factura.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString
    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", duenoId=" + duenoId +
                ", fechaEmision=" + fechaEmision +
                ", total=" + total +
                ", estado=" + estado +
                ", lineas=" + lineas +
                ", numeroFactura='" + numeroFactura + '\'' +
                ", fechaVencimiento=" + fechaVencimiento +
                ", moneda=" + moneda +
                '}';
    }

    public void agregarProducto(String producto, int cantidad, double precio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarProducto'");
    }
}

// Supporting class for invoice items
class InvoiceItem {
    // Implementation of invoice line items
    // Would typically include product ID, description, quantity, unit price, etc.
}
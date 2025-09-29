package org.example.model.entities;

import java.time.LocalDateTime;

public class Factura {

    private String id;
    private LocalDateTime fechaEmision;
    private double total;

    public Factura(String id, LocalDateTime fechaEmision, double total) {
        this.id = id;
        this.fechaEmision = fechaEmision;
        this.total = total;
    }

    public String getId() { return id; }
    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public double getTotal() { return total; }

    public void setId(String id) { this.id = id; }
    public void setFechaEmision(LocalDateTime fechaEmision) { this.fechaEmision = fechaEmision; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return "Factura{" +
                "id='" + id + '\'' +
                ", fechaEmision=" + fechaEmision +
                ", total=" + total +
                '}';
    }
}

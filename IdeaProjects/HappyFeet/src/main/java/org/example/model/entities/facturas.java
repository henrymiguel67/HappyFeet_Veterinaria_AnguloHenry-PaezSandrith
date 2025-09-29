package org.example.model.entities;

import java.time.LocalDateTime;

public class Facturas {
    private int id;
    private int DuenoId;
    private LocalDateTime FechaEmision;
    private double Total;

    public Facturas(int id, int duenoId, LocalDateTime fechaEmision, double total) {
        this.id = id;
        this.DuenoId = duenoId;
        this.FechaEmision = fechaEmision;
        this.Total = total;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getDuenoId() { return DuenoId; }
    public void setDuenoId(int duenoId) { this.DuenoId = duenoId; }
    public LocalDateTime getFechaEmision() { return FechaEmision; }
    public void setFechaEmision(LocalDateTime fechaEmision) { this.FechaEmision = fechaEmision; }
    public double getTotal() { return Total; }
    public void setTotal(double total) { this.Total = total; }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", duenoId=" + DuenoId +
                ", fechaEmision=" + FechaEmision +
                ", total=" + Total +
                '}';
    }
}

package org.example.model.entities;

import java.time.LocalDateTime;

public class Factura {

    private Integer id;
    private Integer duenoId;
    private LocalDateTime fechaEmision;
    private double total;

    public Factura() {
    }

    public Factura(Integer id, Integer duenoId, LocalDateTime fechaEmision, double total) {
        this.id = id;
        this.duenoId = duenoId;
        this.fechaEmision = fechaEmision;
        this.total = total;
    }

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
        this.fechaEmision = fechaEmision;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", duenoId=" + duenoId +
                ", fechaEmision=" + fechaEmision +
                ", total=" + total +
                '}';
    }
}

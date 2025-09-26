package org.example.model.entities;

import java.util.Date;

public class Facturas {
    private int id;
    private int duenoId;
    private Date fechaEmision;
    private double total;

    public facturas(int id, int duenoId, Date fechaEmision, double total) {
        this.id = id;
        this.duenoId = duenoId;
        this.fechaEmision = fechaEmision;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuenoId() {
        return duenoId;
    }

    public void setDuenoId(int duenoId) {
        this.duenoId = duenoId;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

package org.example.model.entities;

import java.util.Date;

public class facturas {
    public int id;
    public int dueno_id;
    public Date fecha_emision;
    public double total;

    public facturas(int id, int dueno_id, Date fecha_emision, double total) {
        this.id = id;
        this.dueno_id = dueno_id;
        this.fecha_emision = fecha_emision;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDueno_id() {
        return dueno_id;
    }

    public void setDueno_id(int dueno_id) {
        this.dueno_id = dueno_id;
    }

    public Date getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.model.entities;

import java.util.Date;

/**
 *
 * @author camper
 */
public class ExportarHistorialMedico {
    public int id;
    public int mascotaid;
    public Date FechaExport;

    public ExportarHistorialMedico(int id, int mascotaid, Date FechaExport) {
        this.id = id;
        this.mascotaid = mascotaid;
        this.FechaExport = FechaExport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMascotaid() {
        return mascotaid;
    }

    public void setMascotaid(int mascotaid) {
        this.mascotaid = mascotaid;
    }

    public Date getFechaExport() {
        return FechaExport;
    }

    public void setFechaExport(Date FechaExport) {
        this.FechaExport = FechaExport;
    }
    @Override
    public String toString() {
        return "ExportarHistorialMedico" +
                "id=" + id +
                ", mascotaId=" + mascotaid +
                ", fechaExportacion=" + FechaExport + '\'' +
         
                '}';
    }
    
}

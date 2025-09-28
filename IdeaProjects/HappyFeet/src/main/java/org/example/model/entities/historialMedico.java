package org.example.model.entities;

import java.util.Date;

public class HistorialMedico {
    // Atributos PRIVADOS (encapsulación)
    private int id;
    private int mascotaId;
    private Date fechaEvento;
    private int eventoTipoId;
    private String descripcion;
    private String diagnostico;
    private String tratamientoRecomendado;
    
    // Constructor completo
    public HistorialMedico(int id, int mascotaId, Date fechaEvento, int eventoTipoId, 
                          String descripcion, String diagnostico, String tratamientoRecomendado) {
        this.id = id;
        this.mascotaId = mascotaId;
        this.fechaEvento = fechaEvento;
        this.eventoTipoId = eventoTipoId;
        this.descripcion = descripcion;
        this.diagnostico = diagnostico;
        this.tratamientoRecomendado = tratamientoRecomendado;
    }
    
    // Constructor vacío
    public HistorialMedico() {}
    
    // Getters y Setters (acceso controlado)
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getMascotaId() {
        return mascotaId;
    }
    
    public void setMascotaId(int mascotaId) {
        this.mascotaId = mascotaId;
    }
    
    public Date getFechaEvento() {
        return fechaEvento;
    }
    
    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }
    
    public int getEventoTipoId() {
        return eventoTipoId;
    }
    
    public void setEventoTipoId(int eventoTipoId) {
        this.eventoTipoId = eventoTipoId;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDiagnostico() {
        return diagnostico;
    }
    
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    
    public String getTratamientoRecomendado() {
        return tratamientoRecomendado;
    }
    
    public void setTratamientoRecomendado(String tratamientoRecomendado) {
        this.tratamientoRecomendado = tratamientoRecomendado;
    }
    
    @Override
    public String toString() {
        return "HistorialMedico{" +
                "id=" + id +
                ", mascotaId=" + mascotaId +
                ", fechaEvento=" + fechaEvento +
                ", diagnostico='" + diagnostico + '\'' +
                '}';
    }
}
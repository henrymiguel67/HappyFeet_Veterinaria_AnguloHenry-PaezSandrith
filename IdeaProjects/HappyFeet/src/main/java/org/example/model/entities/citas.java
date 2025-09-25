package org.example.model.entities;

import java.time.LocalDateTime;

public class Citas {
    private int id;
    private int idDueno;
    private int idMascota;
    private LocalDateTime fechaHora;
    private String motivo;
    private String estado; // Ejemplo: "Pendiente", "Completada", "Cancelada"

    // Constructor vacío
    public Citas() {}

    // Constructor sin ID (para cuando se crea una nueva cita antes de guardarla en la BD)
    public Citas(int idDueno, int idMascota, LocalDateTime fechaHora, String motivo, String estado) {
        this.idDueno = idDueno;
        this.idMascota = idMascota;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estado = estado;
    }

    // Constructor completo
    public Citas(int id, int idDueno, int idMascota, LocalDateTime fechaHora, String motivo, String estado) {
        this.id = id;
        this.idDueno = idDueno;
        this.idMascota = idMascota;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDueno() {
        return idDueno;
    }

    public void setIdDueno(int idDueno) {
        this.idDueno = idDueno;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Citas{" +
                "id=" + id +
                ", idDueno=" + idDueno +
                ", idMascota=" + idMascota +
                ", fechaHora=" + fechaHora +
                ", motivo='" + motivo + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}

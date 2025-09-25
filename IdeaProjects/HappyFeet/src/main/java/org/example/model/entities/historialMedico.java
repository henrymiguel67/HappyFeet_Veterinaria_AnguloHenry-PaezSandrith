package org.example.model.entities;

import java.util.Date;

public class historialMedico {
    public int id;
    public int mascota_id;
    public Date fecha_evento;
    public String descricion;
    public String diagnostico;
    public String tratamiento_recomendado;

    public historialMedico(int id, int mascota_id, Date fecha_evento, String descricion, String diagnostico, String tratamiento_recomendado) {
        this.id = id;
        this.mascota_id = mascota_id;
        this.fecha_evento = fecha_evento;
        this.descricion = descricion;
        this.diagnostico = diagnostico;
        this.tratamiento_recomendado = tratamiento_recomendado;
    }
    public historialMedico() {}

    public int getId() {
        return id;
    }

    public int getMascota_id() {
        return mascota_id;
    }

    public Date getFecha_evento() {
        return fecha_evento;
    }

    public String getDescricion() {
        return descricion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamiento_recomendado() {
        return tratamiento_recomendado;
    }
}

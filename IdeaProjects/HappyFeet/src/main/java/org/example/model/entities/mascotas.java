package org.example.model.entities;

import java.util.Date;

public class mascotas {
    public int id;
    public String nombre;
    public int raza_id;
    public Date fecha_nacimiento;
    public char sexo;
    public String url_foto;

    public mascotas(int id, String nombre, int raza_id, Date fecha_nacimiento, char sexo, String url_foto) {
        this.id = id;
        this.nombre = nombre;
        this.raza_id = raza_id;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.url_foto = url_foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRaza_id() {
        return raza_id;
    }

    public void setRaza_id(int raza_id) {
        this.raza_id = raza_id;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getUrl_foto() {
        return url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }
}

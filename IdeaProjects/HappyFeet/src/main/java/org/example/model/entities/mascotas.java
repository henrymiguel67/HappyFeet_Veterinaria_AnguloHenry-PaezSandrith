package org.example.model.entities;

import java.util.Date;

public class Mascota {
    // Atributos PRIVADOS (encapsulación)
    private int id;
    private String nombre;
    private int duenoId;
    private int razaId;
    private Date fechaNacimiento;
    private String sexo; // Cambié de char a String para 'Macho'/'Hembra'
    private String urlFoto;
    
    // Constructor completo
    public Mascota(int id, String nombre, int duenoId, int razaId, Date fechaNacimiento, String sexo, String urlFoto) {
        this.id = id;
        this.nombre = nombre;
        this.duenoId = duenoId;
        this.razaId = razaId;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.urlFoto = urlFoto;
    }
    
    // Constructor vacío
    public Mascota() {}
    
    // Constructor sin ID (para inserts)
    public Mascota(String nombre, int duenoId, int razaId, Date fechaNacimiento, String sexo, String urlFoto) {
        this.nombre = nombre;
        this.duenoId = duenoId;
        this.razaId = razaId;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.urlFoto = urlFoto;
    }
    
    // Getters y Setters
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
    
    public int getDuenoId() {
        return duenoId;
    }
    
    public void setDuenoId(int duenoId) {
        this.duenoId = duenoId;
    }
    
    public int getRazaId() {
        return razaId;
    }
    
    public void setRazaId(int razaId) {
        this.razaId = razaId;
    }
    
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public String getSexo() {
        return sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public String getUrlFoto() {
        return urlFoto;
    }
    
    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
    
    @Override
    public String toString() {
        return "Mascota{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", duenoId=" + duenoId +
                ", razaId=" + razaId +
                ", sexo='" + sexo + '\'' +
                '}';
    }
}
package org.example.model.entities;

public class Dueno {
    private int id;
    private String nombre;
    private String documento_identidad;
    private String telefono;
    private String email;

    // Constructor vacío
    public Dueno() {}

    // Constructor con id y nombre
    public Dueno(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Constructor completo
    public Dueno(int id, String nombre, String documento_identidad, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.documento_identidad = documento_identidad;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters y setters
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

    public String getDocumento_identidad() {
        return documento_identidad;
    }

    public void setDocumento_identidad(String documento_identidad) {
        this.documento_identidad = documento_identidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Dueño{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", documento_identidad='" + documento_identidad + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

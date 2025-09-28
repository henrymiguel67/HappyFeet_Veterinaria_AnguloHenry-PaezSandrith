package org.example.model.entities;

public class Dueno {
    private int id;
    private String nombre;
    private String telefono;
    private String email;
    private String documentoIdentidad;

    public Dueno(int id, String nombre, String telefono, String email, String documentoIdentidad) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.documentoIdentidad = documentoIdentidad;
    }

    // Getters y setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public String getDocumentoIdentidad() { return documentoIdentidad; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setEmail(String email) { this.email = email; }
    public void setDocumentoIdentidad(String documentoIdentidad) { this.documentoIdentidad = documentoIdentidad; }

    @Override
    public String toString() {
        return "Dueño{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", documentoIdentidad='" + documentoIdentidad + '\'' +
                '}';
    }
}

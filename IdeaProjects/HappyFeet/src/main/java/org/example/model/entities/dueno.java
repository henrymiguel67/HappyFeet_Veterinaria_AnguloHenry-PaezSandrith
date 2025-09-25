package org.example.model.entities;

public class dueno {
   public int id ;
    public String nombre;
    public String documento_identidad;
   public String telefono;
    public String email;

    public dueno(int id, String nombre) {

    }
    public dueno(int id,String nombre,String documento_identidad,String telefono,String email) {
        this.id = id;
        this.nombre = nombre;
        this.documento_identidad = documento_identidad;
        this.telefono = telefono;
        this.email = email;

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
    @Override
    public String toString() {
        dueno d = new dueno(rs.getInt("id"), rs.getString("nombre"));
        d.id = this.id;
        d.nombre = this.nombre;
        return "se añadio un nuevo dueño";
    }
}

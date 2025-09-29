package org.example.Repository;

import org.example.model.entities.Mascota;
import java.util.List;
import java.util.Optional;

public interface IMascotaDAO {
    
    // CREATE - Agregar nueva mascota
    void agregarMascota(Mascota mascota);
    
    // READ - Listar todas las mascotas
    List<Mascota> listarTodas();
    
    // READ - Buscar mascota por ID
    Optional<Mascota> buscarPorId(Integer id);
    
    // READ - Buscar mascotas por dueño
    List<Mascota> buscarPorDuenoId(Integer duenoId);
    
    // READ - Buscar mascotas por nombre
    List<Mascota> buscarPorNombre(String nombre);
    
    // UPDATE - Actualizar mascota
    void actualizarMascota(Mascota mascota);
    
    // DELETE - Eliminar mascota por ID
    void eliminarMascota(Integer id);
    
    // VERIFICAR - Si existe mascota con nombre y dueño
    boolean existeMascota(String nombre, Integer duenoId);
}
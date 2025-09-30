package org.example.Service;

import org.example.model.entities.Mascota;
import org.example.Repository.MascotaDAO;
import java.util.List;
import java.util.Optional;

public class MascotaService {
    private final MascotaDAO mascotaDAO;

    public MascotaService() {
        this.mascotaDAO = new MascotaDAO();
    }

    // CREATE - Agregar nueva mascota con validaciones
    public void agregarMascota(Mascota mascota) {
        validarMascota(mascota);
        
        // Verificar si ya existe una mascota con el mismo nombre y dueño
        if (mascotaDAO.existeMascota(mascota.getNombre(), mascota.getDuenoId())) {
            throw new IllegalArgumentException("Ya existe una mascota con el nombre '" + 
                mascota.getNombre() + "' para este dueño");
        }
        
        mascotaDAO.agregarMascota(mascota);
    }

    // READ - Listar todas las mascotas
    public List<Mascota> listarTodasLasMascotas() {
        return mascotaDAO.listarTodas();
    }

    // READ - Buscar mascota por ID
    public Optional<Mascota> buscarMascotaPorId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de mascota inválido");
        }
        return mascotaDAO.buscarPorId(id);
    }

    // READ - Buscar mascotas por dueño
    public List<Mascota> buscarMascotasPorDueno(Integer duenoId) {
        if (duenoId == null || duenoId <= 0) {
            throw new IllegalArgumentException("ID de dueño inválido");
        }
        return mascotaDAO.buscarPorDuenoId(duenoId);
    }

    // READ - Buscar mascotas por nombre
    public List<Mascota> buscarMascotasPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre de mascota no puede estar vacío");
        }
        return mascotaDAO.buscarPorNombre(nombre.trim());
    }

    // UPDATE - Actualizar mascota
    public void actualizarMascota(Mascota mascota) {
        validarMascota(mascota);
        
        Integer id = mascota.getId();
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de mascota inválido para actualizar");
        }
        
        // Verificar que la mascota existe
        Optional<Mascota> mascotaExistente = mascotaDAO.buscarPorId(mascota.getId());
        if (mascotaExistente.isEmpty()) {
            throw new IllegalArgumentException("No se encontró la mascota con ID: " + mascota.getId());
        }
        
        mascotaDAO.actualizarMascota(mascota);
    }

    // DELETE - Eliminar mascota
    public void eliminarMascota(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID de mascota inválido");
        }
        
        // Verificar que la mascota existe antes de eliminar
        Optional<Mascota> mascota = mascotaDAO.buscarPorId(id);
        if (mascota.isEmpty()) {
            throw new IllegalArgumentException("No se encontró la mascota con ID: " + id);
        }
        
        mascotaDAO.eliminarMascota(id);
    }

    // Validación de datos de la mascota
    private void validarMascota(Mascota mascota) {
        if (mascota == null) {
            throw new IllegalArgumentException("La mascota no puede ser nula");
        }
        
        if (mascota.getNombre() == null || mascota.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la mascota es requerido");
        }
        
        Integer duenoId = mascota.getDuenoId();
        if (duenoId == null || duenoId <= 0) {
            throw new IllegalArgumentException("ID de dueño inválido");
        }
        
        Integer razaId = mascota.getRazaId();
        if (razaId == null || razaId <= 0) {
            throw new IllegalArgumentException("ID de raza inválido");
        }
        
        if (mascota.getSexo() == null || 
            (!mascota.getSexo().equals("Macho") && !mascota.getSexo().equals("Hembra"))) {
            throw new IllegalArgumentException("Sexo debe ser 'Macho' o 'Hembra'");
        }
        
        if (mascota.getFechaNacimiento() == null) {
            throw new IllegalArgumentException("Fecha de nacimiento es requerida");
        }
    }

    // Método adicional: Obtener cantidad de mascotas por dueño
    public int contarMascotasPorDueno(Integer duenoId) {
        if (duenoId == null || duenoId <= 0) {
            throw new IllegalArgumentException("ID de dueño inválido");
        }
        return mascotaDAO.buscarPorDuenoId(duenoId).size();
    }
}
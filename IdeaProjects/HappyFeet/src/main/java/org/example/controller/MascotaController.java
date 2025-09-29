package org.example.controller;

import org.example.model.entities.Mascota;
import org.example.service.MascotaService;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.Level;

public class MascotaController {
    private static final Logger logger = Logger.getLogger(MascotaController.class.getName());
    private final MascotaService mascotaService;

    public MascotaController() {
        this.mascotaService = new MascotaService();
    }

    // CREATE - Agregar nueva mascota
    public boolean agregarMascota(Integer duenoId, String nombre, Integer razaId, 
                                 java.util.Date fechaNacimiento, String sexo, String urlFoto) {
        try {
            Mascota mascota = new Mascota();
            mascota.setDuenoId(duenoId);
            mascota.setNombre(nombre);
            mascota.setRazaId(razaId);
            mascota.setFechaNacimiento(fechaNacimiento);
            mascota.setSexo(sexo);
            mascota.setUrlFoto(urlFoto);

            mascotaService.agregarMascota(mascota);
            logger.info("✅ Mascota agregada exitosamente: " + nombre);
            return true;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al agregar mascota: " + e.getMessage(), e);
            return false;
        }
    }

    // READ - Listar todas las mascotas
    public List<Mascota> listarTodasLasMascotas() {
        try {
            List<Mascota> mascotas = mascotaService.listarTodasLasMascotas();
            logger.info("📋 Se listaron " + mascotas.size() + " mascotas");
            return mascotas;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al listar mascotas: " + e.getMessage(), e);
            return List.of(); // Retorna lista vacía en caso de error
        }
    }

    // READ - Buscar mascota por ID
    public Optional<Mascota> buscarMascotaPorId(Integer id) {
        try {
            Optional<Mascota> mascota = mascotaService.buscarMascotaPorId(id);
            if (mascota.isPresent()) {
                logger.info("🔍 Mascota encontrada: " + mascota.get().getNombre());
            } else {
                logger.info("🔍 No se encontró mascota con ID: " + id);
            }
            return mascota;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al buscar mascota por ID: " + e.getMessage(), e);
            return Optional.empty();
        }
    }

    // READ - Buscar mascotas por dueño
    public List<Mascota> buscarMascotasPorDueno(Integer duenoId) {
        try {
            List<Mascota> mascotas = mascotaService.buscarMascotasPorDueno(duenoId);
            logger.info("🏠 Se encontraron " + mascotas.size() + " mascotas para el dueño ID: " + duenoId);
            return mascotas;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al buscar mascotas por dueño: " + e.getMessage(), e);
            return List.of();
        }
    }

    // READ - Buscar mascotas por nombre
    public List<Mascota> buscarMascotasPorNombre(String nombre) {
        try {
            List<Mascota> mascotas = mascotaService.buscarMascotasPorNombre(nombre);
            logger.info("🔎 Se encontraron " + mascotas.size() + " mascotas con nombre: " + nombre);
            return mascotas;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al buscar mascotas por nombre: " + e.getMessage(), e);
            return List.of();
        }
    }

    // UPDATE - Actualizar mascota
    public boolean actualizarMascota(Integer id, Integer duenoId, String nombre, Integer razaId,
                                    java.util.Date fechaNacimiento, String sexo, String urlFoto) {
        try {
            Mascota mascota = new Mascota();
            mascota.setId(id);
            mascota.setDuenoId(duenoId);
            mascota.setNombre(nombre);
            mascota.setRazaId(razaId);
            mascota.setFechaNacimiento(fechaNacimiento);
            mascota.setSexo(sexo);
            mascota.setUrlFoto(urlFoto);

            mascotaService.actualizarMascota(mascota);
            logger.info("✏️ Mascota actualizada exitosamente: " + nombre);
            return true;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al actualizar mascota: " + e.getMessage(), e);
            return false;
        }
    }

    // DELETE - Eliminar mascota
    public boolean eliminarMascota(Integer id) {
        try {
            mascotaService.eliminarMascota(id);
            logger.info("🗑️ Mascota eliminada exitosamente, ID: " + id);
            return true;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al eliminar mascota: " + e.getMessage(), e);
            return false;
        }
    }

    // Método adicional: Contar mascotas por dueño
    public int contarMascotasPorDueno(Integer duenoId) {
        try {
            int cantidad = mascotaService.contarMascotasPorDueno(duenoId);
            logger.info("📊 Dueño ID " + duenoId + " tiene " + cantidad + " mascotas");
            return cantidad;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error al contar mascotas: " + e.getMessage(), e);
            return 0;
        }
    }

    // Método adicional: Validar datos antes de operaciones
    public boolean validarDatosMascota(String nombre, Integer duenoId, Integer razaId, String sexo) {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                return false;
            }
            if (duenoId == null || duenoId <= 0) {
                return false;
            }
            if (razaId == null || razaId <= 0) {
                return false;
            }
            if (sexo == null || (!sexo.equals("Macho") && !sexo.equals("Hembra"))) {
                return false;
            }
            return true;
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "❌ Error en validación de datos: " + e.getMessage(), e);
            return false;
        }
    }
}
package org.example.Repository;

import org.example.model.entities.Citas;
import java.util.List;
import java.util.ArrayList;

public class CitasDAO implements ICitasDAO {
    
    @Override
    public void crear(Citas cita) {
        // Implementar lógica de base de datos
        System.out.println("Creando cita: " + cita);
    }
    
    @Override
    public Citas buscarPorId(int id) {
        // Implementar lógica de base de datos
        return null;
    }
    
    @Override
    public List<Citas> listarPorMascota(int mascotaId) {
        // Implementar lógica de base de datos
        return new ArrayList<>();
    }
    
    @Override
    public List<Citas> listarTodas() {
        // Implementar lógica de base de datos
        return new ArrayList<>();
    }
    
    @Override
    public void actualizar(Citas cita) {
        // Implementar lógica de base de datos
    }
    
    @Override
    public void eliminar(int id) {
        // Implementar lógica de base de datos
    }
}
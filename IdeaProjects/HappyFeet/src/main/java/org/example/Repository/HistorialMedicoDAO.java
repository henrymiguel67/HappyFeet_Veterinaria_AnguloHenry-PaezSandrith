package org.example.Repository;

import org.example.model.entities.HistorialMedico;
import java.util.List;
import java.util.ArrayList;

public class HistorialMedicoDAO implements IHistorialMedicoDAO {
    
    @Override
    public void crear(HistorialMedico historial) {
        // Implementar lógica de base de datos
    }
    
    @Override
    public HistorialMedico buscarPorId(int id) {
        // Implementar lógica de base de datos
        return null;
    }
    
    @Override
    public List<HistorialMedico> listarPorMascota(int mascotaId) {
        // Implementar lógica de base de datos
        return new ArrayList<>();
    }
    
    @Override
    public void actualizar(HistorialMedico historial) {
        // Implementar lógica de base de datos
    }
    
    @Override
    public void eliminar(int id) {
        // Implementar lógica de base de datos
    }
}
package org.example.Service;

import org.example.model.entities.HistorialMedico;
import org.example.repository.HistorialMedicoDAO;
import java.util.List;

public class HistorialMedicoService {
    private HistorialMedicoDAO historialDAO;
    
    public HistorialMedicoService() {
        this.historialDAO = new HistorialMedicoDAO();
    }
    
    public void registrarHistorial(HistorialMedico historial) {
        historialDAO.crear(historial);
    }
    
    public HistorialMedico buscarHistorialPorId(int id) {
        return historialDAO.buscarPorId(id);
    }
    
    public List<HistorialMedico> obtenerHistorialMascota(int mascotaId) {
        return historialDAO.listarPorMascota(mascotaId);
    }
    
    public void actualizarHistorial(HistorialMedico historial) {
        historialDAO.actualizar(historial);
    }
    
    public void eliminarHistorial(int id) {
        historialDAO.eliminar(id);
    }
}
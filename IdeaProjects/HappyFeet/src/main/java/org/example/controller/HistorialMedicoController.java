package org.example.controller;

import org.example.model.entities.HistorialMedico;
import org.example.Service.HistorialMedicoService;
import java.util.List;

public class HistorialMedicoController {
    private HistorialMedicoService historialService;
    
    public HistorialMedicoController() {
        this.historialService = new HistorialMedicoService();
    }
    
    public void registrarHistorial(HistorialMedico historial) {
        historialService.registrarHistorial(historial);
    }
    
    public HistorialMedico consultarHistorial(int id) {
        return historialService.buscarHistorialPorId(id);
    }
    
    public List<HistorialMedico> consultarHistorialMascota(int mascotaId) {
        return historialService.obtenerHistorialMascota(mascotaId);
    }
    
    public void actualizarHistorial(HistorialMedico historial) {
        historialService.actualizarHistorial(historial);
    }
    
    public void eliminarHistorial(int id) {
        historialService.eliminarHistorial(id);
    }
}
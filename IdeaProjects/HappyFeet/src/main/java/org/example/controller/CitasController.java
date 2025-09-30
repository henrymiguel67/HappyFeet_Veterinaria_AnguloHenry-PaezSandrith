package org.example.controller;

import org.example.model.entities.Citas;
import org.example.Service.CitasService;
import java.util.List;

public class CitasController {
    private CitasService citasService;
    
    public CitasController() {
        this.citasService = new CitasService();
    }
    
    public void programarCita(Citas cita) {
        citasService.programarCita(cita);
    }
    
    public Citas consultarCita(int id) {
        return citasService.buscarCitaPorId(id);
    }
    
    public List<Citas> consultarCitasMascota(int mascotaId) {
        return citasService.obtenerCitasPorMascota(mascotaId);
    }
    
    public List<Citas> consultarTodasLasCitas() {
        return citasService.obtenerTodasLasCitas();
    }
    
    public void actualizarCita(Citas cita) {
        citasService.actualizarCita(cita);
    }
    
    public void cancelarCita(int id) {
        citasService.cancelarCita(id);
    }
}
package org.example.Service;

import org.example.model.entities.Citas;
import org.example.repository.CitasDAO;
import java.util.List;

public class CitasService {
    private CitasDAO citasDAO;
    
    public CitasService() {
        this.citasDAO = new CitasDAO();
    }
    
    public void programarCita(Citas cita) {
        citasDAO.crear(cita);
    }
    
    public Citas buscarCitaPorId(int id) {
        return citasDAO.buscarPorId(id);
    }
    
    public List<Citas> obtenerCitasPorMascota(int mascotaId) {
        return citasDAO.listarPorMascota(mascotaId);
    }
    
    public List<Citas> obtenerTodasLasCitas() {
        return citasDAO.listarTodas();
    }
    
    public void actualizarCita(Citas cita) {
        citasDAO.actualizar(cita);
    }
    
    public void cancelarCita(int id) {
        citasDAO.eliminar(id);
    }
}
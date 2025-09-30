package org.example.Repository;

import org.example.model.entities.HistorialMedico;
import java.util.List;

public interface IHistorialMedicoDAO {
    void crear(HistorialMedico historial);
    HistorialMedico buscarPorId(int id);
    List<HistorialMedico> listarPorMascota(int mascotaId);
    void actualizar(HistorialMedico historial);
    void eliminar(int id);
}
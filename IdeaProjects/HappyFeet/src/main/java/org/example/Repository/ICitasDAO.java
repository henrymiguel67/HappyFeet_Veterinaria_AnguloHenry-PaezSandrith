package org.example.Repository;

import org.example.model.entities.Citas;
import java.util.List;

public interface ICitasDAO {
    void crear(Citas cita);
    Citas buscarPorId(int id);
    List<Citas> listarPorMascota(int mascotaId);
    List<Citas> listarTodas();
    void actualizar(Citas cita);
    void eliminar(int id);
}
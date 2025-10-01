package org.example.Repository;

import org.example.model.entities.Dueno;
import java.util.List;

public interface IDuenoDAO {
    void agregarDueno(Dueno dueno) throws DuenoRepositoryException;

    List<Dueno> listarTodos() throws DuenoRepositoryException;

    List<Dueno> buscarPorNombreOTelefono(String criterio) throws DuenoRepositoryException;

    void eliminarDueno(int id) throws DuenoRepositoryException;

    Dueno buscarPorId(int id) throws DuenoRepositoryException;

    void actualizarDueno(Dueno dueno) throws DuenoRepositoryException;
}

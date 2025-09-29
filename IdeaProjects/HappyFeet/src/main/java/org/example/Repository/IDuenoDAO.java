package org.example.Repository;

import org.example.Repository.DuenoRepositoryException;
import org.example.model.entities.Dueno;
import java.util.List;

public interface IDuenoDAO {
    void agregarDueno(Dueno dueno) throws DuenoRepositoryException;
    List<Dueno> listarTodos() throws DuenoRepositoryException;
}

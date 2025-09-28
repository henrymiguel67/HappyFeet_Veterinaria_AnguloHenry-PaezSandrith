package org.example.model.entities;

import org.example.model.entities.Repository.DuenoRepositoryException;

import java.util.List;

/**
 * Interfaz para operaciones CRUD de la entidad Dueno
 */
public interface IDuenoDAO {
    /**
     * Agrega un nuevo dueño a la base de datos
     * @param dueno Objeto Dueno a agregar
     * @throws DuenoRepositoryException en caso de error
     */
    void agregarDueno(Dueno dueno) throws DuenoRepositoryException;

    /**
     * Lista todos los dueños registrados en la base de datos
     * @return Lista de objetos Dueno
     * @throws DuenoRepositoryException en caso de error
     */
    List<Dueno> listarTodos();
}

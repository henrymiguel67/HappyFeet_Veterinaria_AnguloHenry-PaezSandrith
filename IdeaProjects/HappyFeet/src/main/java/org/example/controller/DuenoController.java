package org.example.controller;

import org.example.model.entities.Dueno;
import org.example.Repository.IDuenoDAO;
import org.example.Repository.DuenoRepositoryException;

import java.util.List;

public record DuenoController(IDuenoDAO duenoDAO) {

    public void agregarDueno(Dueno dueno) throws Exception {
        if (validarDueno(dueno)) {
            duenoDAO.agregarDueno(dueno);
        } else {
            throw new Exception("Datos del dueño inválidos.");
        }
    }

    private boolean validarDueno(Dueno dueno) {
        return dueno != null &&
                dueno.getNombre() != null && !dueno.getNombre().isEmpty() &&
                dueno.getTelefono() != null && !dueno.getTelefono().isEmpty();
    }

    public void crear(Dueno dueno) throws Exception {
        agregarDueno(dueno);
    }

    public List<Dueno> listar() throws DuenoRepositoryException {
        return duenoDAO.listarTodos();
    }

    public void actualizar(Dueno dueno) throws DuenoRepositoryException {
        if (dueno.getId() <= 0) {
            throw new IllegalArgumentException("ID de dueño inválido para actualizar.");
        }
        if (!validarDueno(dueno)) {
            throw new IllegalArgumentException("Datos del dueño inválidos.");
        }
        duenoDAO.actualizarDueno(dueno);
    }

    public void eliminar(int id) throws DuenoRepositoryException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        duenoDAO.eliminarDueno(id);
    }

    public List<Dueno> buscarPorCriterio(String criterio) throws DuenoRepositoryException {
        if (criterio == null || criterio.trim().isEmpty()) {
            throw new IllegalArgumentException("Criterio de búsqueda inválido.");
        }
        return duenoDAO.buscarPorNombreOTelefono(criterio);
    }

    public Dueno buscarDuenoPorId(int id) throws DuenoRepositoryException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }
        return duenoDAO.buscarPorId(id);
    }

    public void registrarDueno(Dueno nuevoDueno) throws Exception {
        agregarDueno(nuevoDueno);
    }

    public List<Dueno> listarDuenos() throws DuenoRepositoryException {
        return listar();
    }
}

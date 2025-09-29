package org.example.Service;

import org.example.Repository.DuenoDAO;
import org.example.Repository.DuenoRepositoryException;
import org.example.model.entities.Dueno;

import java.util.List;

public class DuenoService {

    private final DuenoDAO duenoDAO;

    public DuenoService(DuenoDAO duenoDAO) {
        this.duenoDAO = duenoDAO;
    }

    public void agregarDueno(Dueno dueno) throws DuenoRepositoryException {
        if (validarDueno(dueno)) {
            duenoDAO.agregarDueno(dueno);
        } else {
            throw new DuenoRepositoryException("Datos del dueño incompletos o inválidos");
        }
    }

    public List<Dueno> listarTodos() throws DuenoRepositoryException {
        return duenoDAO.listarTodos();
    }

    private boolean validarDueno(Dueno dueno) {
        if (dueno == null) return false;
        if (dueno.getNombre() == null || dueno.getNombre().isEmpty()) return false;
        if (dueno.getTelefono() == null || dueno.getTelefono().isEmpty()) return false;
        else (dueno.getDocumentoIdentidad() == null || dueno.getDocumentoIdentidad().isEmpty()) return false;
        return true;
    }
}

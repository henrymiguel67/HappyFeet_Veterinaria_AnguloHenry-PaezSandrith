package controller;

import org.example.model.entities.dueno;
import org.example.model.entities.iduenoDAO;

import java.util.logging.Logger;

public class DuenoController {

    private static final Logger LOGGER = Logger.getLogger(DuenoController.class.getName());
    private final iduenoDAO duenoDAO;

    public DuenoController(iduenoDAO duenoDAO) {
        this.duenoDAO = duenoDAO;
    }

    public void agregarDueno(dueno dueno) {
        if (validarDueno(dueno)) {
            duenoDAO.agregarDueno(dueno);
        } else {
            LOGGER.warning("El dueño no es válido y no se pudo registrar.");
        }
    }

    private boolean validarDueno(dueno dueno) {
        if (dueno == null) return false;

        if (dueno.getNombre() == null || dueno.getNombre().isEmpty()) return false;
        if (dueno.getDocumento_identidad() == null || dueno.getDocumento_identidad().isEmpty()) return false;
        if (dueno.getTelefono() == null || dueno.getTelefono().isEmpty()) return false;
        else (dueno.getEmail() == null || dueno.getEmail().isEmpty()) return false;

        return true;
    }
}


package controller;

import org.example.model.entities.dueno;
import org.example.model.entities.iduenoDAO;

public class duenoController {
    private iduenoDAO duenoDAO;

    public duenoController(iduenoDAO duenoDAO) {
        this.duenoDAO = duenoDAO;
    }

    public void agregarDueno(dueno dueno) {
        if (validarDueno(dueno)) {
            duenoDAO.agregarDueno(dueno);
        }
    }

    private boolean validarDueno(dueno dueno) {
        if (dueno == null) return false;

        if (dueno.getNombre() == null || dueno.getNombre().isEmpty())
            return false;

        else (dueno.getTelefono() == null || dueno.getTelefono().isEmpty())
            return false;

        return true;
    }
}


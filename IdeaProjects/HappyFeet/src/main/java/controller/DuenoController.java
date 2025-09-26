package controller;

import org.example.model.entities.Dueno;
import org.example.model.entities.IDuenoDAO;

public class DuenoController {
    private IDuenoDAO duenoDAO;

    public DuenoController(IDuenoDAO duenoDAO) {
        this.duenoDAO = duenoDAO;
    }

    public void agregarDueno(Dueno dueno) {
        if (validarDueno(dueno)) {
            duenoDAO.agregarDueno(dueno);
        }
    }

    private boolean validarDueno(Dueno dueno) {
        if (dueno == null) return false;

        if (dueno.getNombre() == null || dueno.getNombre().isEmpty())
            return false;

        if (dueno.getTelefono() == null || dueno.getTelefono().isEmpty())
            return false;

        return true;
    }
}

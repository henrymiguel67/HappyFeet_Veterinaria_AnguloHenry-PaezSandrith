package org.example.controller;

import org.example.model.entities.Dueno;
import org.example.Repository.IDuenoDAO;

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
}

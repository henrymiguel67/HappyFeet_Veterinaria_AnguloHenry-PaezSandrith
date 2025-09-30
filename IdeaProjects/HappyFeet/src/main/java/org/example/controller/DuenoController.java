package org.example.controller;

import org.example.model.entities.Dueno;

import java.util.List;

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

    public void crear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crear'");
    }

    public void listar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listar'");
    }

    public void actualizar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    public void eliminar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    public void buscarPorCriterio() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorCriterio'");
    }

    public Dueno buscarDuenoPorId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarDuenoPorId'");
    }

    public void registrarDueno(Dueno nuevoDueno) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrarDueno'");
    }

    public List<Dueno> listarDuenos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarDuenos'");
    }
}

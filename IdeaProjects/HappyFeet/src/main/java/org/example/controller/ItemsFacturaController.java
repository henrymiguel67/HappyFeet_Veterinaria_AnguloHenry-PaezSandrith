package org.example.controller;

import org.example.model.entities.ItemsFactura;
import org.example.Repository.ItemsFacturaDAO;
import org.example.Repository.ItemsFacturaException;

public record ItemsFacturaController(ItemsFacturaDAO dao) {

    public void agregarItemFactura(ItemsFactura item) throws ItemsFacturaException {
        if (validarItem(item)) {
            dao.agregarItemFactura(item);
        } else {
            throw new ItemsFacturaException("Datos del item inválidos");
        }
    }

    private boolean validarItem(ItemsFactura item) {
        return item != null &&
                item.getCantidad() > 0 &&
                item.getPrecioUnitario() >= 0;
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
}

package org.example.Service;

import org.example.Repository.ItemsFacturaDAO;
import org.example.model.entities.ItemsFactura;
import org.example.Repository.ItemsFacturaException;

import java.util.List;

public class ItemsFacturaService {
    private ItemsFacturaDAO itemsFacturaDAO;
    
    public ItemsFacturaService() {
        this.itemsFacturaDAO = new ItemsFacturaDAO();
    }
    
    public void crearItemFactura(ItemsFactura item) throws ItemsFacturaException {
        itemsFacturaDAO.agregarItemFactura(item);
    }
    
    public List<ItemsFactura> obtenerItemsPorFactura(int facturaId) throws ItemsFacturaException {
        return itemsFacturaDAO.buscarPorFactura(facturaId);
    }
    
    public List<ItemsFactura> listarTodosLosItems() throws ItemsFacturaException {
        return itemsFacturaDAO.listarTodos();
    }
    
     
    public void eliminarItemFactura(int id) throws ItemsFacturaException {
        itemsFacturaDAO.eliminarItemFactura(id);
    }
}
package org.example.Service;

import org.example.model.entities.ItemsFactura;
import org.example.repository.ItemsFacturaDAO;
import java.util.List;

public class ItemsFacturaService {
    private ItemsFacturaDAO itemsFacturaDAO;
    
    public ItemsFacturaService() {
        this.itemsFacturaDAO = new ItemsFacturaDAO();
    }
    
    public void crearItemFactura(ItemsFactura item) {
        itemsFacturaDAO.crear(item);
    }
    
    public List<ItemsFactura> obtenerItemsPorFactura(int facturaId) {
        return itemsFacturaDAO.listarPorFactura(facturaId);
    }
    
    public void eliminarItem(int id) {
        itemsFacturaDAO.eliminar(id);
    }
}
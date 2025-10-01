package org.example.View;

import org.example.model.entities.ItemsFactura;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewItemsFactura {
    private static final Logger logger = Logger.getLogger(ViewItemsFactura.class.getName());

    // Constructor con Logger
    public ViewItemsFactura() {
       
    }

    public void mostrarMenu() {
        logger.info("\n--- Gestión de Items de Factura ---");
        logger.info("1. Agregar item");
        logger.info("2. Listar items");
        logger.info("0. Volver al menú principal");
        logger.info("Seleccione una opción: ");
    }

    public void mostrarItems(List<ItemsFactura> items) {
        if (items == null || items.isEmpty()) {
            logger.info("No hay items de factura registrados.");
            return;
        }

        logger.info("\n--- Lista de Items de Factura ---");
        for (ItemsFactura item : items) {
            logger.log(Level.INFO, "ID: {0}", item.getId());
            logger.log(Level.INFO, "Factura ID: {0}", item.getIdFactura());
            logger.log(Level.INFO, "Producto ID: {0}", item.getProductoId());
            logger.log(Level.INFO, "Descripci\u00f3n: {0}", item.getServicioDescripcion());
            logger.log(Level.INFO, "Cantidad: {0}", item.getCantidad());
            logger.log(Level.INFO, "Precio: {0}", item.getPrecioUnitario());
            logger.log(Level.INFO, "Subtotal: {0}", item.getSubtotal());
            logger.info("------------------------");
        }
    }

    public void agregarItem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarItem'");
    }

    public void listarItems() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarItems'");
    }
}
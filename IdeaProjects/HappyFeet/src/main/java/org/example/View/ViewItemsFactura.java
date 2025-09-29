package org.example.View;

import org.example.model.entities.ItemsFactura;
import java.util.List;
import java.util.logging.Logger;

public class ViewItemsFactura {
    private final Logger logger;

    // Constructor con Logger
    public ViewItemsFactura() {
        this.logger = Logger.getLogger(ViewItemsFactura.class.getName());
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
            logger.info("ID: " + item.getId());
            logger.info("Factura ID: " + item.getIdFactura());
            logger.info("Producto ID: " + item.getProductoId());
            logger.info("Descripción: " + item.getServicioDescripcion());
            logger.info("Cantidad: " + item.getCantidad());
            logger.info("Precio: " + item.getPrecioUnitario());
            logger.info("Subtotal: " + item.getSubtotal());
            logger.info("------------------------");
        }
    }
}
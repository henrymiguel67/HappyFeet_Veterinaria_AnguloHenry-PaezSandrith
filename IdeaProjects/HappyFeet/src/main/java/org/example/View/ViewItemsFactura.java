package org.example.View;

import org.example.model.entities.ItemsFactura;
import java.util.List;

public class ViewItemsFactura {

    public void mostrarMenu() {
        System.out.println("\n--- Gestión de Items de Factura ---");
        System.out.println("1. Agregar item");
        System.out.println("2. Listar items");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
    }

    public void mostrarItems(List<ItemsFactura> items) {
        System.out.println("\n--- Lista de Items de Factura ---");
        for (ItemsFactura i : items) {
            System.out.println(i);
        }
    }
}

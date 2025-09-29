package org.example.View;

import org.example.model.entities.Dueno;
import java.util.List;

public class View {

    public void mostrarMenu() {
        System.out.println("\n--- Gestión de Dueños ---");
        System.out.println("1. Agregar dueño");
        System.out.println("2. Listar dueños");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
    }

    public void mostrarDuenos(List<Dueno> duenos) {
        System.out.println("\n--- Lista de Dueños ---");
        for (Dueno d : duenos) {
            System.out.println(d);
        }
    }
}


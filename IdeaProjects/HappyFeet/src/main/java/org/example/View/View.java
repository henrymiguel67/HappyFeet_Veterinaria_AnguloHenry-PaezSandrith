package org.example.View;

import org.example.model.entities.Dueno;

import java.util.List;
import java.util.Scanner;

public class View {
    private Scanner scanner;
    
    public View() {
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n==========================================");
            System.out.println("    🏥 SISTEMA HAPPY FEET VETERINARIA   ");
            System.out.println("==========================================");
            System.out.println("1. 👥 Gestión de Dueños");
            System.out.println("2. 🐕 Gestión de Mascotas");
            System.out.println("3. 🩺 Gestión de Citas");
            System.out.println("4. 📦 Gestión de Inventario");
            System.out.println("5. 🧾 Gestión de Facturas");
            System.out.println("0. 🚪 Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1:
                    gestionarDueños();
                    break;
                case 2:
                    System.out.println("Módulo de Mascotas - Próximamente...");
                    break;
                case 3:
                    System.out.println("Módulo de Citas - Próximamente...");
                    break;
                case 4:
                    System.out.println("Módulo de Inventario - Próximamente...");
                    break;
                case 5:
                    System.out.println("Módulo de Facturas - Próximamente...");
                    break;
                case 0:
                    System.out.println("👋 ¡Gracias por usar Happy Feet Veterinaria!");
                    break;
                default:
                    System.out.println("❌ Opción inválida");
            }
        } while (opcion != 0);
        
        scanner.close();
    }
    
    private void gestionarDueños() {
        System.out.println("\n--- Gestión de Dueños ---");
        // Aquí integrarás DuenoController
        System.out.println("Funcionalidad en desarrollo...");
    }

    public void mostrarDuenos(List<Dueno> duenos) {
        if (duenos == null || duenos.isEmpty()) {
            System.out.println("❌ No se encontraron dueños.");
        } else {
            System.out.println("\n===== Lista de Dueños =====");
            for (Dueno dueno : duenos) {
                System.out.println("ID: " + dueno.getId());
                System.out.println("Nombre: " + dueno.getNombre());
                System.out.println("Teléfono: " + dueno.getTelefono());
                System.out.println("Email: " + dueno.getEmail());
                System.out.println("Documento de Identidad: " + dueno.getDocumentoIdentidad());
                System.out.println("------------------------------------");
            }
        }
    }

    // Otros métodos para la vista...
    public void mostrarMenu() {
        System.out.println("\n===== Menú de Gestión de Dueños =====");
        System.out.println("1. Agregar nuevo dueño");
        System.out.println("2. Listar todos los dueños");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
    }
}
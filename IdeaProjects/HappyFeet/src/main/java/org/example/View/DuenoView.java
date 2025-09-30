package org.example.View;

import org.example.controller.DuenoController;
import org.example.model.entities.Dueno;
import java.util.List;
import java.util.Scanner;

public class DuenoView {
    private final DuenoController duenoController;
    private final Scanner scanner;
    
    public DuenoView() {
        this.duenoController = new DuenoController();
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== GESTIÓN DE DUEÑOS ===");
            System.out.println("1. Registrar dueño");
            System.out.println("2. Listar dueños");
            System.out.println("3. Buscar dueño por ID");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            
            switch (opcion) {
                case 1:
                    registrarDueno();
                    break;
                case 2:
                    listarDuenos();
                    break;
                case 3:
                    buscarDuenoPorId();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 4);
    }
    
    public void registrarDueno() {
        System.out.println("\n=== REGISTRAR NUEVO DUEÑO ===");
        System.out.print("Ingrese el nombre del dueño: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Ingrese el teléfono del dueño: ");
        String telefono = scanner.nextLine();
        

        
        System.out.print("Ingrese el email del dueño: ");
        String email = scanner.nextLine();
        
        System.out.print("Ingrese el documento de identidad del dueño: ");
        String documentoIdentidad = scanner.nextLine();
        
        Dueno nuevoDueno = new Dueno(0, documentoIdentidad, documentoIdentidad, documentoIdentidad, documentoIdentidad);
        nuevoDueno.setNombre(nombre);
        nuevoDueno.setTelefono(telefono);
        nuevoDueno.setEmail(email);
        nuevoDueno.setDocumentoIdentidad(documentoIdentidad);
        
        duenoController.registrarDueno(nuevoDueno);
        System.out.println("Dueño registrado exitosamente!");
    }
    
    public void listarDuenos() {
        System.out.println("\n=== LISTA DE DUEÑOS ===");
        List<Dueno> duenos = duenoController.listarDuenos(); // Tipo genérico añadido
        
        if (duenos.isEmpty()) {
            System.out.println("No hay dueños registrados.");
        } else {
            for (Dueno dueno : duenos) {
                System.out.println("ID: " + dueno.getId());
                System.out.println("Nombre: " + dueno.getNombre());
                System.out.println("Teléfono: " + dueno.getTelefono());
                System.out.println("Email: " + dueno.getEmail());
                System.out.println("Documento Identidad: " + dueno.getDocumentoIdentidad());
                System.out.println("--------------------");
            }
        }
    }
    
    public void buscarDuenoPorId() {
        System.out.println("\n=== BUSCAR DUEÑO POR ID ===");
        System.out.print("Ingrese el ID del dueño: ");
        int id = scanner.nextInt(); // Cambiado de Long a int para coincidir con la clase Dueno
        scanner.nextLine(); // Limpiar el buffer
        
        Dueno dueno = duenoController.buscarDuenoPorId(id);
        
        if (dueno != null) {
            System.out.println("\nDatos del dueño:");
            System.out.println("ID: " + dueno.getId());
            System.out.println("Nombre: " + dueno.getNombre());
            System.out.println("Teléfono: " + dueno.getTelefono());
            System.out.println("Email: " + dueno.getEmail());
            System.out.println("Documento Identidad: " + dueno.getDocumentoIdentidad());
        } else {
            System.out.println("No se encontró ningún dueño con el ID proporcionado.");
        }
    }
}
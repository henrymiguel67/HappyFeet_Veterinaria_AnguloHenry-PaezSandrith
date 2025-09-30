package org.example.View;

import org.example.controller.CitasController;
import org.example.model.entities.Citas;
import java.util.List;
import java.util.Scanner;

public class CitasView {
    private CitasController citasController;
    private Scanner scanner;
    
    public CitasView() {
        this.citasController = new CitasController();
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu() {
        System.out.println("=== GESTIÓN DE CITAS ===");
        System.out.println("1. Programar cita");
        System.out.println("2. Consultar citas por mascota");
        System.out.println("3. Listar todas las citas");
        System.out.println("4. Cancelar cita");
        System.out.println("5. Volver al menú principal");
    }
    
    public void programarCita() {
        System.out.println("=== PROGRAMAR NUEVA CITA ===");
        // Lógica para programar cita
    }
    
    public void consultarCitasMascota() {
        System.out.print("Ingrese ID de la mascota: ");
        int mascotaId = scanner.nextInt();
        List<Citas> citas = citasController.consultarCitasMascota(mascotaId);
        
        if (citas.isEmpty()) {
            System.out.println("No se encontraron citas para esta mascota.");
        } else {
            System.out.println("=== CITAS DE LA MASCOTA ===");
            for (Citas cita : citas) {
                System.out.println(cita);
            }
        }
    }
    
    public void listarTodasLasCitas() {
        List<Citas> citas = citasController.consultarTodasLasCitas();
        
        if (citas.isEmpty()) {
            System.out.println("No hay citas programadas.");
        } else {
            System.out.println("=== TODAS LAS CITAS ===");
            for (Citas cita : citas) {
                System.out.println(cita);
            }
        }
    }
}
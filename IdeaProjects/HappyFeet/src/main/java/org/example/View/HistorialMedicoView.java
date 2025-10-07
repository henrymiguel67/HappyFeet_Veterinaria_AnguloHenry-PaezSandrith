package org.example.View;

import org.example.controller.HistorialMedicoController;
import org.example.model.entities.HistorialMedico;
import java.util.List;
import java.util.Scanner;

public class HistorialMedicoView {
    private final HistorialMedicoController historialController;
    private final Scanner scanner;
    
    public HistorialMedicoView() {
        this.historialController = new HistorialMedicoController();
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu() {
        System.out.println("=== GESTIÓN DE HISTORIAL MÉDICO ===");
        System.out.println("1. Registrar historial médico");
        System.out.println("2. Consultar historial por mascota");
        System.out.println("3. Actualizar historial");
        System.out.println("4. Volver al menú principal");
    }
    
    public void registrarHistorial() {
        System.out.println("=== REGISTRAR HISTORIAL MÉDICO ===");
        // Lógica para registrar historial médico
    }
    
    public void consultarHistorialMascota() {
        System.out.print("Ingrese ID de la mascota: ");
        int mascotaId = scanner.nextInt();
        List<HistorialMedico> historiales = historialController.consultarHistorialMascota(mascotaId);
        
        if (historiales.isEmpty()) {
            System.out.println("No se encontraron historiales para esta mascota.");
        } else {
            System.out.println("=== HISTORIAL MÉDICO ===");
            for (HistorialMedico historial : historiales) {
                System.out.println(historial);
            }
        }
    }
}
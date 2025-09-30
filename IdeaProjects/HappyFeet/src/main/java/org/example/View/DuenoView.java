package org.example.view;

import org.example.controller.DuenoController;
import org.example.model.entities.Dueno;
import java.util.List;
import java.util.Scanner;

public class DuenoView {
    private DuenoController duenoController;
    private Scanner scanner;
    
    public DuenoView() {
        this.duenoController = new DuenoController();
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu() {
        System.out.println("=== GESTIÓN DE DUEÑOS ===");
        System.out.println("1. Registrar dueño");
        System.out.println("2. Listar dueños");
        System.out.println("3. Buscar dueño por ID");
        System.out.println("4. Salir");
    }
    
    public void registrarDueno() {
        System.out.println("=== REGISTRAR NUEVO DUEÑO ===");
        // Lógica para registrar dueño
    }
    
    public void listarDuenos() {
        System.out.println("=== LISTA DE DUEÑOS ===");
        // Lógica para listar dueños
    }
}
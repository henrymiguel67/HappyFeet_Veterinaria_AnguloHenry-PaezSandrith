
package org.example.View;

import org.example.controller.FacturaController;
import org.example.model.entities.Factura;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class FacturaView {
    private final FacturaController facturaController;
    private final Scanner scanner;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public FacturaView(FacturaController facturaController) {
        this.facturaController = facturaController;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ FACTURAS ===");
            System.out.println("1. Crear factura");
            System.out.println("2. Listar facturas");
            System.out.println("3. Buscar factura por ID");
            System.out.println("4. Buscar facturas por dueño");
            System.out.println("5. Buscar facturas por rango de fechas");
            System.out.println("6. Eliminar factura");
            System.out.println("7. Reporte de facturación");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> crearFactura();
                case 2 -> listarFacturas();
                case 3 -> buscarFacturaPorId();
                case 4 -> buscarFacturasPorDueno();
                case 5 -> buscarFacturasPorRangoFechas();
                case 6 -> eliminarFactura();
                case 7 -> generarReporteFacturacion();
                case 0 -> System.out.println("Saliendo del módulo de facturas...");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void crearFactura() {
        System.out.print("Ingrese ID del dueño: ");
        int duenoId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese total de la factura: ");
        double total = scanner.nextDouble();
        scanner.nextLine();

        LocalDateTime fecha = LocalDateTime.now();
        Factura factura = new Factura(null, duenoId, fecha, total);

        boolean creada = facturaController.generarFactura(factura);
        if (creada) {
            System.out.println("✅ Factura creada correctamente.");
        } else {
            System.out.println("❌ No se pudo crear la factura.");
        }
    }

    public void listarFacturas() {
        List<Factura> facturas = facturaController.listarTodasLasFacturas();
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas registradas.");
        } else {
            facturas.forEach(System.out::println);
        }
    }

    private void buscarFacturaPorId() {
        System.out.print("Ingrese ID de la factura: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Optional<Factura> factura = facturaController.buscarFacturaPorId(id);
        factura.ifPresentOrElse(
            System.out::println,
            () -> System.out.println("❌ Factura no encontrada.")
        );
    }

    private void buscarFacturasPorDueno() {
        System.out.print("Ingrese ID del dueño: ");
        int duenoId = scanner.nextInt();
        scanner.nextLine();

        List<Factura> facturas = facturaController.buscarFacturasPorDueno(duenoId);
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas para este dueño.");
        } else {
            facturas.forEach(System.out::println);
        }
    }

    private void buscarFacturasPorRangoFechas() {
        System.out.print("Ingrese fecha de inicio (yyyy-MM-dd HH:mm): ");
        String inicioStr = scanner.nextLine();
        System.out.print("Ingrese fecha de fin (yyyy-MM-dd HH:mm): ");
        String finStr = scanner.nextLine();

        LocalDateTime fechaInicio = LocalDateTime.parse(inicioStr, formatter);
        LocalDateTime fechaFin = LocalDateTime.parse(finStr, formatter);

        List<Factura> facturas = facturaController.buscarFacturasPorRangoFechas(fechaInicio, fechaFin);
        if (facturas.isEmpty()) {
            System.out.println("No se encontraron facturas en ese rango.");
        } else {
            facturas.forEach(System.out::println);
        }
    }

    public void eliminarFactura() {
        System.out.print("Ingrese ID de la factura a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean eliminada = facturaController.eliminarFactura(id);
        if (eliminada) {
            System.out.println("✅ Factura eliminada correctamente.");
        } else {
            System.out.println("❌ No se pudo eliminar la factura.");
        }
    }

    private void generarReporteFacturacion() {
        System.out.print("Ingrese fecha de inicio (yyyy-MM-dd HH:mm): ");
        String inicioStr = scanner.nextLine();
        System.out.print("Ingrese fecha de fin (yyyy-MM-dd HH:mm): ");
        String finStr = scanner.nextLine();

        LocalDateTime fechaInicio = LocalDateTime.parse(inicioStr, formatter);
        LocalDateTime fechaFin = LocalDateTime.parse(finStr, formatter);

        facturaController.generarReporteEstadisticas(fechaInicio, fechaFin);
    }
}


package org.example;

import org.example.Repository.DuenoDAO;
import org.example.Repository.ItemsFacturaDAO;
import org.example.Repository.ItemsFacturaException;
import org.example.controller.DuenoController;
import org.example.controller.ItemsFacturaController;
import org.example.model.entities.*;
import org.example.View.View;
import org.example.View.ViewItemsFactura;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    static {
        Logger.getLogger(Main.class.getName());
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Inicializamos DAOs
        DuenoDAO duenoDAO = new DuenoDAO();
        ItemsFacturaDAO itemsDAO = new ItemsFacturaDAO();

        // Inicializamos Controllers
        DuenoController duenoController = new DuenoController(duenoDAO);
        ItemsFacturaController itemsController = new ItemsFacturaController(itemsDAO);

        // Inicializamos Vistas
        View duenoView = new View();
        ViewItemsFactura itemsView = new ViewItemsFactura();

        int opcion;
        do {
            System.out.println("\n===== Sistema Veterinaria Happy Feet =====");
            System.out.println("1. Gestión de Dueños");
            System.out.println("2. Gestión de Items de Factura");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    gestionarDuenos(scanner, duenoController, duenoView, duenoDAO);
                    break;
                case 2:
                    gestionarItems(scanner, itemsController, itemsView, itemsDAO);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    private static void gestionarDuenos(Scanner scanner, DuenoController controller, View view, DuenoDAO dao) {
        int opcion;
        do {
            view.mostrarMenu();
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1: // Agregar dueño
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Documento de identidad: ");
                    String doc = scanner.nextLine();

                    Dueno dueno = new Dueno(0, nombre, telefono, email, doc);
                    try {
                        controller.agregarDueno(dueno);
                        System.out.println("Dueño agregado correctamente.");
                    } catch (Exception e) {
                        System.out.println("Error al agregar dueño: " + e.getMessage());
                    }
                    break;

                case 2: // Listar todos
                    try {
                        List<Dueno> duenos = dao.listarTodos();
                        view.mostrarDuenos(duenos);
                    } catch (Exception e) {
                        System.out.println("Error al listar dueños: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    private static void gestionarItems(Scanner scanner, ItemsFacturaController controller, ViewItemsFactura view, ItemsFacturaDAO dao) {
        int opcion;
        do {
            view.mostrarMenu();
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1: // Agregar item
                    System.out.print("ID Factura: ");
                    int idFactura = Integer.parseInt(scanner.nextLine());
                    System.out.print("ID Producto: ");
                    int idProducto = Integer.parseInt(scanner.nextLine());
                    System.out.print("Descripción del servicio: ");
                    String desc = scanner.nextLine();
                    System.out.print("Cantidad: ");
                    int cantidad = Integer.parseInt(scanner.nextLine());
                    System.out.print("Precio unitario: ");
                    double precio = Double.parseDouble(scanner.nextLine());
                    double subtotal = precio * cantidad;

                    ItemsFactura item = new ItemsFactura(0, idFactura, idProducto, desc, cantidad, precio, subtotal);
                    try {
                        controller.agregarItemFactura(item);
                        System.out.println("Item agregado correctamente con ID: " + item.getId());
                    } catch (ItemsFacturaException e) {
                        System.out.println("Error al agregar item: " + e.getMessage());
                    }
                    break;

                case 2: // Listar items
                    try {
                        List<ItemsFactura> items = dao.listarTodos();
                        view.mostrarItems(items);
                    } catch (ItemsFacturaException e) {
                        System.out.println("Error al listar items: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }
}

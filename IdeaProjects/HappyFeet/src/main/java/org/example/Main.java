package org.example;


import org.example.View.*;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        System.out.println("🐾".repeat(50));
        System.out.println("🌟 " + "¡BIENVENIDO A HAPPY FEET VETERINARIA!".toUpperCase() + " 🌟");
        System.out.println("🐾".repeat(50));
        logger.info("Iniciando Sistema Happy Feet Veterinaria...");

        try (Scanner scanner = new Scanner(System.in)) {
            // Inicializamos Vistas (los controllers se inicializan dentro de las vistas)
            DuenoView duenoView = new DuenoView();
            MascotaView mascotaView = new MascotaView();
            FacturaView facturaView = new FacturaView(null);
            InventarioView inventarioView = new InventarioView();
            ViewItemsFactura itemsFacturaView = new ViewItemsFactura();
            CitasView citasView = new CitasView();
            HistorialMedicoView historialView = new HistorialMedicoView();

            int opcion;
            do {
                mostrarMenuPrincipal();
                System.out.print("🎯 Seleccione una opción: ");
                
                opcion = leerOpcion(scanner);

                switch (opcion) {
                    case 1:
                        gestionarDuenos(duenoView, scanner);
                        break;
                    case 2:
                        gestionarMascotas(mascotaView, scanner);
                        break;
                    case 3:
                        gestionarCitas(citasView, scanner);
                        break;
                    case 4:
                        gestionarHistorialMedico(historialView, scanner);
                        break;
                    case 5:
                        gestionarFacturas(facturaView, scanner);
                        break;
                    case 6:
                        gestionarItemsFactura(itemsFacturaView, scanner);
                        break;
                    case 7:
                        gestionarInventario(inventarioView, scanner);
                        break;
                    case 8:
                        mostrarDespedida();
                        break;
                    default:
                        System.out.println("❌ Opción no válida. Intente nuevamente.");
                }
            } while (opcion != 8);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "🚨 Error en el sistema: " + e.getMessage(), e);
            System.out.println("💥 Error crítico en el sistema. Contacte al administrador.");
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n" + "✨".repeat(60));
        System.out.println("🏥           SISTEMA VETERINARIA HAPPY FEET           🏥");
        System.out.println("✨".repeat(60));
        System.out.println("👥  1. Gestión de Dueños");
        System.out.println("🐶  2. Gestión de Mascotas");
        System.out.println("📅  3. Gestión de Citas");
        System.out.println("🏥  4. Gestión de Historial Médico");
        System.out.println("🧾  5. Gestión de Facturas");
        System.out.println("🛒  6. Gestión de Items de Factura");
        System.out.println("📦  7. Gestión de Inventario");
        System.out.println("🚪  8. Salir del Sistema");
        System.out.println("─".repeat(60));
    }

    private static void mostrarDespedida() {
        System.out.println("\n" + "❤️".repeat(60));
        System.out.println("🌟 ¡Gracias por usar Happy Feet Veterinaria! 🌟");
        System.out.println("🐾 Que tus mascotas siempre estén felices y saludables 🐾");
        System.out.println("👋 ¡Hasta pronto!");
        System.out.println("❤️".repeat(60));
    }

    private static int leerOpcion(Scanner scanner) {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); // Limpiar buffer
            System.out.println("⚠️  Por favor, ingrese un número válido.");
            return -1;
        }
    }

    private static void mostrarEncabezado(String titulo) {
        System.out.println("\n" + "⭐".repeat(50));
        System.out.println("🎯 " + titulo.toUpperCase());
        System.out.println("⭐".repeat(50));
    }

    private static void gestionarDuenos(DuenoView duenoView, Scanner scanner) {
        int opcion;
        do {
            mostrarEncabezado("gestión de dueños");
            System.out.println("👤 1. Registrar Nuevo Dueño");
            System.out.println("📋 2. Listar Todos los Dueños");
            System.out.println("🔍 3. Buscar Dueño por ID");
            System.out.println("📊 4. Estadísticas de Dueños");
            System.out.println("🔙 5. Volver al Menú Principal");
            System.out.println("─".repeat(40));
            System.out.print("🎯 Seleccione opción: ");
            
            opcion = leerOpcion(scanner);
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("\n📝 REGISTRANDO NUEVO DUEÑO...");
                    duenoView.registrarDueno();
                    break;
                case 2:
                    System.out.println("\n📋 LISTANDO DUEÑOS...");
                    duenoView.listarDuenos();
                    break;
                case 3:
                    System.out.println("\n🔍 BUSCANDO DUEÑO...");
                    // duenoView.buscarDuenoPorId();
                    break;
                case 4:
                    System.out.println("\n📊 ESTADÍSTICAS DE DUEÑOS...");
                    // duenoView.mostrarEstadisticas();
                    break;
                case 5:
                    System.out.println("↩️  Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 5);
    }

    private static void gestionarMascotas(MascotaView mascotaView, Scanner scanner) {
        int opcion;
        do {
            mostrarEncabezado("gestión de mascotas");
            System.out.println("🐕 1. Registrar Nueva Mascota");
            System.out.println("📋 2. Listar Todas las Mascotas");
            System.out.println("🔍 3. Buscar Mascota por ID");
            System.out.println("❤️  4. Mascotas por Dueño");
            System.out.println("🔙 5. Volver al Menú Principal");
            System.out.println("─".repeat(40));
            System.out.print("🎯 Seleccione opción: ");
            
            opcion = leerOpcion(scanner);
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n🐕 REGISTRANDO NUEVA MASCOTA...");
                    mascotaView.registrarMascota();
                    break;
                case 2:
                    System.out.println("\n📋 LISTANDO MASCOTAS...");
                    mascotaView.listarMascotas();
                    break;
                case 3:
                    System.out.println("\n🔍 BUSCANDO MASCOTA...");
                    // mascotaView.buscarMascotaPorId();
                    break;
                case 4:
                    System.out.println("\n❤️  MASCOTAS POR DUEÑO...");
                    // mascotaView.mostrarMascotasPorDueno();
                    break;
                case 5:
                    System.out.println("↩️  Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 5);
    }

    private static void gestionarCitas(CitasView citasView, Scanner scanner) {
        int opcion;
        do {
            mostrarEncabezado("gestión de citas");
            System.out.println("📅 1. Programar Nueva Cita");
            System.out.println("🔍 2. Consultar Citas por Mascota");
            System.out.println("📋 3. Listar Todas las Citas");
            System.out.println("❌ 4. Cancelar Cita");
            System.out.println("✅ 5. Confirmar Asistencia");
            System.out.println("🔙 6. Volver al Menú Principal");
            System.out.println("─".repeat(40));
            System.out.print("🎯 Seleccione opción: ");
            
            opcion = leerOpcion(scanner);
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n📅 PROGRAMANDO NUEVA CITA...");
                    citasView.programarCita();
                    break;
                case 2:
                    System.out.println("\n🔍 CONSULTANDO CITAS POR MASCOTA...");
                    citasView.consultarCitasMascota();
                    break;
                case 3:
                    System.out.println("\n📋 LISTANDO TODAS LAS CITAS...");
                    citasView.listarTodasLasCitas();
                    break;
                case 4:
                    System.out.println("\n❌ CANCELANDO CITA...");
                    // citasView.cancelarCita();
                    break;
                case 5:
                    System.out.println("\n✅ CONFIRMAR ASISTENCIA...");
                    // citasView.confirmarAsistencia();
                    break;
                case 6:
                    System.out.println("↩️  Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 6);
    }

    private static void gestionarHistorialMedico(HistorialMedicoView historialView, Scanner scanner) {
        int opcion;
        do {
            mostrarEncabezado("gestión de historial médico");
            System.out.println("📝 1. Registrar Nuevo Historial");
            System.out.println("🔍 2. Consultar Historial por Mascota");
            System.out.println("💊 3. Agregar Tratamiento");
            System.out.println("📊 4. Ver Estadísticas de Salud");
            System.out.println("🔙 5. Volver al Menú Principal");
            System.out.println("─".repeat(40));
            System.out.print("🎯 Seleccione opción: ");
            
            opcion = leerOpcion(scanner);
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n📝 REGISTRANDO HISTORIAL MÉDICO...");
                    historialView.registrarHistorial();
                    break;
                case 2:
                    System.out.println("\n🔍 CONSULTANDO HISTORIAL...");
                    historialView.consultarHistorialMascota();
                    break;
                case 3:
                    System.out.println("\n💊 AGREGANDO TRATAMIENTO...");
                    // historialView.agregarTratamiento();
                    break;
                case 4:
                    System.out.println("\n📊 ESTADÍSTICAS DE SALUD...");
                    // historialView.mostrarEstadisticasSalud();
                    break;
                case 5:
                    System.out.println("↩️  Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 5);
    }

    private static void gestionarFacturas(FacturaView facturaView, Scanner scanner) {
        int opcion;
        do {
            mostrarEncabezado("gestión de facturas");
            System.out.println("🧾 1. Crear Nueva Factura");
            System.out.println("📋 2. Listar Todas las Facturas");
            System.out.println("🔍 3. Buscar Factura por ID");
            System.out.println("💰 4. Ver Total de Ventas");
            System.out.println("🔙 5. Volver al Menú Principal");
            System.out.println("─".repeat(40));
            System.out.print("🎯 Seleccione opción: ");
            
            opcion = leerOpcion(scanner);
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n🧾 CREANDO NUEVA FACTURA...");
                    facturaView.eliminarFactura();
                    break;
                case 2:
                    System.out.println("\n📋 LISTANDO FACTURAS...");
                    facturaView.listarFacturas();
                    break;
                case 3:
                    System.out.println("\n🔍 BUSCANDO FACTURA...");
                    // facturaView.buscarFacturaPorId();
                    break;
                case 4:
                    System.out.println("\n💰 TOTAL DE VENTAS...");
                    // facturaView.mostrarTotalVentas();
                    break;
                case 5:
                    System.out.println("↩️  Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 5);
    }

    private static void gestionarItemsFactura(ViewItemsFactura itemsView, Scanner scanner) {
        int opcion;
        do {
            mostrarEncabezado("gestión de items de factura");
            System.out.println("🛒 1. Agregar Item a Factura");
            System.out.println("📋 2. Listar Items por Factura");
            System.out.println("✏️  3. Modificar Item");
            System.out.println("❌ 4. Eliminar Item");
            System.out.println("🔙 5. Volver al Menú Principal");
            System.out.println("─".repeat(40));
            System.out.print("🎯 Seleccione opción: ");
            
            opcion = leerOpcion(scanner);
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n🛒 AGREGANDO ITEM...");
                    itemsView.agregarItem();
                    break;
                case 2:
                    System.out.println("\n📋 LISTANDO ITEMS...");
                    itemsView.listarItems();
                    break;
                case 3:
                    System.out.println("\n✏️  MODIFICANDO ITEM...");
                    // itemsView.modificarItem();
                    break;
                case 4:
                    System.out.println("\n❌ ELIMINANDO ITEM...");
                    // itemsView.eliminarItem();
                    break;
                case 5:
                    System.out.println("↩️  Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 5);
    }

    private static void gestionarInventario(InventarioView inventarioView, Scanner scanner) {
        int opcion;
        do {
            mostrarEncabezado("gestión de inventario");
            System.out.println("📦 1. Agregar Nuevo Producto");
            System.out.println("📋 2. Listar Todos los Productos");
            System.out.println("🔍 3. Buscar Producto por ID");
            System.out.println("🔄 4. Actualizar Stock");
            System.out.println("📊 5. Ver Productos Bajos en Stock");
            System.out.println("🔙 6. Volver al Menú Principal");
            System.out.println("─".repeat(40));
            System.out.print("🎯 Seleccione opción: ");
            
            opcion = leerOpcion(scanner);
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n📦 AGREGANDO NUEVO PRODUCTO...");
                    inventarioView.agregarProducto();
                    break;
                case 2:
                    System.out.println("\n📋 LISTANDO PRODUCTOS...");
                    inventarioView.listarProductos();
                    break;
                case 3:
                    System.out.println("\n🔍 BUSCANDO PRODUCTO...");
                    // inventarioView.buscarProductoPorId();
                    break;
                case 4:
                    System.out.println("\n🔄 ACTUALIZANDO STOCK...");
                    // inventarioView.actualizarStock();
                    break;
                case 5:
                    System.out.println("\n📊 PRODUCTOS BAJOS EN STOCK...");
                    // inventarioView.mostrarProductosBajosStock();
                    break;
                case 6:
                    System.out.println("↩️  Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 6);
    }
}
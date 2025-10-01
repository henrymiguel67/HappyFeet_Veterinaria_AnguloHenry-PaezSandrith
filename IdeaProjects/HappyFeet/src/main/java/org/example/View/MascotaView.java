package org.example.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.example.controller.MascotaController;
import org.example.model.entities.Mascota;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class MascotaView {
    private static final Logger logger = Logger.getLogger(MascotaView.class.getName());
    private final MascotaController mascotaController;
    private final Scanner scanner;

    public MascotaView() {
        this.mascotaController = new MascotaController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenuMascotas() {
        int opcion;
        do {
            System.out.println("\n=== 🐕 GESTIÓN DE MASCOTAS ===");
            System.out.println("1. ➕ Agregar Mascota");
            System.out.println("2. 📋 Listar Todas las Mascotas");
            System.out.println("3. 🔍 Buscar Mascota por ID");
            System.out.println("4. 🏠 Buscar Mascotas por Dueño");
            System.out.println("5. 🔎 Buscar Mascotas por Nombre");
            System.out.println("6. ✏️ Actualizar Mascota");
            System.out.println("7. 🗑️ Eliminar Mascota");
            System.out.println("8. 📊 Contar Mascotas por Dueño");
            System.out.println("0. ↩️ Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1 -> agregarMascota();
                    case 2 -> listarTodasLasMascotas();
                    case 3 -> buscarMascotaPorId();
                    case 4 -> buscarMascotasPorDueno();
                    case 5 -> buscarMascotasPorNombre();
                    case 6 -> actualizarMascota();
                    case 7 -> eliminarMascota();
                    case 8 -> contarMascotasPorDueno();
                    case 0 -> System.out.println("Volviendo al menú principal...");
                    default -> System.out.println("❌ Opción inválida. Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: Ingrese un número válido.");
                scanner.nextLine(); // Limpiar buffer en caso de error
                opcion = -1;
            }
        } while (opcion != 0);
    }

    private void agregarMascota() {
        System.out.println("\n--- ➕ AGREGAR NUEVA MASCOTA ---");
        
        try {
            System.out.print("ID del Dueño: ");
            Integer duenoId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nombre de la Mascota: ");
            String nombre = scanner.nextLine();

            System.out.print("ID de la Raza: ");
            Integer razaId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Fecha de Nacimiento (YYYY-MM-DD): ");
            String fechaStr = scanner.nextLine();
            java.util.Date fechaNacimiento = java.sql.Date.valueOf(fechaStr);

            System.out.print("Sexo (Macho/Hembra): ");
            String sexo = scanner.nextLine();

            System.out.print("URL de Foto (opcional): ");
            String urlFoto = scanner.nextLine();

            // Validar datos antes de enviar al controller
            if (!mascotaController.validarDatosMascota(nombre, duenoId, razaId, sexo)) {
                System.out.println("❌ Datos inválidos. Verifique la información.");
                return;
            }

            boolean exito = mascotaController.agregarMascota(duenoId, nombre, razaId, fechaNacimiento, sexo, urlFoto);
            if (exito) {
                System.out.println("✅ Mascota agregada exitosamente!");
            } else {
                System.out.println("❌ Error al agregar la mascota.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
            logger.log(Level.SEVERE, "Error en agregarMascota: " + e.getMessage(), e);
        }
    }

    private void listarTodasLasMascotas() {
        System.out.println("\n--- 📋 LISTA DE TODAS LAS MASCOTAS ---");
        
        List<Mascota> mascotas = mascotaController.listarTodasLasMascotas();
        
        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
        } else {
            System.out.printf("%-4s %-20s %-10s %-8s %-12s %-15s%n", 
                "ID", "Nombre", "Dueño ID", "Raza ID", "Sexo", "Nacimiento");
            System.out.println("----------------------------------------------------------------");
            
            for (Mascota mascota : mascotas) {
                System.out.printf("%-4d %-20s %-10d %-8d %-8s %-15s%n",
                    mascota.getId(),
                    mascota.getNombre(),
                    mascota.getDuenoId(),
                    mascota.getRazaId(),
                    mascota.getSexo(),
                    mascota.getFechaNacimiento()
                );
            }
        }
    }

    private void buscarMascotaPorId() {
        System.out.println("\n--- 🔍 BUSCAR MASCOTA POR ID ---");
        
        try {
            System.out.print("Ingrese ID de la mascota: ");
            Integer id = scanner.nextInt();
            scanner.nextLine();

            var mascotaOpt = mascotaController.buscarMascotaPorId(id);
            
            if (mascotaOpt.isPresent()) {
                Mascota mascota = mascotaOpt.get();
                System.out.println("✅ Mascota encontrada:");
                System.out.println("   ID: " + mascota.getId());
                System.out.println("   Nombre: " + mascota.getNombre());
                System.out.println("   Dueño ID: " + mascota.getDuenoId());
                System.out.println("   Raza ID: " + mascota.getRazaId());
                System.out.println("   Sexo: " + mascota.getSexo());
                System.out.println("   Fecha Nacimiento: " + mascota.getFechaNacimiento());
                System.out.println("   URL Foto: " + (mascota.getUrlFoto() != null ? mascota.getUrlFoto() : "No disponible"));
            } else {
                System.out.println("❌ No se encontró mascota con ID: " + id);
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void buscarMascotasPorDueno() {
        System.out.println("\n--- 🏠 BUSCAR MASCOTAS POR DUEÑO ---");
        
        try {
            System.out.print("Ingrese ID del dueño: ");
            Integer duenoId = scanner.nextInt();
            scanner.nextLine();

            List<Mascota> mascotas = mascotaController.buscarMascotasPorDueno(duenoId);
            
            if (mascotas.isEmpty()) {
                System.out.println("No se encontraron mascotas para el dueño ID: " + duenoId);
            } else {
                System.out.println("📋 Mascotas del dueño ID " + duenoId + ":");
                for (Mascota mascota : mascotas) {
                    System.out.println("   🐕 " + mascota.getNombre() + " (ID: " + mascota.getId() + 
                                     ", Sexo: " + mascota.getSexo() + ")");
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void buscarMascotasPorNombre() {
        System.out.println("\n--- 🔎 BUSCAR MASCOTAS POR NOMBRE ---");
        
        try {
            System.out.print("Ingrese nombre o parte del nombre: ");
            String nombre = scanner.nextLine();

            List<Mascota> mascotas = mascotaController.buscarMascotasPorNombre(nombre);
            
            if (mascotas.isEmpty()) {
                System.out.println("No se encontraron mascotas con nombre: " + nombre);
            } else {
                System.out.println("📋 Mascotas encontradas:");
                for (Mascota mascota : mascotas) {
                    System.out.println("   🐕 " + mascota.getNombre() + 
                                     " (ID: " + mascota.getId() + 
                                     ", Dueño ID: " + mascota.getDuenoId() + 
                                     ", Sexo: " + mascota.getSexo() + ")");
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void actualizarMascota() {
        System.out.println("\n--- ✏️ ACTUALIZAR MASCOTA ---");
        
        try {
            System.out.print("ID de la mascota a actualizar: ");
            Integer id = scanner.nextInt();
            scanner.nextLine();

            // Primero buscar la mascota para mostrar datos actuales
            var mascotaOpt = mascotaController.buscarMascotaPorId(id);
            if (mascotaOpt.isEmpty()) {
                System.out.println("❌ No se encontró mascota con ID: " + id);
                return;
            }

            Mascota mascotaActual = mascotaOpt.get();
            System.out.println("Datos actuales:");
            System.out.println("   Nombre: " + mascotaActual.getNombre());
            System.out.println("   Dueño ID: " + mascotaActual.getDuenoId());
            System.out.println("   Raza ID: " + mascotaActual.getRazaId());
            System.out.println("   Sexo: " + mascotaActual.getSexo());

            System.out.println("\nIngrese nuevos datos:");

            System.out.print("Nuevo Dueño ID: ");
            Integer nuevoDuenoId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nuevo Nombre: ");
            String nuevoNombre = scanner.nextLine();

            System.out.print("Nueva Raza ID: ");
            Integer nuevaRazaId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nueva Fecha (YYYY-MM-DD): ");
            String nuevaFechaStr = scanner.nextLine();
            java.util.Date nuevaFecha = java.sql.Date.valueOf(nuevaFechaStr);

            System.out.print("Nuevo Sexo (Macho/Hembra): ");
            String nuevoSexo = scanner.nextLine();

            System.out.print("Nueva URL Foto: ");
            String nuevaUrlFoto = scanner.nextLine();

            boolean exito = mascotaController.actualizarMascota(id, nuevoDuenoId, nuevoNombre, 
                                                               nuevaRazaId, nuevaFecha, nuevoSexo, nuevaUrlFoto);
            if (exito) {
                System.out.println("✅ Mascota actualizada exitosamente!");
            } else {
                System.out.println("❌ Error al actualizar la mascota.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void eliminarMascota() {
        System.out.println("\n--- 🗑️ ELIMINAR MASCOTA ---");
        
        try {
            System.out.print("ID de la mascota a eliminar: ");
            Integer id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("¿Está seguro de eliminar la mascota? (s/n): ");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                boolean exito = mascotaController.eliminarMascota(id);
                if (exito) {
                    System.out.println("✅ Mascota eliminada exitosamente!");
                } else {
                    System.out.println("❌ Error al eliminar la mascota.");
                }
            } else {
                System.out.println("❌ Eliminación cancelada.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void contarMascotasPorDueno() {
        System.out.println("\n--- 📊 CONTAR MASCOTAS POR DUEÑO ---");
        
        try {
            System.out.print("Ingrese ID del dueño: ");
            Integer duenoId = scanner.nextInt();
            scanner.nextLine();

            int cantidad = mascotaController.contarMascotasPorDueno(duenoId);
            System.out.println("📊 El dueño ID " + duenoId + " tiene " + cantidad + " mascota(s)");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

public void registrarMascota() throws java.text.ParseException {
        try {
            System.out.println("=== Registrar nueva mascota ===");
            
            System.out.print("ID del dueño: ");
            Integer duenoId = Integer.valueOf(scanner.nextLine());
            
            System.out.print("Nombre de la mascota: ");
            String nombre = scanner.nextLine();
            
            System.out.print("ID de raza: ");
            Integer razaId = Integer.valueOf(scanner.nextLine());
            
            System.out.print("Fecha de nacimiento (dd/MM/yyyy): ");
            String fechaStr = scanner.nextLine();
            Date fechaNacimiento;
            fechaNacimiento = null;
            fechaNacimiento = new SimpleDateFormat("dd/MM/yyyy").parse(fechaStr);
            
            System.out.print("Sexo (Macho/Hembra): ");
            String sexo = scanner.nextLine();
            
            System.out.print("URL de la foto (opcional): ");
            String urlFoto = scanner.nextLine();

            // Validar datos antes de llamar al controller
            if (!mascotaController.validarDatosMascota(nombre, duenoId, razaId, sexo)) {
                System.out.println("Datos inválidos. Verifica los datos ingresados.");
                return;
            }
            
            boolean exito = mascotaController.agregarMascota(duenoId, nombre, razaId, fechaNacimiento, sexo, urlFoto);
            if (exito) {
                System.out.println("Mascota registrada con éxito!");
            } else {
                System.out.println("Error al registrar la mascota.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID de dueño o raza debe ser un número válido.");
        }
    }

    public void listarMascotas() {
        System.out.println("=== Listado de mascotas ===");
        List<Mascota> mascotas = mascotaController.listarTodasLasMascotas();
        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
        } else {
            for (Mascota m : mascotas) {
                System.out.printf("ID: %d, Nombre: %s, Dueño ID: %d, Raza ID: %d, Sexo: %s, Fecha Nac: %s, Foto URL: %s\n",
                        m.getId(), m.getNombre(), m.getDuenoId(), m.getRazaId(), m.getSexo(),
                        new SimpleDateFormat("dd/MM/yyyy").format(m.getFechaNacimiento()), m.getUrlFoto());
            }
        }
    }
}
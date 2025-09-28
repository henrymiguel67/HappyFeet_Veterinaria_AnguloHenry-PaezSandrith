package org.example;

import org.example.model.entities.Dueno;
import org.example.model.entities.DuenoDAO;
import org.example.model.entities.Repository.DuenoRepositoryException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        // Instanciamos el DAO
        DuenoDAO duenoDAO = new DuenoDAO();

        // Creamos algunos dueños de ejemplo
        Dueno dueno1 = new Dueno(0, "Carlos Pérez", "3101234567", "carlos@email.com", "123456789");
        Dueno dueno2 = new Dueno(0, "Ana Gómez", "3209876543", "ana@email.com", "987654321");

        try {
            // Agregamos los dueños a la base de datos
            duenoDAO.agregarDueno(dueno1);
            duenoDAO.agregarDueno(dueno2);
            logger.info("✅ Dueños agregados correctamente.");

            // Listamos todos los dueños de la base de datos
            List<Dueno> todosDuenos = duenoDAO.listarTodos();
            logger.info("📋 Lista de dueños en la base de datos:");
            for (Dueno d : todosDuenos) {
                logger.info(d.toString());
            }

        } catch (DuenoRepositoryException e) {
            logger.log(Level.SEVERE, "❌ Error en la operación: " + e.getMessage(), e);
        } finally {
            // Cerramos la conexión al terminar
            ConnectionSingleton.getInstance().closeConnection();
        }
    }
}

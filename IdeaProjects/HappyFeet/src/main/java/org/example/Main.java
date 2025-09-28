package org.example;

import org.example.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    
    public static void main(String[] args) {
        logger.info("🚀 Iniciando Sistema Happy Feet Veterinaria...");
        
        // Probar conexión a la base de datos
        probarConexion();
        
        // Aquí iniciarás tu menú principal después
        logger.info("✅ Sistema listo para usar");
    }
    
    private static void probarConexion() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null && !connection.isClosed()) {
                logger.info("✅ Conexión a BD establecida correctamente");
                // Aquí puedes agregar más lógica de tu aplicación
                
                // Cerrar conexión cuando termines
                DatabaseConnection.closeConnection();
            }
        } catch (SQLException e) {
             logger.log(Level.SEVERE, () -> "❌ Error de conexión: " + e.getMessage(), e);
        }
    }
}
package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ConnectionSingleton: Clase que gestiona una única conexión a la base de datos
 * para todo el proyecto.
 */
public class ConnectionSingleton {

    private static ConnectionSingleton instance;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/HappyFeet";
    private static final String USER = "campus2023";
    private static final String PASSWORD = "campus2023";

    private static final Logger logger = Logger.getLogger(ConnectionSingleton.class.getName());

    private ConnectionSingleton() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("✅ Conexión establecida con éxito a la base de datos HappyFeet");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "❌ Error al establecer la conexión.", e);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "❌ No se encontró el driver de MySQL.", e);
        }
    }

    public static synchronized ConnectionSingleton getInstance() {
        if (instance == null) {
            instance = new ConnectionSingleton();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                instance = new ConnectionSingleton(); // reabrir conexión si estaba cerrada
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "❌ Error verificando la conexión.", e);
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("🔒 Conexión cerrada correctamente.");
            } catch (SQLException e) {
                logger.log(Level.WARNING, "⚠️ Error al cerrar la conexión.", e);
            }
        }
    }
}

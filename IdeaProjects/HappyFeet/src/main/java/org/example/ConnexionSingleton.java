package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings({"all"})  
public class ConnectionSingleton {

    private static ConnectionSingleton instance;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/happyfeet_veterinaria";
    private static final String USER = "campus2023";
    @SuppressWarnings("all")  
    private static final String PASSWORD = "campus2023";

    private static final Logger logger = Logger.getLogger(ConnectionSingleton.class.getName());

    private ConnectionSingleton() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("Conexion establecida con exito!");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al establecer la conexion.", e);
        }
    }

    public static ConnectionSingleton getInstance() {
        if (instance == null) {
            instance = new ConnectionSingleton();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
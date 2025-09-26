package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionSingleton {
    private static ConnexionSingleton instance;
    private static Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "campus2023";
    private static final String PASS = "campus2023";

    private ConnexionSingleton() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println(" Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.out.println(" Error al conectar: " + e.getMessage());
        }
    }

    public static ConnexionSingleton getInstance() {
        if (instance == null) {
            instance = new ConnexionSingleton();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println(" Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println(" Error al cerrar la conexión: " + e.getMessage());
        }
    }
}


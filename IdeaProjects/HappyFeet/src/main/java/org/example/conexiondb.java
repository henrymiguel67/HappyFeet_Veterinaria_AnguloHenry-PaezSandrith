package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexiondb {

     static void main(String[] args) {
    String host = "jdbc:mysql://localhost:3306/";
    String user = "campus2023";
    String pass = "campus2023";
    String bd = "HappyFeet";

    String strConn = host + bd;
    try (Connection connection = DriverManager.getConnection(strConn, user, pass)){
        System.out.println("Conexion exitosa");
    } catch (SQLException e){
        System.out.println("Error al conectarse a la base de datos. \n" + e.getMessage());
    }
}
}
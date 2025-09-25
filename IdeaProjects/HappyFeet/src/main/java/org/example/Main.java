package org.example;
import java.sql.Connection;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ConexionSingleton conexion = ConexionSingleton.getInstance();

        Connection conn = conexion.getConnection();
        if (conn != null) {
            System.out.println(" Conectado desde Main.");
        }
        ConexionSingleton.closeConnection();
    }
}
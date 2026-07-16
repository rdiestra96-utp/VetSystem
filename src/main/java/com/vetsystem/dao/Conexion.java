
package com.vetsystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL      = "jdbc:mysql://localhost:3306/vetsystem";
    private static final String USUARIO  = "root";
    private static final String PASSWORD = "Sistemas10";
    
    public static Connection getConexion() {
        try {
            return DriverManager.getConnection(URL, USUARIO, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}

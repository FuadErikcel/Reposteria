package com.mycompany.reposteria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    private final String HOST = "localhost";
    private final String PUERTO = "5432";
    private final String DB = "Reposteria";
    private final String USER = "postgres";
    private final String PASSWORD = "0000";
    
    public Connection getConexion(){
        Connection conexion = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            String url ="jdbc:postgresql://"+HOST+":"+PUERTO+"/"+DB;
            conexion = DriverManager.getConnection(url, USER, PASSWORD);
            JOptionPane.showMessageDialog(null, "conexion exitosa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return conexion;
    }
    
    public void cerrarConexion(Connection conexion) {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    PreparedStatement prepareStatement(String consultaSelect) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

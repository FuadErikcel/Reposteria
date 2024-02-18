/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.reposteria;

/**
 *
 * @author Fuad Erikcel
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Reposteria {

    public static void main(String[] args) throws SQLException {
     Conexion conexionObjeto = new Conexion();
     Connection conexion = conexionObjeto.getConexion();
     
     if (conexion != null) {
            try {
                String consultaSelect = "SELECT idproveedor, nombre FROM proveedores";
                try (PreparedStatement statement = conexion.prepareStatement(consultaSelect);
                     ResultSet resultSet = statement.executeQuery()) {

                    while (resultSet.next()) {
                        String id = resultSet.getString("idproveedor");
                        String nombre = resultSet.getString("nombre");
         
                        System.out.println("ID: " + id + ", Nombre: " + nombre );
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conexionObjeto.cerrarConexion(conexion);
            }
        }
    }
}
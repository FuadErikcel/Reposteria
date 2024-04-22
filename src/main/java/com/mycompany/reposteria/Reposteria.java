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
            Login login = new Login();
            login.setVisible(true);
    }
}
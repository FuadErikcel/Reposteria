/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.reposteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Bryan Aguiluz
 */
public class Inventario extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public Inventario() {
        initComponents();
        mostrarTableInventario();
    }
     Conexion conexionObjeto = new Conexion();
     Connection conexion = conexionObjeto.getConexion();
     
     
    public void searchDates() throws SQLException{
    String identidad = IdIngredienteText.getText();
    
     try {  
            String consultaSQL = "SELECT precioingrediente, cantdisponible, fechavencimiento, nombreingrediente " +
                                 "FROM inventario WHERE idingrediente = ?";
            
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);

            statement.setString(1, identidad);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {              
                PrecioIngredienteText.setText(resultSet.getString("precioingrediente"));
                CantDisponibleText.setText(resultSet.getString("cantdisponible"));
                FechaVencimientoText.setDate(resultSet.getDate("fechavencimiento"));
                NombreIngredienteText.setText(resultSet.getString("nombreingrediente"));
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron resultados para el ID proporcionado.");
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
        }
    }
    
    public void mostrarTableInventario(){
      try {        
            String consultaSQL = "SELECT * " +
                                 "FROM inventario ";

            PreparedStatement statement = conexion.prepareStatement(consultaSQL);

            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("ID");
            modeloTabla.addColumn("Precio");
            modeloTabla.addColumn("Cantidad");
            modeloTabla.addColumn("Caducidad");
            modeloTabla.addColumn("Nombre");
            
            while (resultSet.next()) {
                Object[] fila = {
                        resultSet.getString("idingrediente"),
                        resultSet.getString("precioingrediente"),
                        resultSet.getString("cantdisponible"),
                        resultSet.getString("fechavencimiento"),
                        resultSet.getString("nombreingrediente"),
                };
                modeloTabla.addRow(fila);
            }

            tablaInventario.setModel(modeloTabla);

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
        }
    }  
     
    public void insertDates(){
        String identidad = IdIngredienteText.getText();
        int precio = Integer.parseInt(PrecioIngredienteText.getText());
        String nombreIngrediente = NombreIngredienteText.getText();
        int cantidad = Integer.parseInt(CantDisponibleText.getText());
        java.util.Date fecha = FechaVencimientoText.getDate();
        java.sql.Date fechaVencimiento = new java.sql.Date(fecha.getTime());
        
        //Connection conexion = conexionObjeto.getConexion();
        
         try {
            String consultaInsert = "INSERT INTO inventario(idingrediente, precioingrediente, cantdisponible, fechavencimiento, nombreingrediente) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement statement = conexion.prepareStatement(consultaInsert);
            statement.setString(1, identidad);
            statement.setInt(2, precio);
            statement.setInt(3, cantidad);
            statement.setString(5, nombreIngrediente);
            statement.setDate(4, (fechaVencimiento));

            
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(this, "Datos insertados correctamente");
                IdIngredienteText.setText("");
                PrecioIngredienteText.setText("");
                NombreIngredienteText.setText("");
                CantDisponibleText.setText("");
                //FechaVencimientoText.setText("");
                mostrarTableInventario();
            } else {
                JOptionPane.showMessageDialog(this, "Error al insertar datos");
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar datos: " + ex.getMessage());
        }
        
    }
    
    public void Delete(){
        String identidad = IdIngredienteText.getText();
        
        if(identidad.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor ingrese el ID del ingrediente a eliminar");
        return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este ingrediente?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if(confirmacion != JOptionPane.YES_OPTION) {
        return; 
        }
        
        try {
        String consultaDelete = "DELETE FROM inventario WHERE idingrediente = ?";
        PreparedStatement statement = conexion.prepareStatement(consultaDelete);
        statement.setString(1, identidad);
        
        int filasEliminadas = statement.executeUpdate();

        if (filasEliminadas > 0) {
            JOptionPane.showMessageDialog(this, "Ingrediente eliminado correctamente");
            IdIngredienteText.setText("");
            PrecioIngredienteText.setText("");
            NombreIngredienteText.setText("");
            CantDisponibleText.setText("");
            //FechaVencimientoText.setText("");
            mostrarTableInventario();
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ningún ingrediente con el ID especificado");
        }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar ingrediente: " + ex.getMessage());
        }
    }
    
    public void Modificar(){
        String identidad = IdIngredienteText.getText();
        int precio = Integer.parseInt(PrecioIngredienteText.getText());
        String nombre = NombreIngredienteText.getText();
        int cantidad = Integer.parseInt(CantDisponibleText.getText());
        java.util.Date fecha = FechaVencimientoText.getDate();
        java.sql.Date fechaVencimiento = new java.sql.Date(fecha.getTime());
        
        if(identidad.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el ID del inventaio a actualizar");
            return;
        }
        
        try {
            String consultaUpdate = "UPDATE inventario SET precioingrediente = ?, nombreingrediente = ?, cantdisponible = ?, fechavencimiento = ? WHERE idingrediente = ?";
            PreparedStatement statement = conexion.prepareStatement(consultaUpdate);
            statement.setInt(1, precio);
            statement.setString(2, nombre);
            statement.setInt(3, cantidad);
            statement.setDate(4, fechaVencimiento);
            statement.setString(5, identidad);

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(this, "Inventario actualizado correctamente");
                IdIngredienteText.setText("");
                PrecioIngredienteText.setText("");
                NombreIngredienteText.setText("");
                CantDisponibleText.setText("");
                //FechaVencimientoText.setText("");
                mostrarTableInventario();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún ingrediente con el ID especificado");
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar inventario: " + ex.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        IdIngredienteLabel = new javax.swing.JLabel();
        IdIngredienteText = new javax.swing.JTextField();
        PrecioIngredienteLabel = new javax.swing.JLabel();
        PrecioIngredienteText = new javax.swing.JTextField();
        NombreIngredienteLabel = new javax.swing.JLabel();
        NombreIngredienteText = new javax.swing.JTextField();
        CantDisponibleLabel = new javax.swing.JLabel();
        CantDisponibleText = new javax.swing.JTextField();
        FechaVencimientoLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        FechaVencimientoText = new com.toedter.calendar.JDateChooser();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Inventario");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 21, -1, 34));

        IdIngredienteLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        IdIngredienteLabel.setText("Identidad");
        jPanel1.add(IdIngredienteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 91, 103, -1));

        IdIngredienteText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                IdIngredienteTextFocusLost(evt);
            }
        });
        jPanel1.add(IdIngredienteText, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 91, 120, -1));

        PrecioIngredienteLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PrecioIngredienteLabel.setText("Precio :");
        jPanel1.add(PrecioIngredienteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 132, -1, -1));
        jPanel1.add(PrecioIngredienteText, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 132, 120, -1));

        NombreIngredienteLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NombreIngredienteLabel.setText("Nombre: ");
        jPanel1.add(NombreIngredienteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 173, -1, -1));
        jPanel1.add(NombreIngredienteText, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 173, 120, -1));

        CantDisponibleLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CantDisponibleLabel.setText("Cantidad:");
        jPanel1.add(CantDisponibleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 214, 118, -1));
        jPanel1.add(CantDisponibleText, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 214, 120, -1));

        FechaVencimientoLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        FechaVencimientoLabel.setText("Fecha Vencimiento:");
        jPanel1.add(FechaVencimientoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 255, -1, -1));

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(481, 91, 120, -1));

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(481, 132, 120, -1));

        jButton3.setText("Modificar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(481, 173, 120, -1));

        jButton4.setText("Eliminar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(481, 214, 120, -1));

        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaInventario);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 610, 310));
        jPanel1.add(FechaVencimientoText, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 120, -1));

        jButton5.setText("Generar Informe");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insertDates();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Modificar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            searchDates();
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Delete();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void IdIngredienteTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_IdIngredienteTextFocusLost
        try {
            searchDates();
        } catch (SQLException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }//GEN-LAST:event_IdIngredienteTextFocusLost

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
//        String jrxmlFilePath  = "C:\\Users\\Fuad Erikcel\\Documents\\NetBeansProjects\\Reposteria\\Galletas.jrxml";
//        String jasperFilePath = "C:\\Users\\Fuad Erikcel\\Documents\\NetBeansProjects\\Reposteria\\Galletas.jasper";

        String jrxmlFilePath  = "./Galletas.jrxml";
        String jasperFilePath = "./Galletas.jasper";
        
            try {
                JasperCompileManager.compileReportToFile(jrxmlFilePath);
            } catch (JRException ex) {
                Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            }
      
            try {
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFilePath, null, conexion);
                 JasperViewer.viewReport(jasperPrint, false);

                  JRPdfExporter exporter = new JRPdfExporter();
                  exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                  exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("C:\\Users\\Fuad Erikcel\\Escritorio"));

                  SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
                  SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
                  exporter.setConfiguration(reportConfig);
                  exporter.setConfiguration(exportConfig);


            } catch (JRException ex) {
                Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CantDisponibleLabel;
    private javax.swing.JTextField CantDisponibleText;
    private javax.swing.JLabel FechaVencimientoLabel;
    private com.toedter.calendar.JDateChooser FechaVencimientoText;
    private javax.swing.JLabel IdIngredienteLabel;
    private javax.swing.JTextField IdIngredienteText;
    private javax.swing.JLabel NombreIngredienteLabel;
    private javax.swing.JTextField NombreIngredienteText;
    private javax.swing.JLabel PrecioIngredienteLabel;
    private javax.swing.JTextField PrecioIngredienteText;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaInventario;
    // End of variables declaration//GEN-END:variables
}

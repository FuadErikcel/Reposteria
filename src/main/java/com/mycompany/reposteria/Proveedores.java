/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.reposteria;

/**
 *
 * @author Fuad Erikcel
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;




public class Proveedores extends javax.swing.JFrame {

    /**
     * Creates new form Proveedores
     */
    public Proveedores() {
        initComponents();
        mostrarTableProveedores();
    }
    
     Conexion conexionObjeto = new Conexion();
     Connection conexion = conexionObjeto.getConexion();
     
//public void report(){
//JasperReport jasperReport = JRLoader.loadJasperReport("ruta/al/archivo/informe.jasper");    
//}   


public void searchDates() throws SQLException{
    String identidad = txtIdentidad.getText();
    
     try {  
            String consultaSQL = "SELECT nombre, contacto, detalles " +
                                 "FROM proveedores WHERE idproveedor = ? ";
            
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);

            statement.setString(1, identidad  );
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {              
                txtNombre.setText(resultSet.getString("nombre"));
                txtContacto.setText(resultSet.getString("contacto"));
                txtDetalles.setText(resultSet.getString("detalles"));
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron resultados para el ID proporcionado.");
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
        }
}
            

 public void mostrarTableProveedores(){
      try {        
            String consultaSQL = "SELECT * " +
                                 "FROM proveedores " ;

            PreparedStatement statement = conexion.prepareStatement(consultaSQL);

            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("ID");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Contacto");
            modeloTabla.addColumn("Detalles");

            while (resultSet.next()) {
                Object[] fila = {
                        resultSet.getString("idproveedor"),
                        resultSet.getString("nombre"),
                        resultSet.getString("contacto"),
                        resultSet.getString("detalles"),
                };
                modeloTabla.addRow(fila);
            }

            tablaProveedores.setModel(modeloTabla);

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
        }
    }  


    public void insertDates(){
        String identidad = txtIdentidad.getText();
        String nombre = txtNombre.getText();
        String contacto = txtContacto.getText();
        String detalles = txtDetalles.getText();
        
        //Connection conexion = conexionObjeto.getConexion();
        
         try {
            String consultaInsert = "INSERT INTO proveedores(idproveedor,nombre, contacto, detalles) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consultaInsert);
            statement.setString(1, identidad);
            statement.setString(2, nombre);
            statement.setString(3, contacto);
            statement.setString(4, detalles);
            
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(this, "Datos insertados correctamente");
                txtIdentidad.setText("");
                txtNombre.setText("");
                txtContacto.setText("");
                txtDetalles.setText("");
                mostrarTableProveedores();
            } else {
                JOptionPane.showMessageDialog(this, "Error al insertar datos");
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar datos: " + ex.getMessage());
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbIdentidad = new javax.swing.JLabel();
        lbNombre = new javax.swing.JLabel();
        lbContacto = new javax.swing.JLabel();
        lbDetalles = new javax.swing.JLabel();
        txtIdentidad = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtContacto = new javax.swing.JTextField();
        txtDetalles = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProveedores = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbIdentidad.setText("Identidad:");

        lbNombre.setText("Nombre:");

        lbContacto.setText("Contacto:");

        lbDetalles.setText("Detalles");

        txtIdentidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentidadActionPerformed(evt);
            }
        });

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Identidad", "Nombre", "Contacto", "Detalles"
            }
        ));
        jScrollPane2.setViewportView(tablaProveedores);

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Modificar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Generar Reporte");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Menu Principal");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbIdentidad)
                    .addComponent(lbNombre)
                    .addComponent(lbContacto)
                    .addComponent(lbDetalles))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIdentidad, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                    .addComponent(txtNombre)
                    .addComponent(txtContacto)
                    .addComponent(txtDetalles))
                .addGap(153, 153, 153)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbIdentidad)
                    .addComponent(txtIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbContacto)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDetalles)
                    .addComponent(txtDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdentidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentidadActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        insertDates();       
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            searchDates();
        } catch (SQLException ex) {
            Logger.getLogger(Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
    String identidad = txtIdentidad.getText();
    
    if(identidad.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor ingrese el ID del proveedor a eliminar");
        return;
    }
    
    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este proveedor?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
    
    if(confirmacion != JOptionPane.YES_OPTION) {
        return; 
    }
    
    try {
        String consultaDelete = "DELETE FROM proveedores WHERE idproveedor = ?";
        PreparedStatement statement = conexion.prepareStatement(consultaDelete);
        statement.setString(1, identidad);
        
        int filasEliminadas = statement.executeUpdate();

        if (filasEliminadas > 0) {
            JOptionPane.showMessageDialog(this, "Proveedor eliminado correctamente");
            txtIdentidad.setText("");
            txtNombre.setText("");
            txtContacto.setText("");
            txtDetalles.setText("");
            mostrarTableProveedores();
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ningún proveedor con el ID especificado");
        }

        statement.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al eliminar proveedor: " + ex.getMessage());
         }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String identidad = txtIdentidad.getText();
        String nombre = txtNombre.getText();
        String contacto = txtContacto.getText();
        String detalles = txtDetalles.getText();

        // Verificar si el campo de identidad está vacío
        if(identidad.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el ID del proveedor a actualizar");
            return;
        }

        try {
            String consultaUpdate = "UPDATE proveedores SET nombre = ?, contacto = ?, detalles = ? WHERE idproveedor = ?";
            PreparedStatement statement = conexion.prepareStatement(consultaUpdate);
            statement.setString(1, nombre);
            statement.setString(2, contacto);
            statement.setString(3, detalles);
            statement.setString(4, identidad);

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(this, "Proveedor actualizado correctamente");
                txtIdentidad.setText("");
                txtNombre.setText("");
                txtContacto.setText("");
                txtDetalles.setText("");
                mostrarTableProveedores();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún proveedor con el ID especificado");
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar proveedor: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
             String jrxmlFilePath  = "./Proveedores.jrxml";
             String jasperFilePath = "./Proveedores.jasper";
        
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
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
              MenuPrincipal mp = new MenuPrincipal();
        mp.setVisible(true);
        this.dispose();
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
            java.util.logging.Logger.getLogger(Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Proveedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbContacto;
    private javax.swing.JLabel lbDetalles;
    private javax.swing.JLabel lbIdentidad;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JTable tablaProveedores;
    private javax.swing.JTextField txtContacto;
    private javax.swing.JTextField txtDetalles;
    private javax.swing.JTextField txtIdentidad;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}

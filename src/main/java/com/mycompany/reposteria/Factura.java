/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.reposteria;

import com.mycompany.reposteria.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bryan Aguiluz
 */
public class Factura extends javax.swing.JFrame {
DefaultTableModel model;
    /**
     * Creates new form Factura
     */
    public Factura() {
        initComponents();
        
        model = new DefaultTableModel();
        model.addColumn("ID Producto");
        model.addColumn("Nombre");
        model.addColumn("Precio");
        model.addColumn("Cantidad");  
        tableFac.setModel(model);
    }

    
    Conexion conexionObjeto = new Conexion();
    Connection conexion = conexionObjeto.getConexion();
    
    
    public void searchClientes() throws SQLException{
    String identidad = IdPersonaText.getText();
    
     try {  
            String consultaSQL = "SELECT nombre, contacto " +
                                 "FROM persona WHERE idpersona = ?";
            
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);

            statement.setString(1, identidad);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {              
                NombreText.setText(resultSet.getString("nombre"));
                ContactoText.setText(resultSet.getString("contacto"));
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron resultados para el ID proporcionado.");
                IdPersonaText.setText("");
                NombreText.setText("");
                ContactoText.setText("");
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
        }
    }
    
    public void searchProducto() throws SQLException{
    String identidad = IdProductoText.getText();
    
        try {  
            String consultaSQL = "SELECT precioproducto " +
                                 "FROM producto WHERE idproducto = ?";
            
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);

            statement.setString(1, identidad);
            
            ResultSet resultSet = statement.executeQuery();
            
            String tipo = identidad.substring(0, 2);
            String nombre = null;
            switch (tipo) {
                case "GA":
                    nombre = "Galleta";
                    break;
                case "PA":
                    nombre = "Pan";
                    break;
                case "PN":
                    nombre = "Pastel Normal";
                    break;
                case "PP":
                    nombre = "Pastel Personalizado";
                    break;
            }
            if (resultSet.next()) {              
                PrecioText.setText(resultSet.getString("precioproducto"));
                NombreProductoText.setText(nombre);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron resultados para el ID proporcionado.");
                IdProductoText.setText("");
                PrecioText.setText("");
                NombreProductoText.setText("");
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
        }
    }
    
    
    public void insertGuardar(){
        String identidadProducto = IdProductoText.getText();
        String nombre = NombreProductoText.getText();
        int precio = Integer.parseInt(PrecioText.getText());
        int cantidad = Integer.parseInt(CantidadText.getText());
        model.addRow(new Object[]{identidadProducto, nombre, precio, cantidad});       
        IdProductoText.setText("");
        NombreProductoText.setText("");
        PrecioText.setText("");
        CantidadText.setText("");
        
        double subTotal = 0;
        double total = 0;
        double precio1 = 0;
        double cantidad1 = 0;

        for(int i=0; i<tableFac.getRowCount(); i++){
            
            precio1 = Double.parseDouble(tableFac.getValueAt(i,2).toString());
            cantidad1 =   Double.parseDouble(tableFac.getValueAt(i,3).toString());
            subTotal = precio1 * cantidad1;
            total = total + subTotal;
        }

        String x = Double.toString(subTotal);
        String y = Double.toString(total);

        SubTotalText.setText(x);
        TotalText.setText(y);
        
    }
    
    public void insertFactura(){
        String identidadFactura = IdFacturaText.getText();
        String fecha = FechaVentaText.getText();
        
        try {
            String consultaInsert = "INSERT INTO factura(idfactura, fechaventa) VALUES (?, ?);";
            PreparedStatement statement = conexion.prepareStatement(consultaInsert);
            statement.setString(1, identidadFactura);
            statement.setString(2, fecha);
            
            int filasInsertadas = statement.executeUpdate();
            
            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(this, "Datos insertados correctamente");
                //mostrarTableInventario();
                
            } else {
                JOptionPane.showMessageDialog(this, "Error al insertar datos");
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar datos: " + ex.getMessage());  
        }
    }
    
    public void insertFacC(){
        String identidadFactura = IdFacturaText.getText();
        String idpersona = IdPersonaText.getText();
        
        try {
            String consultaInsert = "INSERT INTO facturacliente(idfacturac, idclientef) VALUES (?, ?);";
            PreparedStatement statement = conexion.prepareStatement(consultaInsert);
            statement.setString(1, identidadFactura);
            statement.setString(2, idpersona);
            
            int filasInsertadas = statement.executeUpdate();
            
            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(this, "Datos insertados correctamente");
                //mostrarTableInventario();
                
            } else {
                JOptionPane.showMessageDialog(this, "Error al insertar datos");
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar datos: " + ex.getMessage());  
        }
        
    }
    
    public void insertFacP() throws SQLException{
        String identidadFactura = IdFacturaText.getText();
        
        try {
            String consultaInsert = "INSERT INTO facturaproducto(idfacturap, idproductof, cantidad) VALUES (?, ?, ?);";
            PreparedStatement statement = conexion.prepareStatement(consultaInsert);
            
            for(int i=0; i<tableFac.getRowCount(); i++){
            String cantidad = tableFac.getValueAt(i, 3).toString();
            String idproducto = tableFac.getValueAt(i, 0).toString();

            int cantidad1 = Integer.parseInt(cantidad);
            
            statement.setString(1, identidadFactura);
            statement.setString(2, idproducto);
            statement.setInt(3, cantidad1);
            statement.executeUpdate();
            }
            
        }catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar datos: " + ex.getMessage());
         }
    }
        
    public void limpiar(){
        IdFacturaText.setText("");
        FechaVentaText.setText("");
        IdPersonaText.setText("");
        NombreText.setText("");
        ContactoText.setText("");
        IdProductoText.setText("");
        NombreProductoText.setText("");
        PrecioText.setText("");
        CantidadText.setText("");

        int fila = model.getRowCount();
        for(int i = fila-1; i>=0; i--){
           model.removeRow(i);
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
        IdVentaLabel = new javax.swing.JLabel();
        PrecioLabel = new javax.swing.JLabel();
        CantidadLabel = new javax.swing.JLabel();
        IdFacturaText = new javax.swing.JTextField();
        CantidadText = new javax.swing.JTextField();
        PrecioText = new javax.swing.JTextField();
        IdClienteLabel = new javax.swing.JLabel();
        NombreLabel = new javax.swing.JLabel();
        ContactoLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        IdPersonaText = new javax.swing.JTextField();
        NombreText = new javax.swing.JTextField();
        ContactoText = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        FechaVentaLabel = new javax.swing.JLabel();
        FechaVentaText = new javax.swing.JTextField();
        SubTotalLabel = new javax.swing.JLabel();
        SubTotalText = new javax.swing.JTextField();
        IdProductoLabel = new javax.swing.JLabel();
        IdProductoText = new javax.swing.JTextField();
        NombreProductoLabel = new javax.swing.JLabel();
        NombreProductoText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableFac = new javax.swing.JTable();
        TotalLabel = new javax.swing.JLabel();
        TotalText = new javax.swing.JTextField();
        CbxFactura = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Factura");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, -1));

        IdVentaLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        IdVentaLabel.setText("ID Factura:");
        jPanel1.add(IdVentaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        PrecioLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        PrecioLabel.setText("Precio C/U:");
        jPanel1.add(PrecioLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, -1, -1));

        CantidadLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CantidadLabel.setText("Cantidad:");
        jPanel1.add(CantidadLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 62, -1));

        IdFacturaText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdFacturaTextActionPerformed(evt);
            }
        });
        jPanel1.add(IdFacturaText, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 100, -1));

        CantidadText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CantidadTextActionPerformed(evt);
            }
        });
        CantidadText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CantidadTextKeyTyped(evt);
            }
        });
        jPanel1.add(CantidadText, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 130, -1));
        jPanel1.add(PrecioText, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 250, 60, -1));

        IdClienteLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        IdClienteLabel.setText("ID Cliente:");
        jPanel1.add(IdClienteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        NombreLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NombreLabel.setText("Nombre:");
        jPanel1.add(NombreLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 64, -1));

        ContactoLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ContactoLabel.setText("Celular:");
        jPanel1.add(ContactoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, -1, -1));

        jButton1.setText("Agregar al Carrito");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, -1, -1));

        jButton2.setText("Eliminar Producto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, 125, -1));

        IdPersonaText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                IdPersonaTextFocusLost(evt);
            }
        });
        jPanel1.add(IdPersonaText, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 130, -1));
        jPanel1.add(NombreText, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 190, -1));
        jPanel1.add(ContactoText, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 190, 80, -1));

        jButton3.setText("Guardar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, -1, -1));

        jButton4.setText("Buscar");
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, -1, -1));

        jButton5.setText("Limpiar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, -1, -1));

        FechaVentaLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        FechaVentaLabel.setText(" Fecha:");
        jPanel1.add(FechaVentaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 43, -1));
        jPanel1.add(FechaVentaText, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 100, -1));

        SubTotalLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SubTotalLabel.setText("SubTotal:");
        jPanel1.add(SubTotalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 360, 70, -1));

        SubTotalText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubTotalTextActionPerformed(evt);
            }
        });
        jPanel1.add(SubTotalText, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 360, 80, -1));

        IdProductoLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        IdProductoLabel.setText("ID Producto:");
        jPanel1.add(IdProductoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        IdProductoText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                IdProductoTextFocusLost(evt);
            }
        });
        IdProductoText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdProductoTextActionPerformed(evt);
            }
        });
        jPanel1.add(IdProductoText, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 110, -1));

        NombreProductoLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NombreProductoLabel.setText("Nombre Producto:");
        jPanel1.add(NombreProductoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, -1, -1));

        NombreProductoText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreProductoTextActionPerformed(evt);
            }
        });
        jPanel1.add(NombreProductoText, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, 150, -1));

        tableFac.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableFac);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 810, 360));

        TotalLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TotalLabel.setText("Total:");
        jPanel1.add(TotalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 390, 40, -1));
        jPanel1.add(TotalText, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 390, 80, -1));

        CbxFactura.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Con Nombre", "Sin Nombre" }));
        CbxFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxFacturaActionPerformed(evt);
            }
        });
        jPanel1.add(CbxFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 110, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 879, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IdFacturaTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdFacturaTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdFacturaTextActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void SubTotalTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubTotalTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SubTotalTextActionPerformed

    private void IdProductoTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdProductoTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdProductoTextActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insertGuardar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void NombreProductoTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreProductoTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreProductoTextActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(CbxFactura.getSelectedIndex()==0){
            insertFactura();
            insertFacC();
            try {
                insertFacP();
            } catch (SQLException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(CbxFactura.getSelectedIndex()==1){
           insertFactura();
           try {
                insertFacP();
            } catch (SQLException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void IdPersonaTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_IdPersonaTextFocusLost
        try {        
            searchClientes();
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_IdPersonaTextFocusLost

    private void IdProductoTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_IdProductoTextFocusLost
        try {        
            searchProducto();
        } catch (SQLException ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_IdProductoTextFocusLost

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        limpiar();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void CbxFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxFacturaActionPerformed
        if(CbxFactura.getSelectedIndex()==1){
            IdPersonaText.setEnabled(false);
            NombreText.setEnabled(false);
            ContactoText.setEnabled(false);
        }else if(CbxFactura.getSelectedIndex()==0){
            IdPersonaText.setEnabled(true);
            NombreText.setEnabled(true);
            ContactoText.setEnabled(true);
        }
    }//GEN-LAST:event_CbxFacturaActionPerformed

    private void CantidadTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantidadTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadTextActionPerformed

    private void CantidadTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadTextKeyTyped
        
    }//GEN-LAST:event_CantidadTextKeyTyped

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
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Factura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CantidadLabel;
    private javax.swing.JTextField CantidadText;
    private javax.swing.JComboBox<String> CbxFactura;
    private javax.swing.JLabel ContactoLabel;
    private javax.swing.JTextField ContactoText;
    private javax.swing.JLabel FechaVentaLabel;
    private javax.swing.JTextField FechaVentaText;
    private javax.swing.JLabel IdClienteLabel;
    private javax.swing.JTextField IdFacturaText;
    private javax.swing.JTextField IdPersonaText;
    private javax.swing.JLabel IdProductoLabel;
    private javax.swing.JTextField IdProductoText;
    private javax.swing.JLabel IdVentaLabel;
    private javax.swing.JLabel NombreLabel;
    private javax.swing.JLabel NombreProductoLabel;
    private javax.swing.JTextField NombreProductoText;
    private javax.swing.JTextField NombreText;
    private javax.swing.JLabel PrecioLabel;
    private javax.swing.JTextField PrecioText;
    private javax.swing.JLabel SubTotalLabel;
    private javax.swing.JTextField SubTotalText;
    private javax.swing.JLabel TotalLabel;
    private javax.swing.JTextField TotalText;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableFac;
    // End of variables declaration//GEN-END:variables
}
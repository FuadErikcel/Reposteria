/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.reposteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fuad Erikcel
 */
public class Productos extends javax.swing.JFrame {

    /**
     * Creates new form Productos
     */
    public Productos() {
        initComponents();
        visible();
    }
    
    Conexion conexionObjeto = new Conexion();
    Connection conexion = conexionObjeto.getConexion();
    
    public void visible(){
        if(cbxProducto.getSelectedIndex()==1){
            lbTipo.setVisible(true);
            cbxTipoPan.setVisible(true);
            cbxTipoGalleta.setVisible(false);
            cbxPastelNormal.setVisible(false);
            lbExtra.setVisible(false);
            txtExtra.setVisible(false);
            txtSabor.setVisible(false);
            txtBetun.setVisible(false);
            txtRelleno.setVisible(false);
            lbRelleno.setVisible(false);
            lbBetun.setVisible(false);
            lbSabor.setVisible(false);
            mostrarTablePan();
        }else if(cbxProducto.getSelectedIndex()==0){
             lbTipo.setVisible(true);
            cbxTipoPan.setVisible(false);
            cbxTipoGalleta.setVisible(true);
            cbxPastelNormal.setVisible(false);
            lbExtra.setVisible(true);
            txtExtra.setVisible(true);
             txtSabor.setVisible(false);
            txtBetun.setVisible(false);
            txtRelleno.setVisible(false);
            lbRelleno.setVisible(false);
            lbBetun.setVisible(false);
            lbSabor.setVisible(false);
            mostrarTableGalletas();
        }else if(cbxProducto.getSelectedIndex()==2){
            lbTipo.setVisible(true);
            cbxTipoPan.setVisible(false);
            cbxTipoGalleta.setVisible(false);
            cbxPastelNormal.setVisible(true);
            lbExtra.setVisible(false);
            txtExtra.setVisible(false);
             txtSabor.setVisible(true);
            txtBetun.setVisible(true);
            txtRelleno.setVisible(true);
            lbRelleno.setVisible(true);
            lbBetun.setVisible(true);
            lbSabor.setVisible(true);
            mostrarTablePastelesNormales();
        }
    }
    
    public void insertDates1(){
        String identidad = txtIdentidad.getText();
        Double precio = Double.parseDouble(txtPrecio.getText());
        String fechaElaboracion = txtFechaElaboracion.getText();
        
        try {
            String consultaInsert = "INSERT INTO producto(idproducto,precioproducto, fechaelaboracion) VALUES (?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consultaInsert);
            statement.setString(1, identidad);
            statement.setDouble(2, precio);
            statement.setString(3, fechaElaboracion); 
            int filasInsertadas = statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar datos: " + ex.getMessage());
         }
    }

    public void insertDates(){
        String identidad = txtIdentidad.getText();
        String precio = txtPrecio.getText();
        String fechaElaboracion = txtFechaElaboracion.getText();
        if(cbxProducto.getSelectedIndex()==0){
 
            String tipo = cbxTipoGalleta.getSelectedItem().toString();
            String extra = txtExtra.getText();
            String fechaVencimiento = txtFechaVencimiento.getText();
            int stock = Integer.parseInt(txtStock.getText());
          
              try {
            String consultaInsert = "INSERT INTO galletas(idgalletas,tipo, extra, fechaVencimiento, stock) VALUES (?, ?, ?, ?,?)";
            PreparedStatement statement = conexion.prepareStatement(consultaInsert);
            statement.setString(1, identidad);
            statement.setString(2, tipo);
            statement.setString(3, extra);
            statement.setString(4, fechaVencimiento);
            statement.setInt(5, stock);
            
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(this, "Datos insertados correctamente");
                txtIdentidad.setText("");
                txtExtra.setText("");
                txtFechaVencimiento.setText("");
                txtStock.setText("");
                txtFechaElaboracion.setText("");
                txtPrecio.setText("");
                mostrarTableGalletas();
            } else {
                JOptionPane.showMessageDialog(this, "Error al insertar datos");
                   }
            statement.close();
            } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar datos: " + ex.getMessage());
                 }
             
        }else if(cbxProducto.getSelectedIndex()==1){
            String tipo = cbxTipoPan.getSelectedItem().toString();
            String fechaVencimiento = txtFechaVencimiento.getText();
            int stock = Integer.parseInt(txtStock.getText());
          
              try {
            String consultaInsert = "INSERT INTO pan(idpan,tipo, fechaVencimiento, stock) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consultaInsert);
            statement.setString(1, identidad);
            statement.setString(2, tipo);
            statement.setString(3, fechaVencimiento);
            statement.setInt(4, stock);
            
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(this, "Datos insertados correctamente");
                txtIdentidad.setText("");
                txtExtra.setText("");
                txtFechaVencimiento.setText("");
                txtStock.setText("");
                txtFechaElaboracion.setText("");
                txtPrecio.setText("");
                mostrarTablePan();
            } else {
                JOptionPane.showMessageDialog(this, "Error al insertar datos");
                   }
            statement.close();
            } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar datos: " + ex.getMessage());
                 }
              
        }else if(cbxProducto.getSelectedIndex()==2){
                String tipo = cbxPastelNormal.getSelectedItem().toString();
                String fechaVencimiento = txtFechaVencimiento.getText();
                int stock = Integer.parseInt(txtStock.getText());
                String sabor = txtSabor.getText();
                String relleno = txtRelleno.getText();
                String betun = txtBetun.getText();


                  try {
                String consultaInsert = "INSERT INTO pnormal(idpastelnormal,tipo,sabor,relleno,betun,stock, fechaVencimiento) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = conexion.prepareStatement(consultaInsert);
                statement.setString(1, identidad);
                statement.setString(2, tipo);
                statement.setString(3, sabor);
                statement.setString(4, relleno);
                statement.setString(5, betun);
                statement.setInt(6, stock);
                statement.setString(7, fechaVencimiento);


                int filasInsertadas = statement.executeUpdate();

                if (filasInsertadas > 0) {
                    JOptionPane.showMessageDialog(this, "Datos insertados correctamente");
                    txtIdentidad.setText("");
                    txtExtra.setText("");
                    txtFechaVencimiento.setText("");
                    txtStock.setText("");
                    txtFechaElaboracion.setText("");
                    txtPrecio.setText("");
                    txtSabor.setText("");
                    txtRelleno.setText("");
                    txtBetun.setText("");

                    mostrarTablePastelesNormales();     
                } else {
                    JOptionPane.showMessageDialog(this, "Error al insertar datos");
                       }
                statement.close();
                } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al insertar datos: " + ex.getMessage());
                     }  
        }        
      
}        
    
    public void mostrarTableGalletas(){
      try {        
            String consultaSQL = "SELECT idproducto, tipo, fechaelaboracion, fechavencimiento, precioproducto, stock " +
                                 "FROM galletas " +
                                 "JOIN producto ON producto.idproducto = galletas.idgalletas";
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);

            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("ID Galleta");
            modeloTabla.addColumn("Tipo");
            modeloTabla.addColumn("Fecha Elaboración");
            modeloTabla.addColumn("Fecha Vencimiento");
            modeloTabla.addColumn("Precio");
            modeloTabla.addColumn("Stock");

            while (resultSet.next()) {
                Object[] fila = {
                        resultSet.getString("idproducto"),
                        resultSet.getString("tipo"),
                        resultSet.getString("fechaelaboracion"),
                        resultSet.getString("fechavencimiento"),
                        resultSet.getDouble("precioproducto"),
                        resultSet.getInt("stock")
                };
                modeloTabla.addRow(fila);
            }

            tablaProductos.setModel(modeloTabla);

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
        }
    }  
    
    
    public void BuscarGalletas(){
            
      try {  
          String identidad = txtIdentidad.getText();
            String consultaSQL = "SELECT idgalletas, fechaelaboracion, precioproducto,  fechavencimiento,  stock, extra " +
                                 "FROM galletas " +
                                 "JOIN producto ON idgalletas = ? ";
            
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);

            statement.setString(1, identidad  );
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {              
                txtFechaElaboracion.setText(resultSet.getString("fechaelaboracion"));
                txtFechaVencimiento.setText(resultSet.getString("fechavencimiento"));
                txtPrecio.setText(resultSet.getString("precioproducto"));
                txtStock.setText(resultSet.getString("stock"));
                txtExtra.setText(resultSet.getString("extra"));
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron resultados para el ID proporcionado.");
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
        }
    }  
    
    
     public void mostrarTablePan(){
      try {

            String consultaSQL = "SELECT idproducto, tipo, fechaelaboracion, fechavencimiento, precioproducto, stock " +
                                 "FROM pan " +
                                 "JOIN producto ON producto.idproducto = pan.idpan";
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);

            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("ID Pan");
            modeloTabla.addColumn("Tipo");
            modeloTabla.addColumn("Fecha Elaboración");
            modeloTabla.addColumn("Fecha Vencimiento");
            modeloTabla.addColumn("Precio");
            modeloTabla.addColumn("Stock");

            while (resultSet.next()) {
                Object[] fila = {
                        resultSet.getString("idproducto"),
                        resultSet.getString("tipo"),
                        resultSet.getString("fechaelaboracion"),
                        resultSet.getString("fechavencimiento"),
                        resultSet.getDouble("precioproducto"),
                        resultSet.getInt("stock")
                };
                modeloTabla.addRow(fila);
            }

            tablaProductos.setModel(modeloTabla);

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
        }
    }  
     
     public void mostrarTablePastelesNormales(){
      try {
            String consultaSQL = "SELECT idproducto, tipo, sabor, relleno, betun, fechaelaboracion, fechavencimiento, precioproducto, stock " +
                                 "FROM pnormal " +
                                 "JOIN producto ON producto.idproducto = pnormal.idpastelnormal";
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);

            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("ID Pastel");
            modeloTabla.addColumn("Tipo");
            modeloTabla.addColumn("Sabor");
            modeloTabla.addColumn("Relleno");
            modeloTabla.addColumn("Betun");
            modeloTabla.addColumn("Fecha Elaboración");
            modeloTabla.addColumn("Fecha Vencimiento");
            modeloTabla.addColumn("Precio");
            modeloTabla.addColumn("Stock");

            while (resultSet.next()) {
                Object[] fila = {
                        resultSet.getString("idproducto"),
                        resultSet.getString("tipo"),
                        resultSet.getString("sabor"),
                        resultSet.getString("relleno"),
                        resultSet.getString("betun"),
                        resultSet.getString("fechaelaboracion"),
                        resultSet.getString("fechavencimiento"),
                        resultSet.getDouble("precioproducto"),
                        resultSet.getInt("stock")
                };
                modeloTabla.addRow(fila);
            }

            tablaProductos.setModel(modeloTabla);

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
        }
    } 
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbProductos = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdentidad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbxProducto = new javax.swing.JComboBox<>();
        lbTipo = new javax.swing.JLabel();
        cbxTipoGalleta = new javax.swing.JComboBox<>();
        cbxTipoPan = new javax.swing.JComboBox<>();
        lbExtra = new javax.swing.JLabel();
        txtExtra = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtFechaElaboracion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtFechaVencimiento = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        cbxPastelNormal = new javax.swing.JComboBox<>();
        lbSabor = new javax.swing.JLabel();
        txtSabor = new javax.swing.JTextField();
        lbRelleno = new javax.swing.JLabel();
        txtRelleno = new javax.swing.JTextField();
        lbBetun = new javax.swing.JLabel();
        txtBetun = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbProductos.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        lbProductos.setText("Productos");
        jPanel1.add(lbProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 17, -1, 40));

        jLabel2.setText("Identidad:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

        txtIdentidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentidadActionPerformed(evt);
            }
        });
        jPanel1.add(txtIdentidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 200, -1));

        jLabel3.setText("Producto:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 60, 20));

        cbxProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Galletas", "Pan", "Pastel Normal" }));
        cbxProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxProductoActionPerformed(evt);
            }
        });
        jPanel1.add(cbxProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 200, -1));

        lbTipo.setText("Tipo:");
        jPanel1.add(lbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        cbxTipoGalleta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Saladas", "Rellenas", "Normales" }));
        cbxTipoGalleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoGalletaActionPerformed(evt);
            }
        });
        jPanel1.add(cbxTipoGalleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 200, -1));

        cbxTipoPan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pan Blanco", "Pan Integral", "Pan Molde", "Pan Baguette", "Semita", "Pan Francés", "Pan de Banano", "Pan de Zanahoria" }));
        jPanel1.add(cbxTipoPan, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 200, -1));

        lbExtra.setText("Extra:");
        jPanel1.add(lbExtra, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, -1, 20));

        txtExtra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtExtraActionPerformed(evt);
            }
        });
        jPanel1.add(txtExtra, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 200, -1));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 90, -1));

        jLabel4.setText("Fecha de Elaboración:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        txtFechaElaboracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaElaboracionActionPerformed(evt);
            }
        });
        jPanel1.add(txtFechaElaboracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 200, -1));

        jLabel5.setText("Precio:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 200, -1));

        jLabel6.setText("Fecha de Vencimiento:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        txtFechaVencimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaVencimientoActionPerformed(evt);
            }
        });
        jPanel1.add(txtFechaVencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 200, -1));

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaProductos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 610, 160));

        jLabel7.setText("Stock:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        txtStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockActionPerformed(evt);
            }
        });
        jPanel1.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, 200, -1));

        cbxPastelNormal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tres Leches", "Helado", "Seco" }));
        jPanel1.add(cbxPastelNormal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 200, -1));

        lbSabor.setText("Sabor:");
        jPanel1.add(lbSabor, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, -1, -1));

        txtSabor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaborActionPerformed(evt);
            }
        });
        jPanel1.add(txtSabor, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 200, -1));

        lbRelleno.setText("Relleno:");
        jPanel1.add(lbRelleno, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, -1, -1));

        txtRelleno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRellenoActionPerformed(evt);
            }
        });
        jPanel1.add(txtRelleno, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 200, -1));

        lbBetun.setText("Betún:");
        jPanel1.add(lbBetun, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, -1, -1));
        jPanel1.add(txtBetun, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 200, -1));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 140, 90, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdentidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentidadActionPerformed
    }//GEN-LAST:event_txtIdentidadActionPerformed

    private void cbxProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxProductoActionPerformed
      visible();
    }//GEN-LAST:event_cbxProductoActionPerformed

    private void cbxTipoGalletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoGalletaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoGalletaActionPerformed

    private void txtExtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtExtraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtExtraActionPerformed

    private void txtFechaElaboracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaElaboracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaElaboracionActionPerformed

    private void txtFechaVencimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaVencimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaVencimientoActionPerformed

    private void txtStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        insertDates1();
        insertDates();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtRellenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRellenoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRellenoActionPerformed

    private void txtSaborActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaborActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaborActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
      BuscarGalletas();
    }//GEN-LAST:event_btnBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Productos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbxPastelNormal;
    private javax.swing.JComboBox<String> cbxProducto;
    private javax.swing.JComboBox<String> cbxTipoGalleta;
    private javax.swing.JComboBox<String> cbxTipoPan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbBetun;
    private javax.swing.JLabel lbExtra;
    private javax.swing.JLabel lbProductos;
    private javax.swing.JLabel lbRelleno;
    private javax.swing.JLabel lbSabor;
    private javax.swing.JLabel lbTipo;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTextField txtBetun;
    private javax.swing.JTextField txtExtra;
    private javax.swing.JTextField txtFechaElaboracion;
    private javax.swing.JTextField txtFechaVencimiento;
    private javax.swing.JTextField txtIdentidad;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtRelleno;
    private javax.swing.JTextField txtSabor;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}

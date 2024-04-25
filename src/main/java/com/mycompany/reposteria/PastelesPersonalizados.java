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

/**
 *
 * @author Fuad Erikcel
 */
public class PastelesPersonalizados extends javax.swing.JFrame {
DefaultTableModel model;
    /**
     * Creates new form PastelesPersonalizados
     */
    public PastelesPersonalizados() {
        initComponents();
        
        model = new DefaultTableModel();
        model.addColumn("Torta");
        model.addColumn("Precio");
        model.addColumn("Cantidad");
        model.addColumn("Relleno");
        model.addColumn("Sabor");  
        tablePP.setModel(model);
        mostrarTablePastelesPersonalizados();
    }
    
     Conexion conexionObjeto = new Conexion();
     Connection conexion = conexionObjeto.getConexion();
    
     public void guardar(){
            String torta = txtTipoTorta.getText();
            String precio = txtPrecioTorta.getText();
            String cantidad = txtCantidad.getText();
            String relleno = txtRelleno.getText();
            String sabor = txtSabor.getText();
           model.addRow(new Object[]{torta, precio, cantidad, relleno, sabor});       
            txtTipoTorta.setText("");
            txtPrecioTorta.setText("");
            txtCantidad.setText("");
            txtRelleno.setText("");
            txtSabor.setText("");
            
            double subTotal = 0;
            double total = 0;
            double precio1 = 0;
            double cantidad1 = 0;
            
            for(int i=0; i<tablePP.getRowCount(); i++){
                precio1 = Double.parseDouble(tablePP.getValueAt(i,1).toString());
                cantidad1 =   Double.parseDouble(tablePP.getValueAt(i,2).toString());
                subTotal += precio1 * cantidad1;
            }
            
          total = (subTotal * 0.30)+subTotal;
          String x = Double.toString(subTotal);
          String y = Double.toString(total);

          txtSubTotal.setText(x);
          txtTotal.setText(y);
    }
     
     public void limpiar(){
         txtId.setText("");
         txtBetun.setText("");
         txtFechaElaboracion.setText("");
         txtFechaEntrega.setText("");
         txtDetalles.setText("");
         txtTotal.setText("");
         txtSubTotal.setText("");

         
         int fila = model.getRowCount();
         for(int i = fila-1; i>=0; i--){
            model.removeRow(i);
        }
     }

 public void insertProduct(){
    String identidad = txtId.getText();
    Double precio =  Double.parseDouble(txtTotal.getText());
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
 public void deleteProduct() {
    String idProducto = txtId.getText();
    try {
        String consulta = "DELETE FROM producto WHERE idproducto = ?";
        PreparedStatement statement = conexion.prepareStatement(consulta);
        statement.setString(1, idProducto);
        statement.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al eliminar producto: " + ex.getMessage());
    }
}
 public void insertPPersonalizado(){
     String idPersonalizado = txtId.getText();
     String betun = txtBetun.getText();
     String fechaEntrega = txtFechaEntrega.getText();
     String detalles = txtDetalles.getText();
    
     try{
            String consultaInsert = "INSERT INTO ppersonalizado(idppersonalizado,detalles, betun, fechaEntrega) VALUES (?, ?, ?,?)";
            PreparedStatement statement = conexion.prepareStatement(consultaInsert);
            statement.setString(1, idPersonalizado);
            statement.setString(2, detalles);
            statement.setString(3, betun); 
            statement.setString(4, fechaEntrega); 
            int filasInsertadas = statement.executeUpdate();
            statement.close();    
     }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Error al insertar datos: " + ex.getMessage());
     }
 }
 
 public void deletePersonalizado() {
    String idPersonalizado = txtId.getText();

    if (idPersonalizado.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese el ID del producto personalizado a eliminar");
        return;
    }

    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este producto personalizado?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
    
    if (confirmacion != JOptionPane.YES_OPTION) {
        return; 
    }

    try {
        String consulta = "DELETE FROM ppersonalizado WHERE idppersonalizado = ?";
        PreparedStatement statement = conexion.prepareStatement(consulta);
        statement.setString(1, idPersonalizado);
        
        int filasEliminadas = statement.executeUpdate();

        if (filasEliminadas > 0) {
            deleteProduct();
            JOptionPane.showMessageDialog(this, "Producto personalizado eliminado correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ningún producto personalizado con el ID especificado");
        }

        statement.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al eliminar producto personalizado: " + ex.getMessage());
    }
}

 
public void insertTortaPersonalizada() throws SQLException{
    String idTorta = txtTipoTorta.getText();
    String idPersonalizado = txtId.getText();
    
    try{
        String consultaInsert = "INSERT INTO tortapersonalizada(idtorta, idppersonalizado, cantidad, sabor, relleno) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = conexion.prepareStatement(consultaInsert);
        
        for(int i=0; i<tablePP.getRowCount(); i++){
            String cantidad = tablePP.getValueAt(i, 2).toString();
            String sabor = tablePP.getValueAt(i, 3).toString();
            String relleno = tablePP.getValueAt(i, 4).toString();
            String torta = tablePP.getValueAt(i, 0).toString();

            int cantidad1 = Integer.parseInt(cantidad);
            
            statement.setString(1, torta);
            statement.setString(2, idPersonalizado);
            statement.setInt(3, cantidad1);
            statement.setString(4, sabor);
            statement.setString(5, relleno);
            statement.executeUpdate();
        }
    }catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar datos: " + ex.getMessage());
         }
}


public void mostrarTablePastelesPersonalizados(){
      try {

            String consultaSQL = "SELECT idproducto, idtorta, fechaelaboracion, fechaentrega, detalles " +
                                 "FROM tortapersonalizada " +
                                 "JOIN producto ON producto.idproducto = tortapersonalizada.idppersonalizado " +
                                 "JOIN ppersonalizado ON tortapersonalizada.idppersonalizado = ppersonalizado.idppersonalizado";
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);

            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("ID Pastel");
            modeloTabla.addColumn("Torta");
            modeloTabla.addColumn("Fecha Elaboración");
            modeloTabla.addColumn("Fecha Entrega");
            modeloTabla.addColumn("Detalles");

            while (resultSet.next()) {
                Object[] fila = {
                        resultSet.getString("idproducto"),
                        resultSet.getString("idtorta"),
                        resultSet.getString("fechaelaboracion"),
                        resultSet.getString("fechaentrega"),
                        resultSet.getString("detalles"),
                };
                modeloTabla.addRow(fila);
            }

            table1.setModel(modeloTabla);

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
        }
    } 

public void buscarTable(){
    try {
        String identidad = txtId.getText();
            String consultaSQL = "SELECT tortapersonalizada.idtorta, cantidad, relleno, sabor, precio " +
                                 "FROM tortapersonalizada, torta WHERE tortapersonalizada.idppersonalizado =? AND torta.idtorta = tortapersonalizada.idtorta";                             
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            statement.setString(1, identidad  );
            
            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Torta");
                    model.addColumn("Precio");
                    model.addColumn("Cantidad");
                    model.addColumn("Relleno");
                    model.addColumn("Sabor");  
                   
                    
            while (resultSet.next()) {
                Object[] fila = {
                        resultSet.getString("idtorta"),
                        resultSet.getString("precio"),
                        resultSet.getString("cantidad"),
                        resultSet.getString("relleno"),
                        resultSet.getString("sabor")
                };
                model.addRow(fila);
            }

            tablePP.setModel(model);
            statement.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
        }
}

public void buscarText(){
    try {
        String identidad = txtId.getText();
            String consultaSQL = "SELECT betun, fechaelaboracion, fechaentrega, detalles, precioproducto " +
                                 "FROM ppersonalizado "+
                    "JOIN producto ON producto.idproducto = ppersonalizado.idppersonalizado "+
                    "WHERE ppersonalizado.idppersonalizado =?";                             
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            statement.setString(1, identidad  );
            
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                txtBetun.setText(resultSet.getString("betun"));
                txtFechaElaboracion.setText(resultSet.getString("fechaelaboracion"));
                txtFechaEntrega.setText(resultSet.getString("fechaentrega"));
                txtDetalles.setText(resultSet.getString("detalles"));
                txtTotal.setText(resultSet.getString("precioproducto"));
            }

            statement.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
        }
}
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbId = new javax.swing.JLabel();
        txtFechaEntrega = new javax.swing.JTextField();
        lbBetun = new javax.swing.JLabel();
        lbFechaEntrega = new javax.swing.JLabel();
        lbDetalles = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDetalles = new javax.swing.JTextArea();
        txtBetun = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbxTorta = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTipoTorta = new javax.swing.JTextField();
        txtPrecioTorta = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSabor = new javax.swing.JTextField();
        txtRelleno = new javax.swing.JTextField();
        lbFechaElaboracion = new javax.swing.JLabel();
        txtFechaElaboracion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePP = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Pasteles Personalizados");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 260, -1));

        lbId.setText("ID:");
        jPanel1.add(lbId, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));
        jPanel1.add(txtFechaEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 230, -1));

        lbBetun.setText("Betún:");
        jPanel1.add(lbBetun, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        lbFechaEntrega.setText("Fecha de Entrega:");
        jPanel1.add(lbFechaEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        lbDetalles.setText("Detalles:");
        jPanel1.add(lbDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        txtDetalles.setColumns(20);
        txtDetalles.setRows(5);
        jScrollPane1.setViewportView(txtDetalles);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 230, 60));

        txtBetun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBetunActionPerformed(evt);
            }
        });
        jPanel1.add(txtBetun, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 230, -1));

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 230, -1));

        jLabel2.setText("Torta:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, -1, -1));

        cbxTorta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3-5 Personas", "5-8 Personas", "8-10 Personas", "10-15 Personas", "15-20 Personas", "20-25Personas" }));
        cbxTorta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTortaActionPerformed(evt);
            }
        });
        jPanel1.add(cbxTorta, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 230, -1));

        jLabel3.setText("Tipo:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, -1, -1));

        jLabel4.setText("Precio:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, -1, -1));

        txtTipoTorta.setEditable(false);
        jPanel1.add(txtTipoTorta, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 230, -1));

        txtPrecioTorta.setEditable(false);
        txtPrecioTorta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioTortaActionPerformed(evt);
            }
        });
        jPanel1.add(txtPrecioTorta, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 230, -1));

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 100, 110, -1));

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 610, -1, -1));

        jLabel5.setText("Cantidad:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, -1, -1));

        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        jPanel1.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 220, 230, -1));

        jLabel6.setText("Sabor:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, -1, -1));

        jLabel7.setText("Relleno:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, -1, -1));
        jPanel1.add(txtSabor, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, 230, -1));

        txtRelleno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRellenoActionPerformed(evt);
            }
        });
        jPanel1.add(txtRelleno, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, 230, -1));

        lbFechaElaboracion.setText("Fecha de Elaboracion:");
        jPanel1.add(lbFechaElaboracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));
        jPanel1.add(txtFechaElaboracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 230, -1));

        jLabel8.setText("Sub-total:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 406, -1, 20));

        txtSubTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubTotalActionPerformed(evt);
            }
        });
        jPanel1.add(txtSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, 110, -1));

        jLabel9.setText("Total:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 440, -1, -1));
        jPanel1.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 440, 110, -1));

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Torta", "Fecha de Elaboracion", "Fecha de Entrega", "Detalles"
            }
        ));
        jScrollPane3.setViewportView(table1);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 610, 210));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 550, -1, -1));

        tablePP.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablePP);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, 340, 150));

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 580, -1, -1));

        jButton4.setText("Menu Principal");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 140, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 790));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtPrecioTortaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioTortaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioTortaActionPerformed

    private void txtRellenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRellenoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRellenoActionPerformed

    private void txtBetunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBetunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBetunActionPerformed

    private void txtSubTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubTotalActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          guardar();  
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void cbxTortaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTortaActionPerformed
        if(cbxTorta.getSelectedIndex()==0){
            txtTipoTorta.setText("TT0001");
            txtPrecioTorta.setText("100");
        }else if(cbxTorta.getSelectedIndex()==1){
            txtTipoTorta.setText("TT0002");
            txtPrecioTorta.setText("200");
        }else if(cbxTorta.getSelectedIndex()==2){
            txtTipoTorta.setText("TT0003");
            txtPrecioTorta.setText("300");
        }else if(cbxTorta.getSelectedIndex()==3){
            txtTipoTorta.setText("TT0004");
            txtPrecioTorta.setText("450");
        }else if(cbxTorta.getSelectedIndex()==4){
            txtTipoTorta.setText("TT0005");
            txtPrecioTorta.setText("550");
        }else if(cbxTorta.getSelectedIndex()==5){
            txtTipoTorta.setText("TT0006");
            txtPrecioTorta.setText("600");
        }
    }//GEN-LAST:event_cbxTortaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        insertProduct();
        insertPPersonalizado();
        try {
            insertTortaPersonalizada();
        } catch (SQLException ex) {
            Logger.getLogger(PastelesPersonalizados.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(this, "Datos insertados correctamente");
        limpiar();
        mostrarTablePastelesPersonalizados();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       buscarTable();
       buscarText();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       deletePersonalizado();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
                MenuPrincipal mp = new MenuPrincipal();
        mp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(PastelesPersonalizados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PastelesPersonalizados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PastelesPersonalizados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PastelesPersonalizados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PastelesPersonalizados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbxTorta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbBetun;
    private javax.swing.JLabel lbDetalles;
    private javax.swing.JLabel lbFechaElaboracion;
    private javax.swing.JLabel lbFechaEntrega;
    private javax.swing.JLabel lbId;
    private javax.swing.JTable table1;
    private javax.swing.JTable tablePP;
    private javax.swing.JTextField txtBetun;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextArea txtDetalles;
    private javax.swing.JTextField txtFechaElaboracion;
    private javax.swing.JTextField txtFechaEntrega;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtPrecioTorta;
    private javax.swing.JTextField txtRelleno;
    private javax.swing.JTextField txtSabor;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTipoTorta;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

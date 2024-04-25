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
        java.util.Date fecha = txtFechaSelect.getDate();
        java.sql.Date fechaElaboracion = new java.sql.Date(fecha.getTime());
        
        try {
            String consultaInsert = "INSERT INTO producto(idproducto,precioproducto, fechaelaboracion) VALUES (?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consultaInsert);
            statement.setString(1, identidad);
            statement.setDouble(2, precio);
            statement.setDate(3, (fechaElaboracion)); 
            int filasInsertadas = statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar datos: " + ex.getMessage());
         }
    }

    public void insertDates(){
        String identidad = txtIdentidad.getText();
        if(cbxProducto.getSelectedIndex()==0){ 
            String tipo = cbxTipoGalleta.getSelectedItem().toString();
            String extra = txtExtra.getText();
            int stock = Integer.parseInt(txtStock.getText());
            java.util.Date fecha = txtVencimiento.getDate();
            java.sql.Date fechaVencimiento = new java.sql.Date(fecha.getTime());
            
          
              try {
            String consultaInsert = "INSERT INTO galletas(idgalletas,tipo, extra, fechaVencimiento, stock) VALUES (?, ?, ?, ?,?)";
            PreparedStatement statement = conexion.prepareStatement(consultaInsert);
            statement.setString(1, identidad);
            statement.setString(2, tipo);
            statement.setString(3, extra);
            statement.setDate(4, fechaVencimiento);
            statement.setInt(5, stock);
            
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(this, "Datos insertados correctamente");
                txtIdentidad.setText("");
                txtExtra.setText("");
                //txtFechaVencimiento.setText("");
                txtStock.setText("");
                //txtFechaElaboracion.setText("");
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
            int stock = Integer.parseInt(txtStock.getText());
            java.util.Date fecha = txtVencimiento.getDate();
            java.sql.Date fechaVencimiento = new java.sql.Date(fecha.getTime());
          
              try {
            String consultaInsert = "INSERT INTO pan(idpan,tipo, fechaVencimiento, stock) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consultaInsert);
            statement.setString(1, identidad);
            statement.setString(2, tipo);
            statement.setDate(3, fechaVencimiento);
            statement.setInt(4, stock);
            
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(this, "Datos insertados correctamente");
                txtIdentidad.setText("");
                txtExtra.setText("");
                //txtFechaVencimiento.setText("");
                txtStock.setText("");
                
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
                int stock = Integer.parseInt(txtStock.getText());
                String sabor = txtSabor.getText();
                String relleno = txtRelleno.getText();
                String betun = txtBetun.getText();
                java.util.Date fecha = txtVencimiento.getDate();
                java.sql.Date fechaVencimiento = new java.sql.Date(fecha.getTime());


                  try {
                String consultaInsert = "INSERT INTO pnormal(idpastelnormal,tipo,sabor,relleno,betun,stock, fechaVencimiento) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = conexion.prepareStatement(consultaInsert);
                statement.setString(1, identidad);
                statement.setString(2, tipo);
                statement.setString(3, sabor);
                statement.setString(4, relleno);
                statement.setString(5, betun);
                statement.setInt(6, stock);
                statement.setDate(7, fechaVencimiento);


                int filasInsertadas = statement.executeUpdate();

                if (filasInsertadas > 0) {
                    JOptionPane.showMessageDialog(this, "Datos insertados correctamente");
                    txtIdentidad.setText("");
                    txtExtra.setText("");
                    txtStock.setText("");
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
    
    public void updateData() {
    String identidad = txtIdentidad.getText();

    if (cbxProducto.getSelectedIndex() == 0) {
        String tipo = cbxTipoGalleta.getSelectedItem().toString();
        String extra = txtExtra.getText();
        int stock = Integer.parseInt(txtStock.getText());
        java.util.Date fecha = txtVencimiento.getDate();
        java.sql.Date fechaVencimiento = new java.sql.Date(fecha.getTime());

        try {
            String consultaUpdate = "UPDATE galletas SET tipo = ?, extra = ?, fechaVencimiento = ?, stock = ? WHERE idgalletas = ?";
            PreparedStatement statement = conexion.prepareStatement(consultaUpdate);
            statement.setString(1, tipo);
            statement.setString(2, extra);
            statement.setDate(3, fechaVencimiento);
            statement.setInt(4, stock);
            statement.setString(5, identidad);

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(this, "Datos actualizados correctamente");
                txtIdentidad.setText("");
                txtExtra.setText("");
                txtStock.setText("");
                mostrarTableGalletas();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna galleta con el ID especificado");
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar datos: " + ex.getMessage());
        }
    } else if (cbxProducto.getSelectedIndex() == 1) {
        String tipo = cbxTipoPan.getSelectedItem().toString();
        int stock = Integer.parseInt(txtStock.getText());
        java.util.Date fecha = txtVencimiento.getDate();
        java.sql.Date fechaVencimiento = new java.sql.Date(fecha.getTime());

        try {
            String consultaUpdate = "UPDATE pan SET tipo = ?, fechaVencimiento = ?, stock = ? WHERE idpan = ?";
            PreparedStatement statement = conexion.prepareStatement(consultaUpdate);
            statement.setString(1, tipo);
            statement.setDate(2, fechaVencimiento);
            statement.setInt(3, stock);
            statement.setString(4, identidad);

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(this, "Datos actualizados correctamente");
                txtIdentidad.setText("");
                txtStock.setText("");
                mostrarTablePan();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún pan con el ID especificado");
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar datos: " + ex.getMessage());
        }
    } else if (cbxProducto.getSelectedIndex() == 2) {
        String tipo = cbxPastelNormal.getSelectedItem().toString();
        int stock = Integer.parseInt(txtStock.getText());
        String sabor = txtSabor.getText();
        String relleno = txtRelleno.getText();
        String betun = txtBetun.getText();
        java.util.Date fecha = txtVencimiento.getDate();
        java.sql.Date fechaVencimiento = new java.sql.Date(fecha.getTime());

        try {
            String consultaUpdate = "UPDATE pnormal SET tipo = ?, sabor = ?, relleno = ?, betun = ?, stock = ?, fechaVencimiento = ? WHERE idpastelnormal = ?";
            PreparedStatement statement = conexion.prepareStatement(consultaUpdate);
            statement.setString(1, tipo);
            statement.setString(2, sabor);
            statement.setString(3, relleno);
            statement.setString(4, betun);
            statement.setInt(5, stock);
            statement.setDate(6, fechaVencimiento);
            statement.setString(7, identidad);

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(this, "Datos actualizados correctamente");
                txtIdentidad.setText("");
                txtStock.setText("");
                txtSabor.setText("");
                txtRelleno.setText("");
                txtBetun.setText("");
                mostrarTablePastelesNormales();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún pastel normal con el ID especificado");
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar datos: " + ex.getMessage());
        }
    }
}

    
public void deleteProduct() {
    String identidad = txtIdentidad.getText();
    
    try {
        String consultaDelete = "DELETE FROM producto WHERE idproducto = ?";
        PreparedStatement statement = conexion.prepareStatement(consultaDelete);
        statement.setString(1, identidad);
        
        int filasEliminadas = statement.executeUpdate();

        if (filasEliminadas > 0) {
            txtIdentidad.setText("");
            txtPrecio.setText("");
            txtFechaSelect.setDate(null);
        }
        statement.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al eliminar producto: " + ex.getMessage());
    }
}
    
 public void deleteProduct1() {
    String identidad = txtIdentidad.getText();
    
    if (cbxProducto.getSelectedIndex() == 0) {
        try {
            String consultaDelete = "DELETE FROM galletas WHERE idgalletas = ?";
            PreparedStatement statement = conexion.prepareStatement(consultaDelete);
            statement.setString(1, identidad);

            int filasEliminadas = statement.executeUpdate();
            
            if (filasEliminadas > 0) {
                deleteProduct();
                JOptionPane.showMessageDialog(this, "Galleta eliminada correctamente");
                txtIdentidad.setText("");
                txtExtra.setText("");
                txtStock.setText("");
                mostrarTableGalletas();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna galleta con el ID especificado");
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar galleta: " + ex.getMessage());
        }
    } else if (cbxProducto.getSelectedIndex() == 1) {
        try {
            String consultaDelete = "DELETE FROM pan WHERE idpan = ?";
            PreparedStatement statement = conexion.prepareStatement(consultaDelete);
            statement.setString(1, identidad);
            
            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas > 0) {
                deleteProduct();
                JOptionPane.showMessageDialog(this, "Pan eliminado correctamente");
                txtIdentidad.setText("");
                txtStock.setText("");
                
                mostrarTablePan();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún pan con el ID especificado");
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar pan: " + ex.getMessage());
        }
    } else if (cbxProducto.getSelectedIndex() == 2) {
        try {
            String consultaDelete = "DELETE FROM pnormal WHERE idpastelnormal = ?";
            PreparedStatement statement = conexion.prepareStatement(consultaDelete);
            statement.setString(1, identidad);
            
            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas > 0) {
                deleteProduct();
                JOptionPane.showMessageDialog(this, "Pastel normal eliminado correctamente");
                txtIdentidad.setText("");
                txtStock.setText("");
                txtSabor.setText("");
                txtRelleno.setText("");
                txtBetun.setText("");
                
                mostrarTablePastelesNormales();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún pastel normal con el ID especificado");
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar pastel normal: " + ex.getMessage());
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
                                 "JOIN producto ON producto.idproducto = galletas.idgalletas " +
                                  "AND galletas.idgalletas = ? ";
            
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);

            statement.setString(1, identidad  );
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {              
                txtFechaSelect.setDate(resultSet.getDate("fechaelaboracion"));
                txtVencimiento.setDate(resultSet.getDate("fechaelaboracion"));
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
        jLabel5 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
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
        txtFechaSelect = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        txtVencimiento = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        btnR = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

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
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 120, -1));

        jLabel4.setText("Fecha de Elaboración:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jLabel5.setText("Precio:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, -1, -1));
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 200, -1));

        jLabel6.setText("Fecha de Vencimiento:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

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
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 140, 120, -1));
        jPanel1.add(txtFechaSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 200, -1));

        jButton1.setText("Eliminar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, 120, -1));
        jPanel1.add(txtVencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 200, -1));

        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, 120, -1));

        btnR.setText("Generar Reporte");
        btnR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRActionPerformed(evt);
            }
        });
        jPanel1.add(btnR, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, -1, -1));

        jButton3.setText("Menu Principal");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, -1, -1));

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        deleteProduct1();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        updateData();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRActionPerformed
      if(cbxProducto.getSelectedIndex()==0){
             String jrxmlFilePath  = "Galletas.jrxml";
             String jasperFilePath = "Galletas.jasper";
        
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
      }else if(cbxProducto.getSelectedIndex()==1){
             String jrxmlFilePath  = "Pan.jrxml";
             String jasperFilePath = "Pan.jasper";
        
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
      }else if(cbxProducto.getSelectedIndex()==2){
             String jrxmlFilePath  = "PastelNormal.jrxml";
             String jasperFilePath = "PastelNormal.jasper";
        
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
      }
    }//GEN-LAST:event_btnRActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
              MenuPrincipal mp = new MenuPrincipal();
        mp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

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
    private javax.swing.JButton btnR;
    private javax.swing.JComboBox<String> cbxPastelNormal;
    private javax.swing.JComboBox<String> cbxProducto;
    private javax.swing.JComboBox<String> cbxTipoGalleta;
    private javax.swing.JComboBox<String> cbxTipoPan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
    private com.toedter.calendar.JDateChooser txtFechaSelect;
    private javax.swing.JTextField txtIdentidad;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtRelleno;
    private javax.swing.JTextField txtSabor;
    private javax.swing.JTextField txtStock;
    private com.toedter.calendar.JDateChooser txtVencimiento;
    // End of variables declaration//GEN-END:variables
}

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
public class consultasProveedores extends javax.swing.JFrame {

    /**
     * Creates new form consultasProveedores
     */
    public consultasProveedores() {
        initComponents();
    }
        Conexion conexionObjeto = new Conexion();
        Connection conexion = conexionObjeto.getConexion();
        
        
        public void fechaVencimiento(){
        try{
            String consultaSQL = "SELECT \n" +
                                                "    idpan AS idproducto,\n" +
                                                "    tipo,\n" +
                                                "    fechavencimiento AS vencimiento,\n" +
                                                "    CASE \n" +
                                                "        WHEN fechavencimiento < CURRENT_DATE THEN 'Producto Vencido'\n" +
                                                "        ELSE (fechavencimiento - CURRENT_DATE) || ' Días Restantes'\n" +
                                                "    END AS estado\n" +
                                                "FROM \n" +
                                                "    pan\n" +
                                                "UNION ALL\n" +
                                                "SELECT \n" +
                                                "    idgalletas AS idproducto,\n" +
                                                "    tipo,\n" +
                                                "    fechavencimiento AS vencimiento,\n" +
                                                "    CASE \n" +
                                                "        WHEN fechavencimiento < CURRENT_DATE THEN 'Producto Vencido'\n" +
                                                "        ELSE (fechavencimiento - CURRENT_DATE) || ' Días Restantes'\n" +
                                                "    END AS estado\n" +
                                                "FROM \n" +
                                                "    galletas;";
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);

            ResultSet resultSet = statement.executeQuery();
            
            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("ID");
            modeloTabla.addColumn("Tipo");
            modeloTabla.addColumn("Fecha de Vencimiento");
            modeloTabla.addColumn("Estado");

            
            while (resultSet.next()) {
                Object[] fila = {
                        resultSet.getString("idproducto"),
                        resultSet.getString("tipo"),
                        resultSet.getString("vencimiento"),
                        resultSet.getString("estado")

                };
                modeloTabla.addRow(fila);
            }

            tablaFechas.setModel(modeloTabla);

            statement.close();
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
        }
    }
        
        
     public void buscarProveedor(){
        String proveedor = txtProveedor.getText();
         
        try{
            String consultaSQL = "SELECT idproveedor, nombre, contacto, detalles FROM proveedores WHERE nombre ILIKE ?" ;
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            statement.setString(1, "%"+proveedor+"%");

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
                        resultSet.getString("detalles")

                };
                modeloTabla.addRow(fila);
            }

            tablaFechas.setModel(modeloTabla);

            statement.close();
            
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFechas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cbxFiltro = new javax.swing.JComboBox<>();
        lbProveedor = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaFechas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaFechas);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, -1, 270));

        jLabel1.setText("Filtro:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        cbxFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buscar Proveedor", "Proximos a Vencer" }));
        cbxFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFiltroActionPerformed(evt);
            }
        });
        jPanel1.add(cbxFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 240, -1));

        lbProveedor.setText("Proveedor:");
        jPanel1.add(lbProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        txtProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProveedorKeyTyped(evt);
            }
        });
        jPanel1.add(txtProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 240, -1));

        jLabel2.setText("Consultas Proveedores");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, -1, -1));

        jButton1.setText("Menu Principal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedorKeyTyped
        buscarProveedor();
    }//GEN-LAST:event_txtProveedorKeyTyped

    private void cbxFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFiltroActionPerformed
        if(cbxFiltro.getSelectedIndex()==1){
            lbProveedor.setVisible(false);
            txtProveedor.setVisible(false);
            fechaVencimiento();
        }
    }//GEN-LAST:event_cbxFiltroActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MenuPrincipal mp = new MenuPrincipal();
        mp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(consultasProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consultasProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consultasProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consultasProveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new consultasProveedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxFiltro;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbProveedor;
    private javax.swing.JTable tablaFechas;
    private javax.swing.JTextField txtProveedor;
    // End of variables declaration//GEN-END:variables
}

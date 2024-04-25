
package com.mycompany.reposteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.mindrot.jbcrypt.BCrypt;

/**
 * @author Fuad Erikcel
 */
public class Usuarios extends javax.swing.JFrame {


    public Usuarios() {
        initComponents();
        mostrarTableUsuarios();
    }
    
    Conexion conexionObjeto = new Conexion();
    Connection conexion = conexionObjeto.getConexion();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIdUser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbxTipoUsuario = new javax.swing.JComboBox<>();
        btnAgregarUser = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("ID:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));
        jPanel1.add(txtIdUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 170, -1));

        jLabel2.setText("Contraseña:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 170, -1));

        jLabel3.setText("Tipo de Usuario:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        cbxTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador General", "Administrador de Ventas", "Personal de Cocina", "Personal de Caja / Venta" }));
        jPanel1.add(cbxTipoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, -1, -1));

        btnAgregarUser.setText("Agregar");
        btnAgregarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUserActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregarUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 110, -1));

        tableUser.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableUser);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 270, 590, 230));

        jButton1.setText("Menu Principal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 117, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUserActionPerformed
        String user = txtIdUser.getText();
        String password = txtPassword.getText();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        int tipoUsuario = cbxTipoUsuario.getSelectedIndex();
        
         try {
            String consultaInsert = "INSERT INTO usuarios(idusuario,password, tipoUsuario) VALUES (?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consultaInsert);
            statement.setString(1, user);
            statement.setString(2, hashedPassword);
            statement.setInt(3, tipoUsuario);
            
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(this, "Datos insertados correctamente");
                txtIdUser.setText("");
                txtPassword.setText("");
                mostrarTableUsuarios();
            } else {
                JOptionPane.showMessageDialog(this, "Error al insertar datos");
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al insertar datos: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnAgregarUserActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
               MenuPrincipal mp = new MenuPrincipal();
        mp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    
         public void mostrarTableUsuarios(){
      try {        
            String consultaSQL = "SELECT usuarios.idusuario, nombre, password,\n" +
                                                "    CASE \n" +
                                                "        WHEN tipoUsuario = 0 THEN 'Administrador General'\n" +
                                                "        WHEN tipoUsuario = 1 THEN 'Administrador de Ventas'\n" +
                                                "		WHEN tipoUsuario = 2 THEN 'Personal de Cocina'\n" +
                                                "                        WHEN tipoUsuario = 3 THEN 'Personal de Caja / Venta'\n" +
                                                "    END AS tipoUsuario\n" +
                                                "FROM usuarios\n" +
                                                "JOIN persona ON persona.idpersona = usuarios.idusuario";

            PreparedStatement statement = conexion.prepareStatement(consultaSQL);

            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("ID");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Tipo de Usuario");

            while (resultSet.next()) {
                Object[] fila = {
                        resultSet.getString("idusuario"),
                        resultSet.getString("nombre"),
                        resultSet.getString("tipoUsuario"),
                };
                modeloTabla.addRow(fila);
            }

            tableUser.setModel(modeloTabla);

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
        }
    }
    
    
    
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
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarUser;
    private javax.swing.JComboBox<String> cbxTipoUsuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableUser;
    private javax.swing.JTextField txtIdUser;
    private javax.swing.JTextField txtPassword;
    // End of variables declaration//GEN-END:variables
}

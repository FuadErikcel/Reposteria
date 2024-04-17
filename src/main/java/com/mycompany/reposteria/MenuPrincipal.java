/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.reposteria;

import javax.swing.JOptionPane;

/**
 *
 * @author Fuad Erikcel
 */
public class MenuPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
        
        if(Login.tipoUser == 1){
            btnPersonal.setVisible(false);
            btnPnormales.setVisible(false);
            btnPpersonalizados.setVisible(false);   
            lbPersonal.setVisible(false);
            lbPnormales.setVisible(false);
            lbPpersonalizados.setVisible(false);
            btnProveedores.setVisible(true);
            btnFacturas.setVisible(true);
            btnInventario.setVisible(true);
            lbProveedores.setVisible(true);
            lbFacturas.setVisible(true);
            lbInventario.setVisible(true);
            btnConsultas.setVisible(true);
            lbConsultas.setVisible(true);
        }else if(Login.tipoUser == 2){
            btnProveedores.setVisible(false);
            btnFacturas.setVisible(false);
            btnInventario.setVisible(false);
            lbProveedores.setVisible(false);
            lbFacturas.setVisible(false);
            lbInventario.setVisible(false);
            btnPersonal.setVisible(false);
            btnPnormales.setVisible(false);
            btnPpersonalizados.setVisible(false);   
            lbPersonal.setVisible(false);
            lbPnormales.setVisible(false);
            lbPpersonalizados.setVisible(false);
            btnConsultas.setVisible(true);
            lbConsultas.setVisible(true);
        }else if(Login.tipoUser == 0){
            btnPersonal.setVisible(true);
            btnPnormales.setVisible(true);
            btnPpersonalizados.setVisible(true);   
            lbPersonal.setVisible(true);
            lbPnormales.setVisible(true);
            lbPpersonalizados.setVisible(true);
            btnProveedores.setVisible(true);
            btnFacturas.setVisible(true);
            btnInventario.setVisible(true);
            lbProveedores.setVisible(true);
            lbFacturas.setVisible(true);
            lbInventario.setVisible(true);
            btnConsultas.setVisible(true);
            lbConsultas.setVisible(true);
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
        btnFacturas = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        btnPersonal = new javax.swing.JButton();
        btnPnormales = new javax.swing.JButton();
        btnPpersonalizados = new javax.swing.JButton();
        btnProveedores = new javax.swing.JButton();
        lbPersonal = new javax.swing.JLabel();
        lbPnormales = new javax.swing.JLabel();
        lbPpersonalizados = new javax.swing.JLabel();
        lbProveedores = new javax.swing.JLabel();
        lbFacturas = new javax.swing.JLabel();
        lbInventario = new javax.swing.JLabel();
        btnConsultas = new javax.swing.JButton();
        lbConsultas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(btnFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, 90, 80));
        jPanel1.add(btnInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 90, 80));

        btnPersonal.setIcon(new javax.swing.ImageIcon("C:\\Users\\Fuad Erikcel\\Documents\\NetBeansProjects\\Reposteria\\src\\main\\java\\com\\mycompany\\reposteria\\icons\\personal.png")); // NOI18N
        btnPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPersonalActionPerformed(evt);
            }
        });
        jPanel1.add(btnPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 90, 80));
        jPanel1.add(btnPnormales, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 90, 80));
        jPanel1.add(btnPpersonalizados, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, 90, 80));

        btnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresActionPerformed(evt);
            }
        });
        jPanel1.add(btnProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, 100, 80));

        lbPersonal.setText("Personal");
        jPanel1.add(lbPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, -1, -1));

        lbPnormales.setText("Pasteles Sencillos");
        jPanel1.add(lbPnormales, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, -1, -1));

        lbPpersonalizados.setText("Pasteles Personalizados");
        jPanel1.add(lbPpersonalizados, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, -1, -1));

        lbProveedores.setText("Proveedores");
        jPanel1.add(lbProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, -1, -1));

        lbFacturas.setText("Facturas");
        jPanel1.add(lbFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, -1, -1));

        lbInventario.setText("Inventario");
        jPanel1.add(lbInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 370, -1, -1));
        jPanel1.add(btnConsultas, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 100, 80));

        lbConsultas.setText("Consultas");
        jPanel1.add(lbConsultas, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 490, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPersonalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPersonalActionPerformed

    private void btnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedoresActionPerformed

    }//GEN-LAST:event_btnProveedoresActionPerformed

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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultas;
    private javax.swing.JButton btnFacturas;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnPersonal;
    private javax.swing.JButton btnPnormales;
    private javax.swing.JButton btnPpersonalizados;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbConsultas;
    private javax.swing.JLabel lbFacturas;
    private javax.swing.JLabel lbInventario;
    private javax.swing.JLabel lbPersonal;
    private javax.swing.JLabel lbPnormales;
    private javax.swing.JLabel lbPpersonalizados;
    private javax.swing.JLabel lbProveedores;
    // End of variables declaration//GEN-END:variables
}

package com.mycompany.reposteria;

//package com.mycompany.reposteria;

import com.mycompany.reposteria.Conexion;
//import com.mycompany.reposteriac.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author angem
 */
public class Person extends javax.swing.JFrame {
  DefaultTableModel model = new DefaultTableModel();
    /*
     * Creates new form Person
     */
    public Person() {
         initComponents();
                 if(cbmPuesto.getSelectedIndex() == 0){
                   mostrarTablaCliente(); 
                 }else {
                    mostrarTablaPersonal(); 
                 }
             
             
         
         visible();   
    }
    
    Conexion conexionObjeto = new Conexion();
    Connection conexion = conexionObjeto.getConexion();
    private boolean opcionesSeleccionadas = false;

public void visible() {
    int index = cbmPersona.getSelectedIndex();

    if (index == 0) { // Si se selecciona la primera opción
        // Mostrar los componentes relevantes y ocultar los demás
        txtID1.setVisible(true);
        lbIdentidad.setVisible(true);
        txtnombre1.setVisible(true);
        lbNombre.setVisible(true);
        txtcorreo1.setVisible(true);
        lbcorreo.setVisible(true);
        txtcontacto1.setVisible(true);
        lbcontacto.setVisible(true);
        txtdireccion1.setVisible(true);
        lbdireccion.setVisible(true);
        txtsalarios1.setVisible(false);
        lbsalario.setVisible(false);
        cbmPuesto.setVisible(false);
        lbpuesto.setVisible(false);
    } else if (index == 1) { // Si se selecciona la segunda opción
        // Mostrar los componentes relevantes y ocultar los demás
        txtID1.setVisible(true);
        lbIdentidad.setVisible(true);
        txtnombre1.setVisible(true);
        lbNombre.setVisible(true);
        txtcorreo1.setVisible(true);
        lbcorreo.setVisible(true);
        txtcontacto1.setVisible(true);
        lbcontacto.setVisible(true);
        txtdireccion1.setVisible(false);
        lbdireccion.setVisible(false);
        txtsalarios1.setVisible(true);
        lbsalario.setVisible(true);
        cbmPuesto.setVisible(true);
        lbpuesto.setVisible(true);
    }
}



public void insertarDatos1() {
    String identidad = txtID1.getText();
    String nombre = txtnombre1.getText();
    String correo = txtcorreo1.getText();
    String contacto = txtcontacto1.getText();
    
    try {
        String consultaInsert = "INSERT INTO persona(idpersona, nombre, correo, contacto) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conexion.prepareStatement(consultaInsert);
        statement.setString(1, identidad);
        statement.setString(2, nombre);
        statement.setString(3, correo);
        statement.setString(4, contacto);
        
        int filasInsertadas = statement.executeUpdate();
        
        if (filasInsertadas > 0) {
            JOptionPane.showMessageDialog(this, "Datos insertados correctamente");
            // Mostrar los datos actualizados en la tabla de cliente
            mostrarTablaCliente();
        } else {
            JOptionPane.showMessageDialog(this, "Error al insertar datos");
        }
        
        statement.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al insertar datos " + ex.getMessage());
    }
}


    
    
    
public void insertDates() {
String identidad = txtID1.getText();
    String nombre = txtnombre1.getText();
    String correo = txtcorreo1.getText();
    String contacto = txtcontacto1.getText();
    String direccion = txtdireccion1.getText();
    String salarios = txtsalarios1.getText();
    String puesto = cbmPuesto.getSelectedItem().toString();
 if(cbmPersona.getSelectedIndex()==0){
    
    try {
        String consultaInsert = "INSERT INTO cliente(Idcliente, direccion)VALUES (?, ?)";
        PreparedStatement statement = conexion.prepareStatement(consultaInsert);
        statement.setString(1, identidad);
        statement.setString(2, direccion);

        int filasInsertadas = statement.executeUpdate();

        if (filasInsertadas > 0) {
            JOptionPane.showMessageDialog(this, "Datos insertados correctamente");
            // Limpiar los campos después de la inserción exitosa
             limpiarCampos();
            
            // Mostrar los datos actualizados en la tabla de cliente
                mostrarTablaCliente();
            
        } else {
            JOptionPane.showMessageDialog(this, "Error al insertar datos");
        }
        statement.close();
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Error: El salario debe ser un valor numérico válido.");
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al insertar datos: " + ex.getMessage());
    }
  }else if(cbmPersona.getSelectedIndex()==1){
      try {
        double salario = Double.parseDouble(salarios); // Parseamos el salario a double

        String consultaInsert = "INSERT INTO personal(idpersonal, salario, puesto) VALUES (?,?,?)";
        PreparedStatement statement = conexion.prepareStatement(consultaInsert);
        statement.setString(1, identidad);
        statement.setDouble(2, salario);
        statement.setString(3, puesto);


        int filasInsertadas = statement.executeUpdate();

        if (filasInsertadas > 0) {
            JOptionPane.showMessageDialog(this, "Datos insertados correctamente");
            
                // Limpiar los campos después de la inserción exitosa
                limpiarCampos();
                // Mostrar los datos actualizados en la tabla de personal
                mostrarTablaPersonal();
        } else {
            JOptionPane.showMessageDialog(this, "Error al insertar datos");
        }
        statement.close();
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Error: El salario debe ser un valor numérico válido.");
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al insertar datos: " + ex.getMessage());
    }
  }
}

public void limpiarCampos() {
    txtID1.setText("");
    txtnombre1.setText("");
    txtcorreo1.setText("");
    txtcontacto1.setText("");
    txtdireccion1.setText("");
    txtsalarios1.setText("");
    cbmPuesto.setSelectedIndex(0);
}



    
public void mostrarTablaCliente() {
    try {
        String consultaSQL = "SELECT idcliente, nombre, correo, contacto, direccion " +
                             "FROM cliente " +
                              "JOIN persona ON persona.idpersona = cliente.idcliente";

        PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        ResultSet resultSet = statement.executeQuery();

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Identidad");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Correo");
        modeloTabla.addColumn("Contacto");
        modeloTabla.addColumn("Direccion");

        while (resultSet.next()) {
            Object[] fila = {
                resultSet.getString("idcliente"),
                resultSet.getString("nombre"),
                resultSet.getString("correo"),
                resultSet.getString("contacto"),
                resultSet.getString("direccion")
            };
            modeloTabla.addRow(fila);
        }

        Tabla.setModel(modeloTabla);
        statement.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
    }
}




 
 public void BuscarCliente() {
    try {
        String identidad = txtID1.getText();
        String consultaSQL = "SELECT idcliente, nombre, correo, contacto, direccion " +
                             "FROM cliente " +
                             "JOIN persona ON cliente.idcliente = persona.idpersona " +
                             "WHERE  cliente.idcliente = ?";
        PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        statement.setString(1, identidad);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            txtID1.setText(resultSet.getString("idcliente"));
            txtnombre1.setText(resultSet.getString("nombre"));
            txtcorreo1.setText(resultSet.getString("correo"));
            txtcontacto1.setText(resultSet.getString("contacto"));
            txtdireccion1.setText(resultSet.getString("direccion"));
            

        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron resultados para la identidad proporcionada.");
        }
        statement.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
    }
}
 
 public void BuscarPersonal(){
     try{
         String identidad = txtID1.getText();
         String consultaSQL ="SELECT idpersonal, nombre, correo, contacto, salario, puesto " +
                             "FROM personal " +
                             "JOIN persona ON personal.idpersonal = persona.idpersona " +
                             "WHERE personal.idpersonal = ?"; 

          PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        statement.setString(1, identidad);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            txtID1.setText(resultSet.getString("idpersonal"));
            txtnombre1.setText(resultSet.getString("nombre"));
            txtcorreo1.setText(resultSet.getString("correo"));
            txtcontacto1.setText(resultSet.getString("contacto"));
            txtsalarios1.setText(resultSet.getString("salario"));
            cbmPuesto.setSelectedItem(resultSet.getString("puesto"));

        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron resultados para la identidad proporcionada.");
        }
        statement.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
    }
}

  
public void mostrarTablaPersonal() {
    try {
        String consultaSQL = "SELECT idpersona, nombre, correo, contacto, salario, puesto " +
                             "FROM personal " +
                             "JOIN persona ON personal.idpersonal = persona.idpersona"; // Cambiado id_persona a idpersona
        PreparedStatement statement = conexion.prepareStatement(consultaSQL);
        ResultSet resultSet = statement.executeQuery();

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Identidad");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Correo");
        modeloTabla.addColumn("Contacto");
        modeloTabla.addColumn("Salario");
        modeloTabla.addColumn("Puesto");

        while (resultSet.next()) {
            Object[] fila = {
                resultSet.getString("idpersona"),
                resultSet.getString("nombre"),
                resultSet.getString("correo"),
                resultSet.getString("contacto"),
                resultSet.getString("salario"),
                resultSet.getString("puesto")
            };
            modeloTabla.addRow(fila);
        }

        Tabla.setModel(modeloTabla);
        statement.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al ejecutar consulta: " + ex.getMessage());
    }
}


public void modificardatos() {
    String identidad = txtID1.getText();

    if (cbmPersona.getSelectedIndex() == 0) {
        // Actualizar datos de cliente
        String direccion = txtdireccion1.getText();
        try {
            String consultaUpdate = "UPDATE cliente SET direccion = ? WHERE idcliente = ?";
            PreparedStatement statement = conexion.prepareStatement(consultaUpdate);
            statement.setString(1, direccion);
            statement.setString(2, identidad);

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(this, "Datos del cliente actualizados correctamente");
                txtdireccion1.setText("");
                mostrarTablaCliente();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningun cliente con esta identidad proporcionada");
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar datos de cliente: " + ex.getMessage());
        }
    } else if (cbmPersona.getSelectedIndex() == 1) {
        // Actualizar datos de personal
        String puesto = cbmPuesto.getSelectedItem().toString();
        

        try {
            String consultaUpdate = "UPDATE personal SET puesto = ? WHERE idpersonal = ?";
            PreparedStatement statement = conexion.prepareStatement(consultaUpdate);
            statement.setString(1, puesto);
            statement.setString(2, identidad);

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(this, "Datos de personal actualizados correctamente");
                txtID1.setText("");
                mostrarTablaPersonal();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún personal con la identidad proporcionada");
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar datos de personal: " + ex.getMessage());
        }
    
    }
    
}


public void deleteperson(){
    String identidad  = txtID1.getText();
    try{
        String consultaDelete = "DELETE FROM persona WHERE idpersona = ?";
        PreparedStatement statement = conexion.prepareStatement(consultaDelete);
        statement.setString(1, identidad);
        
        int filasEliminadas = statement.executeUpdate();

        if (filasEliminadas > 0) {
            txtID1.setText("");
            txtnombre1.setText("");
            txtcorreo1.setText("");
            txtcontacto1.setText("");
        }
       statement.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al eliminar persona: " + ex.getMessage());
    }
}
public void eliminarPersonal(){
    String identidad = txtID1.getText();
    
    if(cbmPersona.getSelectedIndex() == 0){
        try{
            String consultaDelete = "DELETE FROM cliente Where idcliente = ?";
            PreparedStatement statement = conexion.prepareStatement(consultaDelete);
            statement.setString(1, identidad);
            
            int filasEliminadas = statement.executeUpdate();
            
             if (filasEliminadas > 0) {
                deleteperson();
                JOptionPane.showMessageDialog(this, "Cliente eliminada correctamente");
                txtID1.setText("");
                txtdireccion1.setText("");
                mostrarTablaCliente();
        }else {
                 JOptionPane.showMessageDialog(this, "No se encontró ninguna cliente con el la identidad proporcionada"); 
             }
               statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar cliente: " + ex.getMessage());
        }
    } else if (cbmPersona.getSelectedIndex() == 1){
              try{
            String consultaDelete = "DELETE FROM personal Where idpersonal = ?";
            PreparedStatement statement = conexion.prepareStatement(consultaDelete);
            statement.setString(1, identidad);
            
            int filasEliminadas = statement.executeUpdate();
            
             if (filasEliminadas > 0) {
                deleteperson();
                JOptionPane.showMessageDialog(this, "Personal eliminada correctamente");
                txtID1.setText("");
                txtsalarios1.setText("");
                cbmPuesto.setSelectedIndex(-1);
                mostrarTablaPersonal();
        }else {
                 JOptionPane.showMessageDialog(this, "No se encontró ninguna personal con el la identidad proporcionada"); 
             }
               statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar personal: " + ex.getMessage());
        }  
    }
}



    

         


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtcontacto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        txtsalarios = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtpuesto = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lbIdentidad = new javax.swing.JLabel();
        txtID1 = new javax.swing.JTextField();
        txtnombre1 = new javax.swing.JTextField();
        lbNombre = new javax.swing.JLabel();
        lbcorreo = new javax.swing.JLabel();
        txtcorreo1 = new javax.swing.JTextField();
        lbcontacto = new javax.swing.JLabel();
        txtcontacto1 = new javax.swing.JTextField();
        lbdireccion = new javax.swing.JLabel();
        txtdireccion1 = new javax.swing.JTextField();
        txtsalarios1 = new javax.swing.JTextField();
        lbsalario = new javax.swing.JLabel();
        lbpuesto = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        cbmPersona = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cbmPuesto = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        JBmodificar = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Identidad:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Nombre:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Correo:");
        jLabel6.setToolTipText("");

        txtcorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcorreoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Contacto:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Direccion:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Salarios:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Puesto:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbIdentidad.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbIdentidad.setForeground(new java.awt.Color(51, 51, 51));
        lbIdentidad.setText("Identidad:");

        txtID1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtID1ActionPerformed(evt);
            }
        });

        lbNombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbNombre.setForeground(new java.awt.Color(51, 51, 51));
        lbNombre.setText("Nombre:");

        lbcorreo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbcorreo.setForeground(new java.awt.Color(51, 51, 51));
        lbcorreo.setText("Correo:");
        lbcorreo.setToolTipText("");

        txtcorreo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcorreo1ActionPerformed(evt);
            }
        });

        lbcontacto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbcontacto.setForeground(new java.awt.Color(51, 51, 51));
        lbcontacto.setText("Contacto:");

        txtcontacto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcontacto1ActionPerformed(evt);
            }
        });

        lbdireccion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbdireccion.setForeground(new java.awt.Color(51, 51, 51));
        lbdireccion.setText("Direccion:");

        txtdireccion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdireccion1ActionPerformed(evt);
            }
        });

        lbsalario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbsalario.setForeground(new java.awt.Color(51, 51, 51));
        lbsalario.setText("Salarios:");

        lbpuesto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbpuesto.setForeground(new java.awt.Color(51, 51, 51));
        lbpuesto.setText("Puesto:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Identidad", "Nombre", "Correo", "Contacto", "Direccion", "Salario", "Puesto"
            }
        ));
        jScrollPane1.setViewportView(Tabla);

        cbmPersona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente", "Personal" }));
        cbmPersona.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbmPersonaFocusLost(evt);
            }
        });
        cbmPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmPersonaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Persona");

        cbmPuesto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Barista", "Camarero/a", "Cajero/a", "Cocinero/a", "Auxiliar de limpieza", "Gerente de Cafetería", "Repostero/a", "Barista especializado/a", "Encargado/a de compras", "Encargado/a de marketing", "Encargado/a de recursos humanos", "Encargado/a de eventos", "Barista supervisor/a", "Recepcionista" }));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        JBmodificar.setText("Modificar");
        JBmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBmodificarActionPerformed(evt);
            }
        });

        btndelete.setText("Eliminar");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbcontacto)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lbIdentidad, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbcorreo, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtID1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtcorreo1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtcontacto1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(91, 91, 91)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btndelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(JBmodificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lbsalario))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtsalarios1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdireccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbmPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbmPuesto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(305, 305, 305)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcorreo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbcontacto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcontacto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBmodificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndelete)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbmPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdireccion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbsalario, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtsalarios1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbmPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcorreoActionPerformed

    }//GEN-LAST:event_txtcorreoActionPerformed

    private void txtcorreo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcorreo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcorreo1ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
          insertarDatos1();
          insertDates();

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtcontacto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcontacto1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcontacto1ActionPerformed

    private void cbmPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmPersonaActionPerformed
        
      if(cbmPersona.getSelectedIndex() == 0){
                   mostrarTablaCliente(); 
                 }else {
                    mostrarTablaPersonal(); 
                 }
        visible();
    }//GEN-LAST:event_cbmPersonaActionPerformed

    private void cbmPersonaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbmPersonaFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmPersonaFocusLost

    private void txtID1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtID1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtID1ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if(cbmPersona.getSelectedIndex() == 0){
            BuscarCliente();
        }else if (cbmPersona.getSelectedIndex() == 1){
        BuscarPersonal();
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void JBmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBmodificarActionPerformed
      modificardatos();
    }//GEN-LAST:event_JBmodificarActionPerformed

    private void txtdireccion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireccion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccion1ActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        eliminarPersonal();
    }//GEN-LAST:event_btndeleteActionPerformed

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
            java.util.logging.Logger.getLogger(Person.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Person.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Person.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Person.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Person().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBmodificar;
    private javax.swing.JTable Tabla;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btndelete;
    private javax.swing.JComboBox<String> cbmPersona;
    private javax.swing.JComboBox<String> cbmPuesto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbIdentidad;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbcontacto;
    private javax.swing.JLabel lbcorreo;
    private javax.swing.JLabel lbdireccion;
    private javax.swing.JLabel lbpuesto;
    private javax.swing.JLabel lbsalario;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtID1;
    private javax.swing.JTextField txtcontacto;
    private javax.swing.JTextField txtcontacto1;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtcorreo1;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtdireccion1;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnombre1;
    private javax.swing.JTextField txtpuesto;
    private javax.swing.JTextField txtsalarios;
    private javax.swing.JTextField txtsalarios1;
    // End of variables declaration//GEN-END:variables
}

    

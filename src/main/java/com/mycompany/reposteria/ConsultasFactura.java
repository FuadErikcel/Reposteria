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
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Bryan Aguiluz
 */
public class ConsultasFactura extends javax.swing.JFrame {
    private java.sql.Date fechaInicioSQL;
    /**
     * Creates new form Consultas
     */
    public ConsultasFactura() {
        initComponents();
        CbxConsultas.setSelectedIndex(0);
        CbxConsultas.revalidate();
        mostrarDatos();
        Visible();
    }
    Conexion conexionObjeto = new Conexion();
    Connection conexion = conexionObjeto.getConexion();
    
    private void mostrarDatos() {
        try {
            String consultaSQL = "SELECT factura.idFactura, " +
                                 "factura.FechaVenta, " +
                                 "COALESCE(persona.nombre, 'N/A') AS nombre_cliente, " +
                                 "SUM(facturaProducto.cantidad * producto.precioProducto) AS total " +
                                 "FROM factura " +
                                 "LEFT JOIN facturaCliente ON factura.idFactura = facturaCliente.idFacturaC " +
                                 "LEFT JOIN persona ON facturaCliente.idClienteF = persona.idPersona " +
                                 "INNER JOIN facturaProducto ON factura.idFactura = facturaProducto.idFacturaP " +
                                 "INNER JOIN producto ON facturaProducto.idProductoF = producto.idProducto " +
                                 "GROUP BY factura.idFactura, factura.FechaVenta, nombre_cliente";

            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            ResultSet resultSet = statement.executeQuery();

            double totalSuma = 0;
            
            // Limpiar tabla
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("ID");
            modelo.addColumn("Fecha Venta");
            modelo.addColumn("Nombre Cliente");
            modelo.addColumn("Total");

            while (resultSet.next()) {
                Object[] fila = {
                    resultSet.getString("idFactura"),
                    resultSet.getString("FechaVenta"),
                    resultSet.getString("nombre_cliente"),
                    resultSet.getDouble("total")
                };
                modelo.addRow(fila);
                totalSuma += resultSet.getDouble("total");
            }
            Tabla.setModel(modelo);
            TotalTxt.setText(Double.toString(totalSuma));
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private void mostrarFacturasPorFecha() {
        try {
            java.util.Date fechaInicioSeleccionada = fechaInicio.getDate();
            java.util.Date fechaFinSeleccionada = fechaFin.getDate();

            if (fechaInicioSeleccionada == null || fechaFinSeleccionada == null) {
                return;
            }

            java.sql.Date fechaInicioSQL = new java.sql.Date(fechaInicioSeleccionada.getTime());
            java.sql.Date fechaFinSQL = new java.sql.Date(fechaFinSeleccionada.getTime());

            String consultaSQL = "SELECT factura.idFactura, factura.FechaVenta, " + 
                                 "CASE " +
                                 "WHEN facturaCliente.idClienteF IS NOT NULL THEN COALESCE(persona.nombre, 'N/A') " +
                                 " ELSE 'N/A' " +
                                 "END AS nombre_cliente, " +
                                 "SUM(facturaProducto.cantidad * producto.precioProducto) AS total " +
                                 "FROM factura " +
                                 "INNER JOIN facturaProducto ON factura.idFactura = facturaProducto.idFacturaP " +
                                 "INNER JOIN producto ON facturaProducto.idProductoF = producto.idProducto " +
                                 "LEFT JOIN facturaCliente ON factura.idFactura = facturaCliente.idFacturaC " +
                                 "LEFT JOIN persona ON facturaCliente.idClienteF = persona.idPersona " +
                                 "WHERE factura.FechaVenta BETWEEN ? AND ? " +
                                 "GROUP BY factura.idFactura, factura.FechaVenta, nombre_cliente";
            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            statement.setDate(1, fechaInicioSQL);
            statement.setDate(2, fechaFinSQL);
            ResultSet resultSet = statement.executeQuery();

            // Limpiar tabla
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("ID");
            modelo.addColumn("Fecha Venta");
            modelo.addColumn("Cliete");
            modelo.addColumn("Total");
            
            double totalSuma = 0;
            
            // Llenar tabla con resultados de la consulta
            while (resultSet.next()) {
                Object[] fila = {
                    resultSet.getString("idfactura"),
                    resultSet.getString("fechaventa"),
                    resultSet.getString("nombre_cliente"),
                    resultSet.getString("total")
                };
                modelo.addRow(fila);
                totalSuma += resultSet.getDouble("total");
            }
            Tabla.setModel(modelo);
            TotalTxt.setText(Double.toString(totalSuma));
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void mostrarFacturasPorMes() {
        try {
            int mesSeleccionado = jMes.getMonth() + 1;// Sumamos 1 porque los meses en Java comienzan desde 0
            int añoSeleccionado = jAño.getYear();
            
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, añoSeleccionado);
            cal.set(Calendar.MONTH, mesSeleccionado - 1);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            java.sql.Date fechaInicioSQL = new java.sql.Date(cal.getTimeInMillis());
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH)); // Último día del mes
            java.sql.Date fechaFinSQL = new java.sql.Date(cal.getTimeInMillis());
            
            String consulta = "SELECT factura.idFactura, " +
                              "factura.FechaVenta, " +
                              "COALESCE(persona.nombre, 'N/A') AS nombre_cliente, " +
                              "SUM(facturaProducto.cantidad * producto.precioProducto) AS total " +
                              "FROM factura " +
                              "LEFT JOIN facturaCliente ON factura.idFactura = facturaCliente.idFacturaC " +
                              "LEFT JOIN persona ON facturaCliente.idClienteF = persona.idPersona " +
                              "INNER JOIN facturaProducto ON factura.idFactura = facturaProducto.idFacturaP " +
                              "INNER JOIN producto ON facturaProducto.idProductoF = producto.idProducto " +
                              "WHERE EXTRACT(YEAR FROM factura.FechaVenta) = ? " +
                              "AND EXTRACT(MONTH FROM factura.FechaVenta) = ? " +
                              "GROUP BY factura.idFactura, factura.FechaVenta, nombre_cliente";
            
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, añoSeleccionado);
            statement.setInt(2, mesSeleccionado);
            ResultSet resultSet = statement.executeQuery();
            
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("ID");
            modelo.addColumn("Fecha Venta");
            modelo.addColumn("Nombre Cliente");
            modelo.addColumn("Total");
            
            double totalSuma = 0;
            
            while (resultSet.next()) {
                Object[] fila = {
                    resultSet.getString("idfactura"),
                    resultSet.getString("fechaventa"),
                    resultSet.getString("nombre_cliente"),
                    resultSet.getDouble("total")
                };
                modelo.addRow(fila);
                totalSuma += resultSet.getDouble("total");
            }
            Tabla.setModel(modelo);
            TotalTxt.setText(Double.toString(totalSuma));
            statement.close();
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void mostrarFacturasPorAño() {
        try {
            int añoSeleccionado = jAño.getYear();

            // Crear un objeto Calendar con el año seleccionado y el primer día del año
            Calendar cal = Calendar.getInstance();
            cal.clear();
            cal.set(Calendar.YEAR, añoSeleccionado);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DAY_OF_MONTH, 1);

            // Convertir la fecha del primer día del año a un objeto Date SQL
            java.sql.Date fechaInicioSQL = new java.sql.Date(cal.getTimeInMillis());

            // Avanzar al último día del año
            cal.add(Calendar.YEAR, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);

            // Convertir la fecha del último día del año a un objeto Date SQL
            java.sql.Date fechaFinSQL = new java.sql.Date(cal.getTimeInMillis());

            // Consulta SQL para seleccionar facturas en el rango de fechas del año
            String consulta = "SELECT factura.idFactura, " +
                              "factura.FechaVenta, " +
                              "COALESCE(persona.nombre, 'N/A') AS nombre_cliente, " +
                              "SUM(facturaProducto.cantidad * producto.precioProducto) AS total " +
                              "FROM factura " +
                              "LEFT JOIN facturaCliente ON factura.idFactura = facturaCliente.idFacturaC " +
                              "LEFT JOIN persona ON facturaCliente.idClienteF = persona.idPersona " +
                              "INNER JOIN facturaProducto ON factura.idFactura = facturaProducto.idFacturaP " +
                              "INNER JOIN producto ON facturaProducto.idProductoF = producto.idProducto " +
                              "WHERE factura.FechaVenta BETWEEN ? AND ? " +
                              "GROUP BY factura.idFactura, factura.FechaVenta, nombre_cliente";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setDate(1, fechaInicioSQL);
            statement.setDate(2, fechaFinSQL);
            ResultSet resultSet = statement.executeQuery();

            // Limpiar tabla
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("ID");
            modelo.addColumn("Fecha Venta");
            modelo.addColumn("Nombre Cliente");
            modelo.addColumn("Total");

            double totalSuma = 0;

            // Llenar tabla con resultados de la consulta
            while (resultSet.next()) {
                Object[] fila = {
                    resultSet.getString("idfactura"),
                    resultSet.getString("fechaventa"),
                    resultSet.getString("nombre_cliente"),
                    resultSet.getDouble("total")
                };
                modelo.addRow(fila);
                totalSuma += resultSet.getDouble("total");
            }
            Tabla.setModel(modelo);
            TotalTxt.setText(Double.toString(totalSuma)); // Establecer el total en el JTextField TotalTxt
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    private void buscarFacturasPorCliente() {
        try {
            String nombreCliente = txtCliente.getText();

            String consultaSQL = "SELECT factura.idFactura, " +
                                 "persona.nombre AS nombreCliente, " +
                                 "factura.FechaVenta, " +
                                 "SUM(facturaProducto.cantidad * producto.precioProducto) AS total " +
                                 "FROM factura " +
                                 "INNER JOIN facturaCliente ON factura.idFactura = facturaCliente.idFacturaC " +
                                 "INNER JOIN persona ON facturaCliente.idClienteF = persona.idPersona " +
                                 "INNER JOIN facturaProducto ON factura.idFactura = facturaProducto.idFacturaP " +
                                 "INNER JOIN producto ON facturaProducto.idProductoF = producto.idProducto " +
                                 "WHERE persona.nombre ILIKE ? " +
                                 "GROUP BY factura.idFactura, persona.nombre, factura.FechaVenta";

            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            statement.setString(1, "%" + nombreCliente + "%");
            ResultSet resultSet = statement.executeQuery();

            // Limpiar tabla
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("ID Factura");
            modelo.addColumn("Nombre Cliente");
            modelo.addColumn("Fecha Venta");
            modelo.addColumn("Total");

            double totalSuma = 0;

            while (resultSet.next()) {
                Object[] fila = {
                    resultSet.getString("idFactura"),
                    resultSet.getString("nombreCliente"),
                    resultSet.getString("FechaVenta"),
                    resultSet.getDouble("total")
                };
                modelo.addRow(fila);
                totalSuma += resultSet.getDouble("total");
            }
            Tabla.setModel(modelo);
            TotalTxt.setText(Double.toString(totalSuma)); // Establecer el total en el JTextField TotalTxt
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private void buscarFacturasPorFactura() {
        try {
            String idFactura = txtFactura.getText();

            String consultaSQL = "SELECT factura.idFactura, " +
                                 "COALESCE(persona.nombre, 'N/A') AS nombreCliente, " +
                                 "factura.FechaVenta, " +
                                 "SUM(facturaProducto.cantidad * producto.precioProducto) AS total " +
                                 "FROM factura " +
                                 "LEFT JOIN facturaCliente ON factura.idFactura = facturaCliente.idFacturaC " +
                                 "LEFT JOIN persona ON facturaCliente.idClienteF = persona.idPersona " +
                                 "INNER JOIN facturaProducto ON factura.idFactura = facturaProducto.idFacturaP " +
                                 "INNER JOIN producto ON facturaProducto.idProductoF = producto.idProducto " +
                                 "WHERE factura.idFactura ILIKE ? " +
                                 "GROUP BY factura.idFactura, persona.nombre, factura.FechaVenta";

            PreparedStatement statement = conexion.prepareStatement(consultaSQL);
            statement.setString(1, "%" + idFactura + "%");
            ResultSet resultSet = statement.executeQuery();

            // Limpiar tabla
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("ID Factura");
            modelo.addColumn("Nombre Cliente");
            modelo.addColumn("Fecha Venta");
            modelo.addColumn("Total");

            double totalSuma = 0;

            while (resultSet.next()) {
                Object[] fila = {
                    resultSet.getString("idFactura"),
                    resultSet.getString("nombreCliente"),
                    resultSet.getString("FechaVenta"),
                    resultSet.getDouble("total")
                };
                modelo.addRow(fila);
                totalSuma += resultSet.getDouble("total");
            }
            Tabla.setModel(modelo);
            TotalTxt.setText(Double.toString(totalSuma)); // Establecer el total en el JTextField TotalTxt
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    
    public void Visible() {
        if (CbxConsultas.getSelectedIndex() == 0) {
            lblInicio.setVisible(true);
            lblFin.setVisible(true);
            fechaInicio.setVisible(true);
            fechaFin.setVisible(true);
            lblMes.setVisible(false);
            jMes.setVisible(false);
            lblAño.setVisible(false);
            jAño.setVisible(false);
            lblCliente.setVisible(false);
            txtCliente.setVisible(false);
            lblFactura.setVisible(false);
            txtFactura.setVisible(false);
        } else if (CbxConsultas.getSelectedIndex() == 1) {
            lblInicio.setVisible(false);
            lblFin.setVisible(false);
            fechaInicio.setVisible(false);
            fechaFin.setVisible(false);
            lblMes.setVisible(true);
            jMes.setVisible(true);
            lblAño.setVisible(true);
            jAño.setVisible(true);
            lblCliente.setVisible(false);
            txtCliente.setVisible(false);
            lblFactura.setVisible(false);
            txtFactura.setVisible(false);
        }else if (CbxConsultas.getSelectedIndex() == 2) {
            lblInicio.setVisible(false);
            lblFin.setVisible(false);
            fechaInicio.setVisible(false);
            fechaFin.setVisible(false);
            lblMes.setVisible(false);
            jMes.setVisible(false);
            lblAño.setVisible(true);
            jAño.setVisible(true);
            lblCliente.setVisible(false);
            txtCliente.setVisible(false);
            lblFactura.setVisible(false);
            txtFactura.setVisible(false);
        }else if (CbxConsultas.getSelectedIndex() == 3) {
            lblInicio.setVisible(false);
            lblFin.setVisible(false);
            fechaInicio.setVisible(false);
            fechaFin.setVisible(false);
            lblMes.setVisible(false);
            jMes.setVisible(false);
            lblAño.setVisible(false);
            jAño.setVisible(false);
            lblCliente.setVisible(true);
            txtCliente.setVisible(true);
            lblFactura.setVisible(false);
            txtFactura.setVisible(false);
        }
        else if (CbxConsultas.getSelectedIndex() == 4) {
            lblInicio.setVisible(false);
            lblFin.setVisible(false);
            fechaInicio.setVisible(false);
            fechaFin.setVisible(false);
            lblMes.setVisible(false);
            jMes.setVisible(false);
            lblAño.setVisible(false);
            jAño.setVisible(false);
            lblCliente.setVisible(false);
            txtCliente.setVisible(false);
            lblFactura.setVisible(true);
            txtFactura.setVisible(true);
        }
    }
    
    private void ordenarDatosTabla(boolean ascendente) {
        DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
        List<Comparable[]> datos = new ArrayList<>();

        // Obtener los datos de la tabla
        for (int i = 0; i < modelo.getRowCount(); i++) {
            Comparable[] fila = new Comparable[modelo.getColumnCount()];
            for (int j = 0; j < modelo.getColumnCount(); j++) {
                fila[j] = (Comparable) modelo.getValueAt(i, j);
            }
            datos.add(fila);
        }

        // Ordenar los datos por la columna de fecha de venta
        Collections.sort(datos, (fila1, fila2) -> {
            Comparable fecha1 = fila1[modelo.findColumn("Fecha Venta")];
            Comparable fecha2 = fila2[modelo.findColumn("Fecha Venta")];
            return ascendente ? fecha1.compareTo(fecha2) : fecha2.compareTo(fecha1);
        });

        // Actualizar el modelo de la tabla con los datos ordenados
        modelo.setRowCount(0);
        for (Comparable[] fila : datos) {
            modelo.addRow(fila);
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
        CbxFiltroOrden = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        CbxConsultas = new javax.swing.JComboBox<>();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        fechaFin = new com.toedter.calendar.JDateChooser();
        lblInicio = new javax.swing.JLabel();
        lblFin = new javax.swing.JLabel();
        botonBuscar = new javax.swing.JButton();
        jMes = new com.toedter.calendar.JMonthChooser();
        lblMes = new javax.swing.JLabel();
        lblAño = new javax.swing.JLabel();
        jAño = new com.toedter.calendar.JYearChooser();
        lblCliente = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        lblFactura = new javax.swing.JLabel();
        txtFactura = new javax.swing.JTextField();
        TotalTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Eras Bold ITC", 0, 18)); // NOI18N
        jLabel1.setText("Consulta Facturas");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 210, 30));

        CbxFiltroOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascendente", "Descendente" }));
        CbxFiltroOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxFiltroOrdenActionPerformed(evt);
            }
        });
        jPanel1.add(CbxFiltroOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 150, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Orden");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 150, -1));

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(Tabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 640, 320));

        CbxConsultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fecha", "Mes", "Año", "Cliente", "Factura" }));
        CbxConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxConsultasActionPerformed(evt);
            }
        });
        CbxConsultas.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                CbxConsultasPropertyChange(evt);
            }
        });
        jPanel1.add(CbxConsultas, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 150, -1));
        jPanel1.add(fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 130, -1));

        fechaFin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fechaFinPropertyChange(evt);
            }
        });
        jPanel1.add(fechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 130, -1));

        lblInicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblInicio.setText("Fecha Inicio");
        jPanel1.add(lblInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, -1));

        lblFin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFin.setText("Fecha Fin");
        jPanel1.add(lblFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, -1, -1));

        botonBuscar.setText("Buscar");
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(botonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 80, -1));
        jPanel1.add(jMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, -1, -1));

        lblMes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblMes.setText("Mes:");
        jPanel1.add(lblMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));

        lblAño.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAño.setText("Año:");
        jPanel1.add(lblAño, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, -1, -1));
        jPanel1.add(jAño, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 80, -1));

        lblCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCliente.setText("Nombre Cliente:");
        jPanel1.add(lblCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, -1, -1));
        jPanel1.add(txtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, 160, -1));

        lblFactura.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFactura.setText("Factura:");
        jPanel1.add(lblFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, -1, -1));
        jPanel1.add(txtFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 160, -1));

        TotalTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalTxtActionPerformed(evt);
            }
        });
        jPanel1.add(TotalTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 560, 100, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Total:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 560, -1, -1));

        jButton1.setText("Menu Principal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CbxFiltroOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxFiltroOrdenActionPerformed
        String filtroOrden = (String) CbxFiltroOrden.getSelectedItem();
        if(filtroOrden != null) {
            switch (filtroOrden) {
                case "Descendente":
                    ordenarDatosTabla(false);
                    break;
                case "Ascendente":
                    ordenarDatosTabla(true);
                    break;
                default:
                    break;
            }
        }
    }//GEN-LAST:event_CbxFiltroOrdenActionPerformed

    private void CbxConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxConsultasActionPerformed
        Visible();
    }//GEN-LAST:event_CbxConsultasActionPerformed

    private void CbxConsultasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_CbxConsultasPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_CbxConsultasPropertyChange

    private void fechaFinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fechaFinPropertyChange
        
    }//GEN-LAST:event_fechaFinPropertyChange

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        if(CbxConsultas.getSelectedIndex() == 0){
            mostrarFacturasPorFecha();
        }else if(CbxConsultas.getSelectedIndex() == 1){
            mostrarFacturasPorMes();
        }else if(CbxConsultas.getSelectedIndex() == 2){
            mostrarFacturasPorAño();
        }else if(CbxConsultas.getSelectedIndex() == 3){
            buscarFacturasPorCliente();
        }else if(CbxConsultas.getSelectedIndex() == 4){
            buscarFacturasPorFactura();
        }
        
    }//GEN-LAST:event_botonBuscarActionPerformed

    private void TotalTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalTxtActionPerformed

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
            java.util.logging.Logger.getLogger(ConsultasFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultasFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultasFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultasFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultasFactura().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CbxConsultas;
    private javax.swing.JComboBox<String> CbxFiltroOrden;
    private javax.swing.JTable Tabla;
    private javax.swing.JTextField TotalTxt;
    private javax.swing.JButton botonBuscar;
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private com.toedter.calendar.JYearChooser jAño;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private com.toedter.calendar.JMonthChooser jMes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAño;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblFactura;
    private javax.swing.JLabel lblFin;
    private javax.swing.JLabel lblInicio;
    private javax.swing.JLabel lblMes;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtFactura;
    // End of variables declaration//GEN-END:variables
}

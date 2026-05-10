package com.null_terminators.proyectofinal.view;

import com.null_terminators.proyectofinal.controller.UsuarioController;
import com.null_terminators.proyectofinal.model.Empleado;
import javax.swing.JOptionPane;

/**
 * Formulario de Gestion de Empleados (Version Final Corregida)
 * 
 * @author rufernecall
 */
public class EmpleadoForm extends javax.swing.JDialog {

    private UsuarioController controller;
    private Empleado empleadoActual;
    private boolean guardado = false;

    public EmpleadoForm(java.awt.Frame parent, boolean modal, Empleado e) {
        super(parent, modal);
        this.controller = new UsuarioController();
        this.empleadoActual = e;
        initComponents();
        if (e != null)
            cargarDatos();
        setLocationRelativeTo(parent);
    }

    private void cargarDatos() {
        txtNombre.setText(empleadoActual.getNombre());
        txtDni.setText(empleadoActual.getDni());
        txtCargo.setText(empleadoActual.getCargo());
        txtTelefono.setText(empleadoActual.getTelefono());
        txtSueldo.setText(String.valueOf(empleadoActual.getSueldo()));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCargo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSueldo = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion de Empleado");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18));
        lblTitulo.setForeground(new java.awt.Color(40, 167, 69));
        lblTitulo.setText("DATOS DEL EMPLEADO");

        jLabel2.setText("Nombre:");
        jLabel3.setText("Cargo:");
        jLabel4.setText("Telefono:");
        jLabel6.setText("DNI:");
        jLabel5.setText("Sueldo:");

        btnGuardar.setBackground(new java.awt.Color(40, 167, 69));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12));
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(evt -> btnGuardarActionPerformed(evt));

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(evt -> btnCancelarActionPerformed(evt));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(btnGuardar,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 110,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnCancelar,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 90,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel2)
                                                                        .addComponent(jLabel3)
                                                                        .addComponent(jLabel4)
                                                                        .addComponent(jLabel6)
                                                                        .addComponent(jLabel5))
                                                                .addGap(30, 30, 30)
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(txtSueldo,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                250,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(txtDni,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                250,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(txtTelefono,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                250,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(txtCargo,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                250,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(txtNombre,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                250,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addContainerGap(30, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblTitulo)
                                                .addGap(0, 0, Short.MAX_VALUE)))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(lblTitulo)
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(txtSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35,
                                        Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)));

        pack();
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        if (empleadoActual == null)
            empleadoActual = new Empleado();
        empleadoActual.setNombre(txtNombre.getText());
        empleadoActual.setDni(txtDni.getText());
        empleadoActual.setTelefono(txtTelefono.getText());
        empleadoActual.setCargo(txtCargo.getText());
        try {
            empleadoActual.setSueldo(Double.parseDouble(txtSueldo.getText()));
        } catch (NumberFormatException ex) {
            empleadoActual.setSueldo(0.0);
        }

        if (controller.guardarEmpleado(empleadoActual)) {
            guardado = true;
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar empleado.");
        }
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    public boolean isGuardado() {
        return guardado;
    }

    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSueldo;
    private javax.swing.JTextField txtTelefono;
}

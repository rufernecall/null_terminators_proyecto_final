package com.null_terminators.proyectofinal.view;

import com.null_terminators.proyectofinal.controller.ComercialController;
import com.null_terminators.proyectofinal.model.Cliente;
import javax.swing.JOptionPane;

/**
 * Formulario de Gestion de Clientes vinculado al Controlador
 * 
 * @author rufernecall
 */
public class ClienteForm extends javax.swing.JDialog {

        private ComercialController controller;
        private Cliente clienteActual;
        private boolean guardado = false;

        public ClienteForm(java.awt.Frame parent, boolean modal, Cliente c) {
                super(parent, modal);
                this.controller = new ComercialController();
                this.clienteActual = c;
                initComponents();
                if (c != null)
                        cargarDatos();
                setLocationRelativeTo(parent);
        }

        private void cargarDatos() {
                txtDocumento.setText(clienteActual.getDocumentoIdentidad());
                txtEmail.setText(clienteActual.getEmail());
                txtTelefono.setText(clienteActual.getTelefono());
                txtDireccion.setText(clienteActual.getDireccion());
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                lblTitulo = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                txtNombre = new javax.swing.JTextField();
                jLabel3 = new javax.swing.JLabel();
                txtDocumento = new javax.swing.JTextField();
                jLabel4 = new javax.swing.JLabel();
                txtTelefono = new javax.swing.JTextField();
                jLabel5 = new javax.swing.JLabel();
                txtEmail = new javax.swing.JTextField();
                jLabel6 = new javax.swing.JLabel();
                txtDireccion = new javax.swing.JTextField();
                btnGuardar = new javax.swing.JButton();
                btnCancelar = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setTitle("Gestion de Cliente");

                lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                lblTitulo.setForeground(new java.awt.Color(50, 157, 246));
                lblTitulo.setText("DATOS DEL CLIENTE");

                jLabel2.setText("Nombre:");

                jLabel3.setText("DNI / RUC:");

                jLabel4.setText("Telefono:");

                jLabel5.setText("Email:");

                jLabel6.setText("Direccion:");

                btnGuardar.setBackground(new java.awt.Color(50, 157, 246));
                btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
                btnGuardar.setText("GUARDAR");
                btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnGuardarActionPerformed(evt);
                        }
                });

                btnCancelar.setText("CANCELAR");
                btnCancelar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnCancelarActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(30, 30, 30)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGroup(layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                .addComponent(btnGuardar,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                110,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addComponent(btnCancelar,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                90,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addComponent(jLabel2)
                                                                                                                                                .addComponent(jLabel3)
                                                                                                                                                .addComponent(jLabel4)
                                                                                                                                                .addComponent(jLabel5)
                                                                                                                                                .addComponent(jLabel6))
                                                                                                                                .addGap(30, 30, 30)
                                                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addComponent(txtDireccion,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                250,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(txtEmail,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                250,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(txtTelefono,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                250,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(txtDocumento,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                250,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addComponent(txtNombre,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                250,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                                                .addContainerGap(30,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(lblTitulo)
                                                                                                .addGap(0, 0, Short.MAX_VALUE)))));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(25, 25, 25)
                                                                .addComponent(lblTitulo)
                                                                .addGap(25, 25, 25)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel2)
                                                                                .addComponent(txtNombre,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(15, 15, 15)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel3)
                                                                                .addComponent(txtDocumento,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(15, 15, 15)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel4)
                                                                                .addComponent(txtTelefono,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(15, 15, 15)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel5)
                                                                                .addComponent(txtEmail,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(15, 15, 15)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel6)
                                                                                .addComponent(txtDireccion,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                35,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(btnGuardar,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                40,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnCancelar,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                40,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(30, 30, 30)));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnGuardarActionPerformed
                if (clienteActual == null)
                        clienteActual = new Cliente();
                clienteActual.setNombre(txtNombre.getText());
                clienteActual.setDocumentoIdentidad(txtDocumento.getText());
                clienteActual.setEmail(txtEmail.getText());
                clienteActual.setTelefono(txtTelefono.getText());
                clienteActual.setDireccion(txtDireccion.getText());

                if (controller.guardarCliente(clienteActual)) {
                        guardado = true;
                        dispose();
                } else {
                        JOptionPane.showMessageDialog(this, "Error al guardar cliente.");
                }
        }// GEN-LAST:event_btnGuardarActionPerformed

        private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelarActionPerformed
                dispose();
        }// GEN-LAST:event_btnCancelarActionPerformed

        public boolean isGuardado() {
                return guardado;
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnCancelar;
        private javax.swing.JButton btnGuardar;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel lblTitulo;
        private javax.swing.JTextField txtDireccion;
        private javax.swing.JTextField txtDocumento;
        private javax.swing.JTextField txtEmail;
        private javax.swing.JTextField txtNombre;
        private javax.swing.JTextField txtTelefono;
        // End of variables declaration//GEN-END:variables
}

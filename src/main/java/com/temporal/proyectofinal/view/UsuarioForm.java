package com.temporal.proyectofinal.view;

import com.temporal.proyectofinal.dao.UsuarioDAO;
import com.temporal.proyectofinal.model.Empleado;
import com.temporal.proyectofinal.model.Usuario;
import com.temporal.proyectofinal.util.DataStore;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Formulario de Usuarios compatible con NetBeans Designer
 * @author rufernecall
 */
public class UsuarioForm extends javax.swing.JDialog {

    private UsuarioDAO uDAO;
    private Usuario usuarioActual;
    private boolean guardado = false;

    public UsuarioForm(java.awt.Frame parent, boolean modal, Usuario u) {
        super(parent, modal);
        this.uDAO = new UsuarioDAO();
        this.usuarioActual = u;
        
        initComponents();
        cargarEmpleados();
        
        if (u != null) {
            lblTitulo.setText("EDITAR USUARIO");
            txtUser.setText(u.getUsername());
            txtPass.setText(u.getPassword());
            cbRol.setSelectedItem(u.getRol());
            if (u.getEmpleado() != null) {
                seleccionarEmpleado(u.getEmpleado().getId());
            }
        }
        
        setLocationRelativeTo(parent);
    }

    private void cargarEmpleados() {
        cbEmpleado.removeAllItems();
        Empleado ninguno = new Empleado();
        ninguno.setId(0L);
        ninguno.setNombre("--- SIN VINCULAR ---");
        cbEmpleado.addItem(ninguno);
        
        List<Empleado> lista = DataStore.getInstance().getEmpleados();
        for (Empleado e : lista) {
            cbEmpleado.addItem(e);
        }
    }

    private void seleccionarEmpleado(Long id) {
        for (int i = 0; i < cbEmpleado.getItemCount(); i++) {
            Empleado e = cbEmpleado.getItemAt(i);
            if (e != null && e.getId().equals(id)) {
                cbEmpleado.setSelectedIndex(i);
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        cbEmpleado = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbRol = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion de Usuario");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(50, 157, 246));
        lblTitulo.setText("DATOS DE ACCESO");

        jLabel2.setText("Usuario:");

        jLabel3.setText("Contraseña:");

        jLabel4.setText("Vincular Empleado:");

        jLabel5.setText("Rol Sistema:");

        cbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "VENDEDOR", "ALMACENERO", "GERENTE" }));

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbRol, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(30, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTitulo)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbRol, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String user = txtUser.getText().trim();
        String pass = new String(txtPass.getPassword());
        
        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.");
            return;
        }
        
        Empleado emp = (Empleado) cbEmpleado.getSelectedItem();
        
        if (usuarioActual == null) usuarioActual = new Usuario();
        usuarioActual.setUsername(user);
        usuarioActual.setPassword(pass);
        usuarioActual.setRol(cbRol.getSelectedItem().toString());
        usuarioActual.setActivo(true);
        
        if (emp != null && emp.getId() > 0) {
            usuarioActual.setEmpleado(emp);
        } else {
            usuarioActual.setEmpleado(null);
        }
        
        if (usuarioActual.getId() == null ? uDAO.insertar(usuarioActual) : uDAO.actualizar(usuarioActual)) {
            guardado = true;
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar usuario.");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    public boolean isGuardado() { return guardado; }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<Empleado> cbEmpleado;
    private javax.swing.JComboBox<String> cbRol;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}

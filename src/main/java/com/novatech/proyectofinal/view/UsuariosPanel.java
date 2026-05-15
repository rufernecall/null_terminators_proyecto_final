package com.novatech.proyectofinal.view;

import com.novatech.proyectofinal.controller.UsuarioController;
import com.novatech.proyectofinal.model.Usuario;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Panel de Usuarios vinculado al Controlador compatible con NetBeans
 * 
 * @author rufernecall
 */
public class UsuariosPanel extends javax.swing.JPanel implements ViewPanel {

    private UsuarioController controller;
    private DefaultTableModel modelo;

    public UsuariosPanel() {
        initComponents();
        controller = new UsuarioController();
        modelo = (DefaultTableModel) tablaUsuarios.getModel();
        configurarEventos();
    }

    @Override
    public void alCargar() {
        listar();
    }

    private void configurarEventos() {
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editarSeleccionado();
                }
            }
        });
    }

    private void listar() {
        try {
            modelo.setRowCount(0);
            List<Usuario> lista = controller.listarUsuarios();
            for (Usuario u : lista) {
                modelo.addRow(new Object[] {
                        u.getId(),
                        u.getUsername(),
                        u.getRol(),
                        (u.getEmpleado() != null ? u.getEmpleado().getNombre() : "SIN VINCULO"),
                        u.isActivo() ? "Activo" : "Inactivo"
                });
            }
        } catch (Exception e) {
            System.out.println("error listando: " + e.getMessage());
        }
    }

    private void editarSeleccionado() {
        int row = tablaUsuarios.getSelectedRow();
        if (row != -1) {
            Long id = (Long) tablaUsuarios.getValueAt(row, 0);
            Usuario u = controller.listarUsuarios().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
            if (u != null) {
                UsuarioForm form = new UsuarioForm(null, true, u);
                form.setVisible(true);
                if (form.isGuardado())
                    listar();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setBackground(new java.awt.Color(10, 12, 18));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Control de Accesos");

        tablaUsuarios.setBackground(new java.awt.Color(30, 30, 30));
        tablaUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "ID", "Usuario", "Rol", "Empleado", "Estado"
                }));
        jScrollPane1.setViewportView(tablaUsuarios);

        btnNuevo.setBackground(new java.awt.Color(40, 167, 69));
        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("+ NUEVO ACCESO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(125, 58, 58));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 788,
                                                Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(btnNuevo,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 160,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnEliminar,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 120,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                                .addContainerGap()));
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNuevoActionPerformed
        UsuarioForm form = new UsuarioForm(null, true, null);
        form.setVisible(true);
        if (form.isGuardado())
            listar();
    }// GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEliminarActionPerformed
        int row = tablaUsuarios.getSelectedRow();
        if (row != -1) {
            Long id = (Long) tablaUsuarios.getValueAt(row, 0);
            if (JOptionPane.showConfirmDialog(this, "¿Seguro?") == 0) {
                if (controller.eliminarUsuario(id))
                    listar();
            }
        }
    }// GEN-LAST:event_btnEliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaUsuarios;
    // End of variables declaration//GEN-END:variables
}

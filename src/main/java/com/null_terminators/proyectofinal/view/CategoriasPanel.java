package com.null_terminators.proyectofinal.view;

import com.null_terminators.proyectofinal.dao.CategoriaDAO;
import com.null_terminators.proyectofinal.model.Categoria;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Gestor para organizar los productos por categorias
 * 
 * @author rufernecall
 */
public class CategoriasPanel extends javax.swing.JPanel {

    private CategoriaDAO cDAO;
    private DefaultTableModel modelo;

    public CategoriasPanel() {
        this.cDAO = new CategoriaDAO();
        initComponents();
        modelo = (DefaultTableModel) tablaCategorias.getModel();
        listar();
    }

    // Cargamos la lista desde la base de datos
    private void listar() {
        modelo.setRowCount(0);
        List<Categoria> lista = cDAO.listar();
        for (Categoria c : lista) {
            modelo.addRow(new Object[] {
                    c.getId(),
                    c.getNombre(),
                    c.isActivo() ? "Activo" : "Inactivo"
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCategorias = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setBackground(new java.awt.Color(20, 20, 25));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Categorias de Productos");

        tablaCategorias.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "ID", "Nombre", "Estado"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaCategorias);

        btnNuevo.setBackground(new java.awt.Color(40, 167, 69));
        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("Nueva Categoria");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar Seleccionada");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(220, 53, 69));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
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
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblTitulo)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 558,
                                                        Short.MAX_VALUE)
                                                .addGap(20, 20, 20)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(btnNuevo,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnEditar,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnEliminar,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 160,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(30, 30, 30)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(lblTitulo)
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470,
                                                Short.MAX_VALUE))
                                .addGap(30, 30, 30)));
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        String nombre = JOptionPane.showInputDialog(this, "Nombre de la categoria:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            Categoria c = new Categoria();
            c.setNombre(nombre.trim());
            c.setActivo(true);
            if (cDAO.insertar(c))
                listar();
        }
    }

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tablaCategorias.getSelectedRow();
        if (row != -1) {
            Long id = (Long) tablaCategorias.getValueAt(row, 0);
            String current = (String) tablaCategorias.getValueAt(row, 1);
            String nuevo = JOptionPane.showInputDialog(this, "Cambiar nombre:", current);
            if (nuevo != null && !nuevo.trim().isEmpty()) {
                Categoria c = new Categoria();
                c.setId(id);
                c.setNombre(nuevo.trim());
                c.setActivo(true);
                if (cDAO.actualizar(c))
                    listar();
            }
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tablaCategorias.getSelectedRow();
        if (row != -1) {
            Long id = (Long) tablaCategorias.getValueAt(row, 0);
            if (JOptionPane.showConfirmDialog(this, "¿Borrar esta categoria?") == 0) {
                if (cDAO.eliminar(id))
                    listar();
            }
        }
    }

    // Los componentes que usa netbeans
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tablaCategorias;
    // End of variables declaration//GEN-END:variables
}

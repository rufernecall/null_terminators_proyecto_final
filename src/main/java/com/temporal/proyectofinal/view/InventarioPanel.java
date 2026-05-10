package com.temporal.proyectofinal.view;

import com.temporal.proyectofinal.controller.InventarioController;
import com.temporal.proyectofinal.model.Producto;
import com.temporal.proyectofinal.util.DataStore;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * Inventario de alto nivel con KPIs y alertas de stock
 * 
 * @author rufernecall
 */
public class InventarioPanel extends javax.swing.JPanel {

    private InventarioController controller;
    private DefaultTableModel modelo;
    private JLabel lblTotalModelos, lblUnidadesStock, lblValorTotal;

    public InventarioPanel() {
        initComponents();
        controller = new InventarioController();
        modelo = (DefaultTableModel) tablaInventario.getModel();

        initCustomKpis();
        configurarTabla();
        listarProductos("");
        cargarKpis();
    }

    private void initCustomKpis() {
        lblTotalModelos = new JLabel("0");
        lblUnidadesStock = new JLabel("0");
        lblValorTotal = new JLabel("S/ 0.00");

        pnlKpis.add(crearMiniTarjeta("MODELOS", lblTotalModelos, new Color(50, 150, 255)));
        pnlKpis.add(crearMiniTarjeta("STOCK FÍSICO", lblUnidadesStock, new Color(166, 51, 255)));
        pnlKpis.add(crearMiniTarjeta("VALOR TOTAL", lblValorTotal, new Color(40, 167, 69)));
    }

    private void cargarKpis() {
        new Thread(() -> {
            int modelos = controller.getTotalProductos();
            int stock = controller.getTotalStockGlobal();
            double valor = controller.getValorInventarioTotal();

            SwingUtilities.invokeLater(() -> {
                lblTotalModelos.setText(String.valueOf(modelos));
                lblUnidadesStock.setText(String.valueOf(stock));
                lblValorTotal.setText("S/ " + String.format("%.2f", valor));
            });
        }).start();
    }

    private void configurarTabla() {
        // Renderer para alertar cuando el stock es bajo
        tablaInventario.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                int stock = (int) value;
                if (stock < 5) {
                    c.setForeground(new Color(255, 100, 100)); // Rojo alerta
                    if (!isSelected)
                        c.setBackground(new Color(60, 30, 30));
                } else {
                    c.setForeground(Color.WHITE);
                    if (!isSelected)
                        c.setBackground(new Color(30, 30, 35));
                }
                return c;
            }
        });
    }

    private void listarProductos(String filtro) {
        modelo.setRowCount(0);
        List<Producto> lista = DataStore.getInstance().getProductos();
        for (Producto p : lista) {
            if (p.getNombre().toLowerCase().contains(filtro.toLowerCase())) {
                modelo.addRow(new Object[] {
                        p.getId(),
                        p.getNombre(),
                        String.format("%.2f", p.getPrecio()),
                        p.getStock()
                });
            }
        }
    }

    private JPanel crearMiniTarjeta(String titulo, JLabel valor, Color acento) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.dispose();
            }
        };
        card.setLayout(new GridLayout(2, 1));
        card.setBackground(new Color(35, 35, 45));
        card.setBorder(new EmptyBorder(10, 15, 10, 15));

        JLabel lblTit = new JLabel(titulo);
        lblTit.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblTit.setForeground(acento);
        valor.setFont(new Font("Segoe UI", Font.BOLD, 18));
        valor.setForeground(Color.WHITE);

        card.add(lblTit);
        card.add(valor);
        return card;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        pnlKpis = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();

        setBackground(new java.awt.Color(20, 20, 25));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Control de Inventario");

        pnlKpis.setOpaque(false);
        pnlKpis.setLayout(new java.awt.GridLayout(1, 3, 20, 0));

        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(40, 167, 69));
        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("+ Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(88, 157, 246));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(125, 58, 58));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "ID", "Nombre", "Precio", "Stock"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tablaInventario.setRowHeight(35);
        jScrollPane1.setViewportView(tablaInventario);

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
                                        .addComponent(pnlKpis, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 280,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 110,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 110,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 110,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 840,
                                                Short.MAX_VALUE))
                                .addGap(30, 30, 30)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(lblTitulo)
                                .addGap(25, 25, 25)
                                .addComponent(pnlKpis, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                                .addGap(30, 30, 30)));
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtBuscarKeyReleased
        listarProductos(txtBuscar.getText());
    }// GEN-LAST:event_txtBuscarKeyReleased

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNuevoActionPerformed
        ProductoForm form = new ProductoForm(null, true, new Producto());
        form.setVisible(true);
        if (form.isGuardado()) {
            listarProductos("");
            cargarKpis();
        }
    }// GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEditarActionPerformed
        int row = tablaInventario.getSelectedRow();
        if (row != -1) {
            Long id = (Long) tablaInventario.getValueAt(row, 0);
            Producto p = DataStore.getInstance().getProductos().stream()
                    .filter(prod -> prod.getId().equals(id)).findFirst().orElse(null);

            if (p != null) {
                ProductoForm form = new ProductoForm(null, true, p);
                form.setVisible(true);
                if (form.isGuardado()) {
                    listarProductos("");
                    cargarKpis();
                }
            }
        }
    }// GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEliminarActionPerformed
        int row = tablaInventario.getSelectedRow();
        if (row != -1) {
            if (JOptionPane.showConfirmDialog(this, "¿Eliminar producto?") == 0) {
                Long id = (Long) tablaInventario.getValueAt(row, 0);
                if (controller.eliminarProducto(id)) {
                    DataStore.getInstance().refrescarProductos();
                    listarProductos("");
                    cargarKpis();
                }
            }
        }
    }// GEN-LAST:event_btnEliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlKpis;
    private javax.swing.JTable tablaInventario;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}

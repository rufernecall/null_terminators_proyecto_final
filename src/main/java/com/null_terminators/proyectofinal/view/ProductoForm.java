package com.null_terminators.proyectofinal.view;

import com.null_terminators.proyectofinal.controller.InventarioController;
import com.null_terminators.proyectofinal.model.Categoria;
import com.null_terminators.proyectofinal.model.Producto;
import com.null_terminators.proyectofinal.model.Proveedor;
import com.null_terminators.proyectofinal.util.DataStore;
import com.null_terminators.proyectofinal.util.SupabaseStorageUtil;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Formulario de Productos vinculado al Controlador
 * 
 * @author rufernecall
 */
public class ProductoForm extends javax.swing.JDialog {

    private InventarioController controller;
    private Producto productoActual;
    private boolean guardado = false;

    public ProductoForm(java.awt.Frame parent, boolean modal, Producto p) {
        super(parent, modal);
        this.controller = new InventarioController();
        this.productoActual = p;
        initComponents();
        cargarComboData();
        cargarDatos();
        setLocationRelativeTo(parent);
    }

    private void cargarComboData() {
        cbCategoria.removeAllItems();
        DataStore.getInstance().getCategorias().forEach(cbCategoria::addItem);
        cbProveedor.removeAllItems();
        DataStore.getInstance().getProveedores().forEach(cbProveedor::addItem);
    }

    private void actualizarPreview() {
        String urlStr = txtImagen.getText();
        if (urlStr == null || urlStr.isEmpty()) {
            lblPreview.setIcon(null);
            lblPreview.setText("Sin Imagen");
            return;
        }

        // --- OPTIMIZACION: Intentar usar cache primero ---
        ImageIcon cached = DataStore.getInstance().getCachedImage(urlStr);
        if (cached != null) {
            lblPreview.setIcon(cached);
            lblPreview.setText("");
            return;
        }

        new Thread(() -> {
            try {
                URL url = new URL(urlStr);
                Image img = ImageIO.read(url);
                if (img != null) {
                    Image scaled = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    SwingUtilities.invokeLater(() -> {
                        lblPreview.setIcon(new ImageIcon(scaled));
                        lblPreview.setText("");
                    });
                }
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    lblPreview.setIcon(null);
                    lblPreview.setText("Error al cargar imagen");
                });
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        pnlDatos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbProveedor = new javax.swing.JComboBox<>();
        pnlImagenRow = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtImagen = new javax.swing.JTextField();
        btnSubir = new javax.swing.JButton();
        pnlPreview = new javax.swing.JPanel();
        lblPreview = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion de Producto");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(40, 167, 69));
        lblTitulo.setText("DATOS DEL PRODUCTO");

        pnlDatos.setOpaque(false);
        pnlDatos.setLayout(new java.awt.GridLayout(0, 1, 0, 5));

        jLabel2.setText("Nombre:");
        pnlDatos.add(jLabel2);
        pnlDatos.add(txtNombre);

        jLabel3.setText("Precio de Venta:");
        pnlDatos.add(jLabel3);
        pnlDatos.add(txtPrecio);

        jLabel4.setText("Stock Inicial:");
        pnlDatos.add(jLabel4);
        pnlDatos.add(txtStock);

        jLabel5.setText("Categoria:");
        pnlDatos.add(jLabel5);
        pnlDatos.add(cbCategoria);

        jLabel6.setText("Proveedor:");
        pnlDatos.add(jLabel6);
        pnlDatos.add(cbProveedor);

        pnlImagenRow.setOpaque(false);
        pnlImagenRow.setLayout(new java.awt.BorderLayout());

        jLabel7.setText("URL Imagen:");
        pnlImagenRow.add(jLabel7, java.awt.BorderLayout.NORTH);

        txtImagen.setEditable(false);
        pnlImagenRow.add(txtImagen, java.awt.BorderLayout.CENTER);

        btnSubir.setText("...");
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });
        pnlImagenRow.add(btnSubir, java.awt.BorderLayout.EAST);

        pnlDatos.add(pnlImagenRow);

        pnlPreview.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Vista Previa ",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(153, 153, 153))); // NOI18N
        pnlPreview.setOpaque(false);
        pnlPreview.setLayout(new java.awt.BorderLayout());

        lblPreview.setForeground(new java.awt.Color(102, 102, 102));
        lblPreview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPreview.setText("Sin Imagen");
        pnlPreview.add(lblPreview, java.awt.BorderLayout.CENTER);

        btnGuardar.setBackground(new java.awt.Color(40, 167, 69));
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
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 320,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)
                                                .addComponent(pnlPreview, javax.swing.GroupLayout.DEFAULT_SIZE, 290,
                                                        Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                .createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 140,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblTitulo)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(25, 25, 25)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lblTitulo)
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(pnlPreview, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30,
                                        Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSubirActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccionar Imagen del Producto");
        chooser.setFileFilter(new FileNameExtensionFilter("Imagenes", "jpg", "png", "jpeg"));

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selected = chooser.getSelectedFile();
            // Mostrar indicador de carga
            lblPreview.setText("Subiendo...");
            lblPreview.setIcon(null);

            new Thread(() -> {
                try {
                    String url = SupabaseStorageUtil.subirImagen(selected);
                    SwingUtilities.invokeLater(() -> {
                        txtImagen.setText(url);
                        actualizarPreview();
                        JOptionPane.showMessageDialog(this, "Imagen subida exitosamente a la nube.");
                    });
                } catch (Exception e) {
                    SwingUtilities.invokeLater(() -> {
                        lblPreview.setText("Error subida");
                        JOptionPane.showMessageDialog(this, "Error al subir imagen: " + e.getMessage());
                    });
                }
            }).start();
        }
    }// GEN-LAST:event_btnSubirActionPerformed

    private void cargarDatos() {
        if (productoActual != null) {
            txtNombre.setText(productoActual.getNombre());
            txtPrecio.setText(String.valueOf(productoActual.getPrecio()));
            txtStock.setText(String.valueOf(productoActual.getStock()));
            txtStock.setEditable(false); // Bloqueamos edicion manual de stock
            txtStock.setToolTipText("El stock solo se actualiza via Ventas o Compras");
            txtImagen.setText(productoActual.getImagenUrl());
            actualizarPreview();

            if (productoActual.getCategoria() != null) {
                for (int i = 0; i < cbCategoria.getItemCount(); i++) {
                    if (cbCategoria.getItemAt(i).getId().equals(productoActual.getCategoria().getId())) {
                        cbCategoria.setSelectedIndex(i);
                        break;
                    }
                }
            }
        }
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnGuardarActionPerformed
        try {
            if (productoActual == null)
                productoActual = new Producto();
            productoActual.setNombre(txtNombre.getText());
            productoActual.setPrecio(Double.parseDouble(txtPrecio.getText()));
            productoActual.setStock(Integer.parseInt(txtStock.getText()));
            productoActual.setCategoria((Categoria) cbCategoria.getSelectedItem());
            productoActual.setProveedor((Proveedor) cbProveedor.getSelectedItem());
            productoActual.setImagenUrl(txtImagen.getText());

            if (controller.guardarProducto(productoActual)) {
                DataStore.getInstance().refrescarProductos();
                guardado = true;
                dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Datos invalidos: " + e.getMessage());
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
    private javax.swing.JButton btnSubir;
    private javax.swing.JComboBox<Categoria> cbCategoria;
    private javax.swing.JComboBox<Proveedor> cbProveedor;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblPreview;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlImagenRow;
    private javax.swing.JPanel pnlPreview;
    private javax.swing.JTextField txtImagen;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}

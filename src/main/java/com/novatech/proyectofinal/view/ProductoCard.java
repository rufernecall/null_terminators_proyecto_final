package com.novatech.proyectofinal.view;

import com.novatech.proyectofinal.model.Producto;
import com.novatech.proyectofinal.util.DataStore;
import java.awt.Image;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

/**
 * Tarjeta de producto compatible con NetBeans Designer y Cache de Imagenes
 * 
 * @author rufernecall
 */
public class ProductoCard extends javax.swing.JPanel {

    private Producto producto;

    public ProductoCard(Producto p) {
        initComponents();
        this.producto = p;
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 52, 60), 1));
        cargarDatos();
    }

    private void cargarDatos() {
        if (producto == null)
            return;

        lblNombre.setText(producto.getNombre());
        lblPrecio.setText("S/ " + String.format("%.2f", producto.getPrecio()));
        lblStock.setText("Stock: " + producto.getStock());

        // --- OPTIMIZACION: Usar cache de DataStore ---
        String urlStr = producto.getImagenUrl();
        if (urlStr != null && !urlStr.isEmpty()) {
            ImageIcon cached = DataStore.getInstance().getCachedImage(urlStr);
            if (cached != null) {
                lblImg.setIcon(cached);
                lblImg.setText("");
            } else {
                cargarImagenAsincrona(urlStr);
            }
        } else {
            lblImg.setText("Sin Foto");
        }
    }

    private void cargarImagenAsincrona(String urlStr) {
        new Thread(() -> {
            try {
                URL url = new URL(urlStr);
                Image img = ImageIO.read(url);
                if (img != null) {
                    Image scaledImg = img.getScaledInstance(160, 120, Image.SCALE_SMOOTH);
                    SwingUtilities.invokeLater(() -> {
                        lblImg.setText("");
                        lblImg.setIcon(new ImageIcon(scaledImg));
                    });
                }
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> lblImg.setText("Error IMG"));
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlImagen = new javax.swing.JPanel();
        lblImg = new javax.swing.JLabel();
        pnlInfo = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5),
                new java.awt.Dimension(32767, 5));
        lblPrecio = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5),
                new java.awt.Dimension(32767, 5));
        lblStock = new javax.swing.JLabel();

        setBackground(new java.awt.Color(30, 32, 40));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 52, 60)));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setPreferredSize(new java.awt.Dimension(180, 240));
        setLayout(new java.awt.BorderLayout());

        pnlImagen.setBackground(new java.awt.Color(20, 22, 28));
        pnlImagen.setPreferredSize(new java.awt.Dimension(180, 140));
        pnlImagen.setLayout(new java.awt.BorderLayout());

        lblImg.setForeground(new java.awt.Color(64, 64, 64));
        lblImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImg.setText("Cargando...");
        pnlImagen.add(lblImg, java.awt.BorderLayout.CENTER);

        add(pnlImagen, java.awt.BorderLayout.NORTH);

        pnlInfo.setBackground(new java.awt.Color(30, 32, 40));
        pnlInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlInfo.setLayout(new javax.swing.BoxLayout(pnlInfo, javax.swing.BoxLayout.Y_AXIS));

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("Nombre Producto");
        pnlInfo.add(lblNombre);
        pnlInfo.add(filler1);

        lblPrecio.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblPrecio.setForeground(new java.awt.Color(88, 157, 246));
        lblPrecio.setText("S/ 0.00");
        pnlInfo.add(lblPrecio);
        pnlInfo.add(filler2);

        lblStock.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblStock.setForeground(new java.awt.Color(128, 128, 128));
        lblStock.setText("Stock: 0");
        pnlInfo.add(lblStock);

        add(pnlInfo, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JLabel lblImg;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblStock;
    private javax.swing.JPanel pnlImagen;
    private javax.swing.JPanel pnlInfo;
    // End of variables declaration//GEN-END:variables
}

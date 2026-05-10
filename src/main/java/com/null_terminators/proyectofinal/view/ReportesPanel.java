package com.null_terminators.proyectofinal.view;

import com.null_terminators.proyectofinal.controller.ReporteController;
import com.null_terminators.proyectofinal.model.Venta;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * Historial de Ventas vinculado al Controlador de Reportes
 * 
 * @author rufernecall
 */
public class ReportesPanel extends javax.swing.JPanel {

    private ReporteController controller;
    private DefaultTableModel modelo;

    public ReportesPanel() {
        initComponents();
        controller = new ReporteController();
        modelo = (DefaultTableModel) tablaVentas.getModel();
        listarVentas();
        cargarEstadisticas();
    }

    private void listarVentas() {
        try {
            modelo.setRowCount(0);
            List<Venta> lista = controller.listarVentas();
            for (Venta v : lista) {
                modelo.addRow(new Object[] {
                        v.getId(),
                        v.getFecha().toString(),
                        v.getCliente() != null ? v.getCliente().getNombre() : "General",
                        String.format("%.2f", v.getTotal()),
                        v.getEstado()
                });
            }
        } catch (Exception e) {
            System.out.println("error listando ventas: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        pnlTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        pnlBotones = new javax.swing.JPanel();
        btnRefrescar = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        tabEstadisticas = new javax.swing.JTabbedPane();
        pnlTopProductos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaTop = new javax.swing.JList<>();
        pnlVendedores = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaVendedores = new javax.swing.JList<>();

        setBackground(new java.awt.Color(20, 22, 28));
        setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Centro de Reportes y Analitica");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 20, 15, 20));
        add(jLabel1, java.awt.BorderLayout.NORTH);

        jSplitPane1.setDividerLocation(550);
        jSplitPane1.setDividerSize(5);

        pnlTabla.setOpaque(false);
        pnlTabla.setLayout(new java.awt.BorderLayout());

        tablaVentas.setBackground(new java.awt.Color(30, 32, 40));
        tablaVentas.setForeground(new java.awt.Color(255, 255, 255));
        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "ID", "Fecha", "Cliente", "Total", "Estado" }
        ));
        jScrollPane1.setViewportView(tablaVentas);
        pnlTabla.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnlBotones.setOpaque(false);
        pnlBotones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 10));

        btnRefrescar.setText("Refrescar");
        btnRefrescar.addActionListener(e -> { listarVentas(); cargarEstadisticas(); });
        pnlBotones.add(btnRefrescar);

        btnExportar.setText("Exportar PDF");
        pnlBotones.add(btnExportar);

        pnlTabla.add(pnlBotones, java.awt.BorderLayout.SOUTH);
        jSplitPane1.setLeftComponent(pnlTabla);

        tabEstadisticas.setBackground(new java.awt.Color(25, 27, 35));
        
        pnlTopProductos.setLayout(new java.awt.BorderLayout());
        pnlTopProductos.add(new javax.swing.JLabel(" PRODUCTOS MAS VENDIDOS", javax.swing.SwingConstants.CENTER), java.awt.BorderLayout.NORTH);
        jScrollPane2.setViewportView(listaTop);
        pnlTopProductos.add(jScrollPane2, java.awt.BorderLayout.CENTER);
        tabEstadisticas.addTab("Top Ventas", pnlTopProductos);

        pnlVendedores.setLayout(new java.awt.BorderLayout());
        pnlVendedores.add(new javax.swing.JLabel(" VENTAS POR VENDEDOR", javax.swing.SwingConstants.CENTER), java.awt.BorderLayout.NORTH);
        jScrollPane3.setViewportView(listaVendedores);
        pnlVendedores.add(jScrollPane3, java.awt.BorderLayout.CENTER);
        tabEstadisticas.addTab("Vendedores", pnlVendedores);

        jSplitPane1.setRightComponent(tabEstadisticas);
        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void cargarEstadisticas() {
        var top = controller.getTopProductos();
        javax.swing.DefaultListModel<String> modelTop = new javax.swing.DefaultListModel<>();
        top.forEach((k, v) -> modelTop.addElement(k + " (" + v + " u)"));
        listaTop.setModel(modelTop);

        var vend = controller.getVentasPorVendedor();
        javax.swing.DefaultListModel<String> modelVend = new javax.swing.DefaultListModel<>();
        vend.forEach((k, v) -> modelVend.addElement(k + ": S/ " + String.format("%.2f", v)));
        listaVendedores.setModel(modelVend);
    }

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {
        listarVentas();
    }

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {
        // Logica de exportacion
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JList<String> listaTop;
    private javax.swing.JList<String> listaVendedores;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlTabla;
    private javax.swing.JPanel pnlTopProductos;
    private javax.swing.JPanel pnlVendedores;
    private javax.swing.JTabbedPane tabEstadisticas;
    private javax.swing.JTable tablaVentas;
    // End of variables declaration//GEN-END:variables
}

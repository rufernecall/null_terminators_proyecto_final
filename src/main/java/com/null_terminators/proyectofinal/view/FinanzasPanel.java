package com.null_terminators.proyectofinal.view;

import com.null_terminators.proyectofinal.dao.FinanzasDAO;
import com.null_terminators.proyectofinal.model.Comprobante;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * Panel para la gestion y visualizacion de Facturas y Boletas
 * @author rufernecall
 */
public class FinanzasPanel extends javax.swing.JPanel {

    private final FinanzasDAO finanzasDAO;
    private DefaultTableModel modelo;

    public FinanzasPanel() {
        this.finanzasDAO = new FinanzasDAO();
        initComponents();
        modelo = (DefaultTableModel) tablaComprobantes.getModel();
        listarComprobantes();
    }

    private void listarComprobantes() {
        modelo.setRowCount(0);
        List<Comprobante> lista = finanzasDAO.listar();
        for (Comprobante c : lista) {
            modelo.addRow(new Object[]{
                c.getId(),
                c.getTipo(),
                c.getSerie() + "-" + c.getNumero(),
                c.getFecha().toString(),
                String.format("%.2f", c.getMontoTotal()),
                c.getEstado(),
                c.getVentaId() != null ? "VENTA #" + c.getVentaId() : "COMPRA #" + c.getCompraId()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        pnlFiltros = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        btnFiltrar = new javax.swing.JButton();
        btnRefrescar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaComprobantes = new javax.swing.JTable();
        btnVerDetalle = new javax.swing.JButton();

        setBackground(new java.awt.Color(25, 25, 30));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("GESTIÓN DE FINANZAS Y COMPROBANTES");

        pnlFiltros.setOpaque(false);
        pnlFiltros.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 10));

        jLabel1.setForeground(new java.awt.Color(200, 200, 200));
        jLabel1.setText("Tipo de Documento:");
        pnlFiltros.add(jLabel1);

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS", "FACTURA", "BOLETA" }));
        pnlFiltros.add(cmbTipo);

        btnFiltrar.setText("Filtrar");
        pnlFiltros.add(btnFiltrar);

        btnRefrescar.setText("Refrescar");
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarComprobantes();
            }
        });
        pnlFiltros.add(btnRefrescar);

        tablaComprobantes.setBackground(new java.awt.Color(35, 35, 45));
        tablaComprobantes.setForeground(new java.awt.Color(255, 255, 255));
        tablaComprobantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tipo", "Serie-Num", "Fecha", "Monto", "Estado", "Referencia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaComprobantes.setRowHeight(30);
        jScrollPane1.setViewportView(tablaComprobantes);

        btnVerDetalle.setBackground(new java.awt.Color(50, 100, 255));
        btnVerDetalle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVerDetalle.setForeground(new java.awt.Color(255, 255, 255));
        btnVerDetalle.setText("VER DETALLE / IMPRIMIR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnVerDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 940, Short.MAX_VALUE)
                            .addComponent(pnlFiltros, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitulo)
                .addGap(20, 20, 20)
                .addComponent(pnlFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addComponent(btnVerDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnVerDetalle;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlFiltros;
    private javax.swing.JTable tablaComprobantes;
    // End of variables declaration//GEN-END:variables
}

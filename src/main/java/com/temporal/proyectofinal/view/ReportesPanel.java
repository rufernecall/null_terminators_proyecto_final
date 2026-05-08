package com.temporal.proyectofinal.view;

import com.temporal.proyectofinal.dao.VentasDAO;
import com.temporal.proyectofinal.model.OrdenVenta;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * Historial de Ventas (Sincronizado con Supabase)
 * @author rufernecall
 */
public class ReportesPanel extends javax.swing.JPanel {

    private DefaultTableModel modelo;

    public ReportesPanel() {
        initComponents();
        modelo = (DefaultTableModel) tablaVentas.getModel();
        listarVentas();
    }
    
    private void listarVentas() {
        try {
            modelo.setRowCount(0);
            VentasDAO vDAO = new VentasDAO();
            List<OrdenVenta> lista = vDAO.listar();
            for (OrdenVenta v : lista) {
                modelo.addRow(new Object[]{
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        btnRefrescar = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();

        setBackground(new java.awt.Color(30, 30, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Historial de Ventas");

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID", "Fecha", "Cliente", "Total", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaVentas);

        btnRefrescar.setText("Refrescar Lista");
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarActionPerformed(evt);
            }
        });

        btnExportar.setText("Exportar PDF");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JTable tablaVentas;
    // End of variables declaration//GEN-END:variables
}

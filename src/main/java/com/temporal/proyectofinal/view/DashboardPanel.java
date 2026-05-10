package com.temporal.proyectofinal.view;

import com.temporal.proyectofinal.controller.ReporteController;
import com.temporal.proyectofinal.model.OrdenVenta;
import java.awt.*;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;

/**
 * Dashboard optimizado y compatible con NetBeans Designer
 * @author rufernecall
 */
public class DashboardPanel extends javax.swing.JPanel {

    private ReporteController controller;
    private JLabel lblVentasHoy, lblTotalProductos, lblTotalClientes, lblStockCritico;
    private DefaultTableModel modelo;

    public DashboardPanel() {
        initComponents();
        controller = new ReporteController();
        modelo = (DefaultTableModel) tablaRecientes.getModel();
        initCustomCards();
        cargarEstadisticas();
    }

    // Aca creamos las tarjetas con profundidad que el diseñador no puede hacer solo
    private void initCustomCards() {
        lblVentasHoy = new JLabel("S/ 0.00");
        lblTotalProductos = new JLabel("0");
        lblTotalClientes = new JLabel("0");
        lblStockCritico = new JLabel("0 Items");

        pnlGrid.add(crearTarjeta("VENTAS HOY", lblVentasHoy, new Color(40, 167, 69)));
        pnlGrid.add(crearTarjeta("STOCK TOTAL", lblTotalProductos, new Color(50, 100, 255)));
        pnlGrid.add(crearTarjeta("CLIENTES", lblTotalClientes, new Color(166, 51, 255)));
        pnlGrid.add(crearTarjeta("ALERTAS STOCK", lblStockCritico, new Color(220, 53, 69)));
    }

    private void cargarEstadisticas() {
        new Thread(() -> {
            try {
                Map<String, Object> stats = controller.getEstadisticasGenerales();
                if (!stats.isEmpty()) {
                    double ventas = (double) stats.get("ventasHoy");
                    long stock = (long) stats.get("stockTotal");
                    long clientes = (long) stats.get("clientesTotal");
                    long critico = (long) stats.get("stockCritico");

                    SwingUtilities.invokeLater(() -> {
                        lblVentasHoy.setText("S/ " + String.format("%.2f", ventas));
                        lblTotalProductos.setText(String.valueOf(stock));
                        lblTotalClientes.setText(String.valueOf(clientes));
                        lblStockCritico.setText(critico + " Items");
                    });
                }

                List<OrdenVenta> recientes = controller.getUltimasVentas(8);
                SwingUtilities.invokeLater(() -> {
                    modelo.setRowCount(0);
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    for (OrdenVenta o : recientes) {
                        modelo.addRow(new Object[]{
                            o.getCliente().getNombre(),
                            o.getFecha().format(fmt),
                            "S/ " + String.format("%.2f", o.getTotal())
                        });
                    }
                });

            } catch (Exception e) {
                System.err.println("error dashboard: " + e.getMessage());
            }
        }).start();
    }

    private JPanel crearTarjeta(String titulo, JLabel valor, Color acento) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
            }
        };
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(30, 30, 40));
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel lblTit = new JLabel(titulo);
        lblTit.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTit.setForeground(acento);
        valor.setFont(new Font("Segoe UI", Font.BOLD, 28));
        valor.setForeground(Color.WHITE);

        card.add(lblTit);
        card.add(Box.createVerticalStrut(10));
        card.add(valor);
        return card;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        pnlGrid = new javax.swing.JPanel();
        lblSub = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaRecientes = new javax.swing.JTable();

        setBackground(new java.awt.Color(20, 20, 25));
        setLayout(new java.awt.BorderLayout());

        pnlMain.setOpaque(false);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Panel de Control");

        pnlGrid.setOpaque(false);
        pnlGrid.setLayout(new java.awt.GridLayout(1, 4, 20, 0));

        lblSub.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSub.setForeground(new java.awt.Color(255, 255, 255));
        lblSub.setText(" ACTIVIDAD RECIENTE");

        tablaRecientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Fecha/Hora", "Monto Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaRecientes);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlGrid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo)
                            .addComponent(lblSub))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitulo)
                .addGap(30, 30, 30)
                .addComponent(pnlGrid, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(lblSub)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );

        add(pnlMain, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSub;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlGrid;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTable tablaRecientes;
    // End of variables declaration//GEN-END:variables
}

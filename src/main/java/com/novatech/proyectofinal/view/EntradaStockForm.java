package com.novatech.proyectofinal.view;

import com.novatech.proyectofinal.model.*;
import com.novatech.proyectofinal.controller.ComercialController;
import com.novatech.proyectofinal.util.DataStore;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Formulario para el registro de Adquisiciones (Entradas de Stock)
 * 
 * @author rufernecall
 */
public class EntradaStockForm extends javax.swing.JDialog {

    private final ComercialController comercialController;
    private final List<DetalleCompra> detalles;
    private final Empleado responsable;
    private double totalCompra = 0.0;

    public EntradaStockForm(java.awt.Frame parent, boolean modal, Empleado responsable) {
        super(parent, modal);
        this.comercialController = new ComercialController();
        this.detalles = new ArrayList<>();
        this.responsable = responsable;
        initComponents();
        cargarCombos();
        setLocationRelativeTo(parent);
    }

    private void cargarCombos() {
        DefaultComboBoxModel<Proveedor> modelProv = new DefaultComboBoxModel<>();
        DataStore.getInstance().getProveedores().forEach(modelProv::addElement);
        cmbProveedor.setModel(modelProv);

        DefaultComboBoxModel<Producto> modelProd = new DefaultComboBoxModel<>();
        DataStore.getInstance().getProductos().forEach(modelProd::addElement);
        cmbProducto.setModel(modelProd);
    }

    private void agregarItem() {
        Producto p = (Producto) cmbProducto.getSelectedItem();
        try {
            int cant = Integer.parseInt(txtCantidad.getText());
            double costo = Double.parseDouble(txtCosto.getText());

            if (cant <= 0 || costo < 0)
                throw new Exception();

            DetalleCompra d = new DetalleCompra();
            d.setProducto(p);
            d.setCantidad(cant);
            d.setCostoUnitario(costo);
            d.setSubtotal(cant * costo);

            detalles.add(d);
            actualizarTabla();

            txtCantidad.setText("");
            txtCosto.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ingrese cantidad y costo validos.");
        }
    }

    private void actualizarTabla() {
        DefaultTableModel model = (DefaultTableModel) tablaDetalles.getModel();
        model.setRowCount(0);
        totalCompra = 0;
        for (DetalleCompra d : detalles) {
            model.addRow(new Object[] {
                    d.getProducto().getNombre(),
                    d.getCantidad(),
                    String.format("%.2f", d.getCostoUnitario()),
                    String.format("%.2f", d.getSubtotal())
            });
            totalCompra += d.getSubtotal();
        }
        lblTotal.setText("Total: S/ " + String.format("%.2f", totalCompra));
    }

    private void finalizarEntrada() {
        if (detalles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Agregue al menos un producto.");
            return;
        }

        Compra c = new Compra();
        c.setProveedor((Proveedor) cmbProveedor.getSelectedItem());
        c.setResponsable(responsable);
        c.setFecha(LocalDateTime.now());
        c.setTotal(totalCompra);
        c.setEstado("COMPLETADO");

        if (comercialController.registrarCompra(c, detalles)) {
            JOptionPane.showMessageDialog(this, "Adquisicion registrada y stock actualizado.");
            DataStore.getInstance().refrescarProductos();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar la entrada.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox<>();
        pnlAdd = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmbProducto = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDetalles = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        btnFinalizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entrada de Mercaderia");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(50, 100, 255));
        lblTitulo.setText("REGISTRO DE ADQUISICIÓN");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Proveedor:");

        pnlAdd.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Añadir Item al Detalle", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 11))); // NOI18N
        pnlAdd.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 10));

        jLabel2.setText("Producto:");
        pnlAdd.add(jLabel2);

        cmbProducto.setPreferredSize(new java.awt.Dimension(250, 30));
        pnlAdd.add(cmbProducto);

        jLabel3.setText("Cant:");
        pnlAdd.add(jLabel3);

        txtCantidad.setPreferredSize(new java.awt.Dimension(60, 30));
        pnlAdd.add(txtCantidad);

        jLabel4.setText("Costo U.:");
        pnlAdd.add(jLabel4);

        txtCosto.setPreferredSize(new java.awt.Dimension(80, 30));
        pnlAdd.add(txtCosto);

        btnAgregar.setBackground(new java.awt.Color(50, 100, 255));
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Añadir");
        btnAgregar.setPreferredSize(new java.awt.Dimension(100, 30));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarItem();
            }
        });
        pnlAdd.add(btnAgregar);

        tablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "Producto", "Cant", "Costo U.", "Subtotal" }
        ) {
            boolean[] canEdit = new boolean [] { false, false, false, false };
            public boolean isCellEditable(int rowIndex, int columnIndex) { return canEdit [columnIndex]; }
        });
        tablaDetalles.setRowHeight(30);
        jScrollPane1.setViewportView(tablaDetalles);

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotal.setText("Total: S/ 0.00");

        btnFinalizar.setBackground(new java.awt.Color(40, 167, 69));
        btnFinalizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFinalizar.setForeground(new java.awt.Color(255, 255, 255));
        btnFinalizar.setText("FINALIZAR REGISTRO");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarEntrada();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo)
                    .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(25, 25, 25)
                .addComponent(pnlAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotal))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JComboBox<Producto> cmbProducto;
    private javax.swing.JComboBox<Proveedor> cmbProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlAdd;
    private javax.swing.JTable tablaDetalles;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCosto;
}

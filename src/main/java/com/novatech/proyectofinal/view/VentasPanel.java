package com.novatech.proyectofinal.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.novatech.proyectofinal.controller.VentaController;
import com.novatech.proyectofinal.dao.ClienteDAO;
import com.novatech.proyectofinal.model.Categoria;
import com.novatech.proyectofinal.model.Cliente;
import com.novatech.proyectofinal.model.Producto;
import com.novatech.proyectofinal.model.Usuario;
import com.novatech.proyectofinal.util.DataStore;

/**
 * Punto de Venta Rediseñado (Sin Emojis y con Correccion de Stretching)
 * 
 * @author rufernecall
 */
public class VentasPanel extends javax.swing.JPanel {

    private VentaController controller;
    private Usuario usuarioLogueado;
    private Long categoriaSeleccionada = null;
    private ClienteDAO clienteDAO;

    public VentasPanel(Usuario usuario) {
        this.usuarioLogueado = usuario;
        this.controller = new VentaController();
        this.clienteDAO = new ClienteDAO();
        initComponents();
        cargarCategorias();
        cargarClientes();
        refrescarCatalogo();
        configurarTabla();
        configurarResponsive();
    }

    private void configurarResponsive() {
        jScrollPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                ajustarGrid();
            }
        });
    }

    private void ajustarGrid() {
        int width = jScrollPane1.getViewport().getWidth();
        if (width <= 0)
            return;

        int cardWidth = 180 + 15; // Ancho de card + gap
        int cols = Math.max(1, width / cardWidth);

        pnlGridProductos.setLayout(new java.awt.GridLayout(0, cols, 15, 15));
        pnlGridProductos.revalidate();
    }

    private void cargarCategorias() {
        pnlCategorias.removeAll();
        // Eliminado emoji de "TODO"
        JButton btnTodos = crearBotonCategoria("TODOS", null);
        pnlCategorias.add(btnTodos);

        List<Categoria> cats = DataStore.getInstance().getCategorias();
        for (Categoria c : cats) {
            pnlCategorias.add(javax.swing.Box.createVerticalStrut(5));
            pnlCategorias.add(crearBotonCategoria(c.getNombre(), c.getId()));
        }
        pnlCategorias.revalidate();
        pnlCategorias.repaint();
    }

    private void cargarClientes() {
        List<Cliente> clientes = clienteDAO.listar();
        DefaultComboBoxModel<Cliente> model = new DefaultComboBoxModel<>();
        for (Cliente c : clientes) {
            model.addElement(c);
        }
        cmbCliente.setModel(model);
    }

    private JButton crearBotonCategoria(String nombre, Long id) {
        JButton btn = new JButton(nombre);
        btn.setMaximumSize(new java.awt.Dimension(160, 45));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(30, 32, 40));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn.addActionListener(e -> {
            this.categoriaSeleccionada = id;
            filtrarProductos(txtBuscar.getText().trim());
        });
        return btn;
    }

    private void configurarTabla() {
        tablaCarrito.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tablaCarrito.getSelectedRow();
                    if (row != -1) {
                        controller.eliminarProducto(row);
                        actualizarCarrito();
                    }
                }
            }
        });
    }

    private void refrescarCatalogo() {
        filtrarProductos("");
    }

    private void filtrarProductos(String filtro) {
        pnlGridProductos.removeAll();
        List<Producto> lista = DataStore.getInstance().getProductos();
        List<Producto> filtrados = lista.stream()
                .filter(p -> p.getNombre().toLowerCase()
                        .contains(filtro.replace(" Buscar por nombre...", "").toLowerCase()))
                .filter(p -> categoriaSeleccionada == null
                        || (p.getCategoria() != null && p.getCategoria().getId().equals(categoriaSeleccionada)))
                .collect(Collectors.toList());

        for (Producto p : filtrados) {
            ProductoCard card = new ProductoCard(p);
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    agregarConCantidad(p);
                }
            });
            pnlGridProductos.add(card);
        }
        ajustarGrid();
        pnlGridProductos.revalidate();
        pnlGridProductos.repaint();
    }

    private void agregarConCantidad(Producto p) {
        String cantStr = JOptionPane.showInputDialog(this, "¿Cantidad para " + p.getNombre() + "?", "1");
        if (cantStr != null && !cantStr.isEmpty()) {
            try {
                int cant = Integer.parseInt(cantStr);
                if (cant > 0 && cant <= p.getStock()) {
                    controller.agregarProducto(p, cant);
                    actualizarCarrito();
                } else {
                    JOptionPane.showMessageDialog(this, "Stock insuficiente. Disponible: " + p.getStock());
                }
            } catch (Exception e) {
            }
        }
    }

    private void actualizarCarrito() {
        DefaultTableModel modelo = (DefaultTableModel) tablaCarrito.getModel();
        modelo.setRowCount(0);
        for (var d : controller.getCarrito()) {
            modelo.addRow(new Object[] { d.getProducto().getNombre(), d.getCantidad(), d.getSubtotal() });
        }
        lblTotal.setText("S/ " + String.format("%.2f", controller.getTotalVenta()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCategorias = new javax.swing.JPanel();
        pnlCentral = new javax.swing.JPanel();
        pnlBusqueda = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnRefrescar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlGridWrapper = new javax.swing.JPanel();
        pnlGridProductos = new javax.swing.JPanel();
        pnlCarrito = new javax.swing.JPanel();
        pnlTituloCarrito = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCarrito = new javax.swing.JTable();
        pnlPago = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        lblCli = new javax.swing.JLabel();
        cmbCliente = new javax.swing.JComboBox<>();
        lblMet = new javax.swing.JLabel();
        cmbMetodoPago = new javax.swing.JComboBox<>();
        btnVender = new javax.swing.JButton();

        setBackground(new java.awt.Color(10, 12, 18));
        setLayout(new java.awt.BorderLayout());

        pnlCategorias.setBackground(new java.awt.Color(20, 20, 28));
        pnlCategorias.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CATEGORIAS",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(150, 150, 160))); // NOI18N
        pnlCategorias.setPreferredSize(new java.awt.Dimension(180, 520));
        pnlCategorias.setLayout(new javax.swing.BoxLayout(pnlCategorias, javax.swing.BoxLayout.Y_AXIS));
        add(pnlCategorias, java.awt.BorderLayout.WEST);

        pnlCentral.setOpaque(false);
        pnlCentral.setLayout(new java.awt.BorderLayout());

        pnlBusqueda.setOpaque(false);
        pnlBusqueda.setPreferredSize(new java.awt.Dimension(610, 60));
        pnlBusqueda.setLayout(null);

        txtBuscar.setBackground(new java.awt.Color(20, 22, 28));
        txtBuscar.setForeground(new java.awt.Color(204, 204, 204));
        txtBuscar.setText(" Buscar por nombre...");
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        pnlBusqueda.add(txtBuscar);
        txtBuscar.setBounds(20, 10, 450, 40);

        btnRefrescar.setText("Refrescar");
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarActionPerformed(evt);
            }
        });
        pnlBusqueda.add(btnRefrescar);
        btnRefrescar.setBounds(480, 10, 100, 40);

        pnlCentral.add(pnlBusqueda, java.awt.BorderLayout.NORTH);

        jScrollPane1.setBorder(null);

        pnlGridWrapper.setOpaque(false);
        pnlGridWrapper.setLayout(new java.awt.BorderLayout());

        pnlGridProductos.setBackground(new java.awt.Color(10, 12, 18));
        pnlGridProductos.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 15));
        pnlGridWrapper.add(pnlGridProductos, java.awt.BorderLayout.NORTH);

        jScrollPane1.setViewportView(pnlGridWrapper);

        pnlCentral.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(pnlCentral, java.awt.BorderLayout.CENTER);

        pnlCarrito.setBackground(new java.awt.Color(20, 20, 25));
        pnlCarrito.setPreferredSize(new java.awt.Dimension(350, 520));
        pnlCarrito.setLayout(new java.awt.BorderLayout());

        pnlTituloCarrito.setOpaque(false);
        pnlTituloCarrito.setPreferredSize(new java.awt.Dimension(350, 50));
        pnlTituloCarrito.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CARRITO DE COMPRAS");
        pnlTituloCarrito.add(jLabel1);
        jLabel1.setBounds(20, 15, 157, 20);

        pnlCarrito.add(pnlTituloCarrito, java.awt.BorderLayout.NORTH);

        jScrollPane2.setBorder(null);

        tablaCarrito.setBackground(new java.awt.Color(30, 30, 30));
        tablaCarrito.setForeground(new java.awt.Color(255, 255, 255));
        tablaCarrito.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Item", "Cant", "Subtotal"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaCarrito);

        pnlCarrito.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pnlPago.setBackground(new java.awt.Color(22, 24, 32));
        pnlPago.setPreferredSize(new java.awt.Dimension(350, 280));
        pnlPago.setLayout(null);

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("S/ 0.00");
        pnlPago.add(lblTotal);
        lblTotal.setBounds(20, 10, 310, 50);

        lblCli.setForeground(new java.awt.Color(160, 160, 160));
        lblCli.setText("CLIENTE:");
        pnlPago.add(lblCli);
        lblCli.setBounds(20, 70, 100, 16);

        pnlPago.add(cmbCliente);
        cmbCliente.setBounds(20, 90, 310, 35);

        lblMet.setForeground(new java.awt.Color(160, 160, 160));
        lblMet.setText("METODO DE PAGO:");
        pnlPago.add(lblMet);
        lblMet.setBounds(20, 135, 120, 16);

        cmbMetodoPago.setModel(
                new javax.swing.DefaultComboBoxModel<>(new String[] { "EFECTIVO", "TARJETA", "TRANSFERENCIA" }));
        pnlPago.add(cmbMetodoPago);
        cmbMetodoPago.setBounds(20, 155, 310, 35);

        lblDoc = new javax.swing.JLabel();
        lblDoc.setForeground(new java.awt.Color(160, 160, 160));
        lblDoc.setText("COMPROBANTE:");
        pnlPago.add(lblDoc);
        lblDoc.setBounds(20, 195, 120, 16);

        cmbTipoComprobante = new javax.swing.JComboBox<>(new String[] { "BOLETA", "FACTURA" });
        pnlPago.add(cmbTipoComprobante);
        cmbTipoComprobante.setBounds(20, 215, 310, 35);

        btnVender.setBackground(new java.awt.Color(50, 100, 255));
        btnVender.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnVender.setForeground(new java.awt.Color(255, 255, 255));
        btnVender.setText("FINALIZAR PAGO");
        btnVender.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderActionPerformed(evt);
            }
        });
        pnlPago.add(btnVender);
        btnVender.setBounds(20, 265, 310, 60);

        pnlCarrito.add(pnlPago, java.awt.BorderLayout.SOUTH);
        pnlPago.setPreferredSize(new java.awt.Dimension(350, 340));

        add(pnlCarrito, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtBuscarKeyReleased
        filtrarProductos(txtBuscar.getText().trim());
    }// GEN-LAST:event_txtBuscarKeyReleased

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRefrescarActionPerformed
        DataStore.getInstance().refrescarProductos();
        refrescarCatalogo();
    }// GEN-LAST:event_btnRefrescarActionPerformed

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnVenderActionPerformed
        if (controller.getCarrito().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El carrito esta vacio.");
            return;
        }

        Cliente seleccionado = (Cliente) cmbCliente.getSelectedItem();
        String metodo = cmbMetodoPago.getSelectedItem().toString();
        String tipoDoc = cmbTipoComprobante.getSelectedItem().toString();

        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "Selecciona un cliente.");
            return;
        }

        if (controller.finalizarVenta(seleccionado, usuarioLogueado.getEmpleado(), metodo, tipoDoc)) {
            JOptionPane.showMessageDialog(this, "Venta Exitosa!\nComprobante generado: " + tipoDoc);
            actualizarCarrito();
            DataStore.getInstance().refrescarProductos();
            refrescarCatalogo();
        }
    }// GEN-LAST:event_btnVenderActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JButton btnVender;
    private javax.swing.JComboBox<com.novatech.proyectofinal.model.Cliente> cmbCliente;
    private javax.swing.JComboBox<String> cmbMetodoPago;
    private javax.swing.JComboBox<String> cmbTipoComprobante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCli;
    private javax.swing.JLabel lblMet;
    private javax.swing.JLabel lblDoc;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlBusqueda;
    private javax.swing.JPanel pnlCarrito;
    private javax.swing.JPanel pnlCategorias;
    private javax.swing.JPanel pnlCentral;
    private javax.swing.JPanel pnlGridProductos;
    private javax.swing.JPanel pnlGridWrapper;
    private javax.swing.JPanel pnlPago;
    private javax.swing.JPanel pnlTituloCarrito;
    private javax.swing.JTable tablaCarrito;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}

package com.novatech.proyectofinal.view;

import com.novatech.proyectofinal.model.Usuario;
import com.novatech.proyectofinal.util.DataStore;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.function.Consumer;
import javax.swing.*;

/**
 * Frame principal del sistema.
 * 
 * @author rufernecall
 */
public class MainFrame extends javax.swing.JFrame {

    private CardLayout cardLayout;
    private Usuario usuarioLogueado;

    public MainFrame(Usuario usuario, Consumer<String> progress) {
        this.usuarioLogueado = usuario;

        initComponents();
        cardLayout = (CardLayout) contentPanel.getLayout();

        // Titulo limpio
        setTitle("Novatech ERP");
        setLocationRelativeTo(null);

        // Sincronizamos la data con Supabase
        if (progress != null)
            progress.accept("Cargando datos...");
        DataStore.getInstance().init(progress);

        cargarPaneles();
        configurarPermisos();
        configurarHotkeys();

        // ir al dashboard
        showPanel("dashboard");
    }

    private void configurarHotkeys() {
        // F1 -> Dashboard
        getRootPane().registerKeyboardAction(e -> showPanel("dashboard"),
                KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        // F2 -> Ventas (POS)
        getRootPane().registerKeyboardAction(e -> showPanel("ventas"),
                KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        // F3 -> Inventario
        getRootPane().registerKeyboardAction(e -> showPanel("inventario"),
                KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        // F4 -> Reportes
        getRootPane().registerKeyboardAction(e -> showPanel("reportes"),
                KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private void cargarPaneles() {
        String rol = usuarioLogueado.getRol();

        // Paneles comunes o base
        contentPanel.add(new DashboardPanel(usuarioLogueado), "dashboard");

        // Carga condicional segun Rol
        if (rol.equals("ADMIN") || rol.equals("VENDEDOR")) {
            contentPanel.add(new VentasPanel(usuarioLogueado), "ventas");
            contentPanel.add(new ClientesPanel(), "clientes");
        }

        if (rol.equals("ADMIN") || rol.equals("ALMACENERO") || rol.equals("GERENTE")) {
            contentPanel.add(new InventarioPanel(usuarioLogueado), "inventario");
            contentPanel.add(new MovimientosPanel(), "movimientos");
            contentPanel.add(new CategoriasPanel(), "categorias");
            contentPanel.add(new ProveedoresPanel(), "proveedores");
        }

        if (rol.equals("ADMIN") || rol.equals("GERENTE")) {
            contentPanel.add(new ReportesPanel(), "reportes");
            contentPanel.add(new FinanzasPanel(), "finanzas");
        }

        if (rol.equals("ADMIN")) {
            contentPanel.add(new UsuariosPanel(), "usuarios");
            contentPanel.add(new EmpleadosPanel(), "empleados");
        }
    }

    private void configurarPermisos() {
        String rol = usuarioLogueado.getRol();
        boolean isAdmin = "ADMIN".equals(rol);
        boolean isVendedor = "VENDEDOR".equals(rol);
        boolean isAlmacenero = "ALMACENERO".equals(rol);
        boolean isGerente = "GERENTE".equals(rol);

        // Menu Almacen
        menuAlmacen.setVisible(isAdmin || isAlmacenero || isGerente);
        itemMovimientos.setVisible(isAdmin || isAlmacenero || isGerente);

        // Menu Ventas
        menuVentas.setVisible(isAdmin || isVendedor || isGerente);
        itemPos.setVisible(isAdmin || isVendedor);
        itemDashboard.setVisible(isAdmin || isGerente || isVendedor);
        itemReportes.setVisible(isAdmin || isGerente);

        // Menu Finanzas
        menuFinanzas.setVisible(isAdmin || isGerente);

        // Menu Entidades
        menuEntidades.setVisible(isAdmin || isVendedor || isAlmacenero);
        itemClientes.setVisible(isAdmin || isVendedor);
        itemProveedores.setVisible(isAdmin || isAlmacenero);
        itemEmpleados.setVisible(isAdmin); // Solo admin gestiona personal

        // Menu Sistema
        itemUsuarios.setVisible(isAdmin);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        menuAlmacen = new javax.swing.JMenu();
        itemInventario = new javax.swing.JMenuItem();
        itemMovimientos = new javax.swing.JMenuItem();
        itemCategorias = new javax.swing.JMenuItem();
        menuVentas = new javax.swing.JMenu();
        itemPos = new javax.swing.JMenuItem();
        itemDashboard = new javax.swing.JMenuItem();
        menuEntidades = new javax.swing.JMenu();
        itemClientes = new javax.swing.JMenuItem();
        itemEmpleados = new javax.swing.JMenuItem();
        itemProveedores = new javax.swing.JMenuItem();
        menuSistema = new javax.swing.JMenu();
        itemUsuarios = new javax.swing.JMenuItem();
        itemLogout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        contentPanel.setBackground(new java.awt.Color(20, 20, 25));
        contentPanel.setLayout(new java.awt.CardLayout());

        menuAlmacen.setText("Almacen");

        itemInventario.setText("Inventario Central");
        itemInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemInventarioActionPerformed(evt);
            }
        });
        menuAlmacen.add(itemInventario);

        itemMovimientos.setText("Historial de Movimientos");
        itemMovimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPanel("movimientos");
            }
        });
        menuAlmacen.add(itemMovimientos);

        itemCategorias.setText("Categorias");
        itemCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCategoriasActionPerformed(evt);
            }
        });
        menuAlmacen.add(itemCategorias);

        menuBar.add(menuAlmacen);

        menuVentas.setText("Ventas");

        itemPos.setText("Punto de Venta (POS)");
        itemPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPosActionPerformed(evt);
            }
        });
        menuVentas.add(itemPos);

        itemDashboard.setText("Estadisticas");
        itemDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDashboardActionPerformed(evt);
            }
        });
        menuVentas.add(itemDashboard);

        itemReportes = new javax.swing.JMenuItem();
        itemReportes.setText("Reportes Historicos");
        itemReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemReportesActionPerformed(evt);
            }
        });
        menuVentas.add(itemReportes);

        menuBar.add(menuVentas);

        menuFinanzas = new javax.swing.JMenu();
        menuFinanzas.setText("Finanzas");

        itemComprobantes = new javax.swing.JMenuItem();
        itemComprobantes.setText("Ver Facturas/Boletas");
        itemComprobantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardLayout.show(contentPanel, "finanzas");
            }
        });
        menuFinanzas.add(itemComprobantes);

        menuBar.add(menuFinanzas);

        menuEntidades.setText("Entidades");

        itemClientes.setText("Clientes");
        itemClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemClientesActionPerformed(evt);
            }
        });
        menuEntidades.add(itemClientes);

        itemEmpleados.setText("Personal");
        itemEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEmpleadosActionPerformed(evt);
            }
        });
        menuEntidades.add(itemEmpleados);

        itemProveedores.setText("Proveedores");
        itemProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemProveedoresActionPerformed(evt);
            }
        });
        menuEntidades.add(itemProveedores);

        menuBar.add(menuEntidades);

        menuSistema.setText("Sistema");

        itemUsuarios.setText("Usuarios");
        itemUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemUsuariosActionPerformed(evt);
            }
        });
        menuSistema.add(itemUsuarios);

        itemLogout.setText("Cerrar Sesion");
        itemLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLogoutActionPerformed(evt);
            }
        });
        menuSistema.add(itemLogout);

        menuBar.add(menuSistema);

        setJMenuBar(menuBar);

        pnlStatus = new javax.swing.JPanel();
        lblStatusUser = new javax.swing.JLabel();
        lblStatusRole = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        contentPanel.setBackground(new java.awt.Color(20, 20, 25));
        contentPanel.setLayout(new java.awt.CardLayout());

        pnlStatus.setBackground(new java.awt.Color(15, 15, 20));
        pnlStatus.setPreferredSize(new java.awt.Dimension(1200, 30));
        pnlStatus.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 5));

        lblStatusUser.setForeground(new java.awt.Color(180, 180, 180));
        lblStatusUser.setText("USUARIO: " + usuarioLogueado.getUsername());
        pnlStatus.add(lblStatusUser);

        lblStatusRole.setForeground(new java.awt.Color(180, 180, 180));
        lblStatusRole.setText("ROL: " + usuarioLogueado.getRol());
        pnlStatus.add(lblStatusRole);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
                        .addComponent(pnlStatus, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                                .addComponent(pnlStatus, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void showPanel(String name) {
        cardLayout.show(contentPanel, name);

        // Buscamos el componente que se esta mostrando para refrescar sus datos
        for (java.awt.Component comp : contentPanel.getComponents()) {
            if (comp.isVisible() && comp instanceof ViewPanel) {
                ((ViewPanel) comp).alCargar();
                break;
            }
        }
    }

    private void itemInventarioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_itemInventarioActionPerformed
        showPanel("inventario");
    }// GEN-LAST:event_itemInventarioActionPerformed

    private void itemCategoriasActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_itemCategoriasActionPerformed
        showPanel("categorias");
    }// GEN-LAST:event_itemCategoriasActionPerformed

    private void itemPosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_itemPosActionPerformed
        showPanel("ventas");
    }// GEN-LAST:event_itemPosActionPerformed

    private void itemDashboardActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_itemDashboardActionPerformed
        showPanel("dashboard");
    }// GEN-LAST:event_itemDashboardActionPerformed

    private void itemClientesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_itemClientesActionPerformed
        showPanel("clientes");
    }// GEN-LAST:event_itemClientesActionPerformed

    private void itemEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_itemEmpleadosActionPerformed
        showPanel("empleados");
    }// GEN-LAST:event_itemEmpleadosActionPerformed

    private void itemProveedoresActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_itemProveedoresActionPerformed
        showPanel("proveedores");
    }// GEN-LAST:event_itemProveedoresActionPerformed

    private void itemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_itemUsuariosActionPerformed
        showPanel("usuarios");
    }// GEN-LAST:event_itemUsuariosActionPerformed

    private void itemReportesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_itemReportesActionPerformed
        showPanel("reportes");
    }// GEN-LAST:event_itemReportesActionPerformed

    private void itemLogoutActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_itemLogoutActionPerformed
        dispose();
        new LoginFrame().setVisible(true);
    }// GEN-LAST:event_itemLogoutActionPerformed

    public MainFrame(Usuario usuario) {
        this(usuario, null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JMenuItem itemCategorias;
    private javax.swing.JMenuItem itemMovimientos;
    private javax.swing.JMenuItem itemClientes;
    private javax.swing.JMenuItem itemDashboard;
    private javax.swing.JMenuItem itemEmpleados;
    private javax.swing.JMenuItem itemInventario;
    private javax.swing.JMenuItem itemLogout;
    private javax.swing.JMenuItem itemPos;
    private javax.swing.JMenuItem itemReportes;
    private javax.swing.JMenuItem itemProveedores;
    private javax.swing.JMenuItem itemUsuarios;
    private javax.swing.JMenu menuAlmacen;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuEntidades;
    private javax.swing.JMenu menuFinanzas;
    private javax.swing.JMenuItem itemComprobantes;
    private javax.swing.JMenu menuSistema;
    private javax.swing.JMenu menuVentas;
    private javax.swing.JPanel pnlStatus;
    private javax.swing.JLabel lblStatusUser;
    private javax.swing.JLabel lblStatusRole;
    // End of variables declaration//GEN-END:variables
}

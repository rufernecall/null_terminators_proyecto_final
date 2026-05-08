package com.temporal.proyectofinal.view;

import com.temporal.proyectofinal.model.Usuario;
import com.temporal.proyectofinal.util.DataStore;
import java.awt.CardLayout;
import java.util.function.Consumer;
import javax.swing.*;

/**
 * Frame principal compatible con NetBeans Designer
 * @author rufernecall
 */
public class MainFrame extends javax.swing.JFrame {

    private CardLayout cardLayout;
    private Usuario usuarioLogueado;

    public MainFrame(Usuario usuario, Consumer<String> progress) {
        this.usuarioLogueado = usuario;
        
        initComponents();
        cardLayout = (CardLayout) contentPanel.getLayout();
        
        // Titulo Corporativo Dinamico
        setTitle("ERP & POS System | Rol: " + usuario.getRol() + " | Usuario: " + usuario.getUsername());
        setLocationRelativeTo(null);
        
        // Sincronizamos la data con Supabase
        if (progress != null) progress.accept("Sincronizando Base de Datos...");
        DataStore.getInstance().init(progress);

        cargarPaneles();
        configurarPermisos();
        
        // Arrancamos en el dashboard
        cardLayout.show(contentPanel, "dashboard");
    }

    private void cargarPaneles() {
        String rol = usuarioLogueado.getRol();
        
        // Paneles comunes o base
        contentPanel.add(new DashboardPanel(), "dashboard");
        
        // Carga condicional segun Rol
        if (rol.equals("ADMIN") || rol.equals("VENDEDOR")) {
            contentPanel.add(new VentasPanel(usuarioLogueado), "ventas");
            contentPanel.add(new ClientesPanel(), "clientes");
        }
        
        if (rol.equals("ADMIN") || rol.equals("ALMACENERO")) {
            contentPanel.add(new InventarioPanel(), "inventario");
            contentPanel.add(new CategoriasPanel(), "categorias");
            contentPanel.add(new ProveedoresPanel(), "proveedores");
        }
        
        if (rol.equals("ADMIN") || rol.equals("GERENTE")) {
            contentPanel.add(new ReportesPanel(), "reportes");
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
        menuAlmacen.setVisible(isAdmin || isAlmacenero);
        
        // Menu Ventas
        menuVentas.setVisible(isAdmin || isVendedor || isGerente);
        itemPos.setVisible(isAdmin || isVendedor);
        itemDashboard.setVisible(isAdmin || isGerente || isVendedor);
        
        // Menu Entidades
        menuEntidades.setVisible(isAdmin || isVendedor || isAlmacenero);
        itemClientes.setVisible(isAdmin || isVendedor);
        itemProveedores.setVisible(isAdmin || isAlmacenero);
        itemEmpleados.setVisible(isAdmin); // Solo admin gestiona personal
        
        // Menu Sistema
        itemUsuarios.setVisible(isAdmin);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        menuAlmacen = new javax.swing.JMenu();
        itemInventario = new javax.swing.JMenuItem();
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

        menuBar.add(menuVentas);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemInventarioActionPerformed
        cardLayout.show(contentPanel, "inventario");
    }//GEN-LAST:event_itemInventarioActionPerformed

    private void itemCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCategoriasActionPerformed
        cardLayout.show(contentPanel, "categorias");
    }//GEN-LAST:event_itemCategoriasActionPerformed

    private void itemPosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPosActionPerformed
        cardLayout.show(contentPanel, "ventas");
    }//GEN-LAST:event_itemPosActionPerformed

    private void itemDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDashboardActionPerformed
        cardLayout.show(contentPanel, "dashboard");
    }//GEN-LAST:event_itemDashboardActionPerformed

    private void itemClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemClientesActionPerformed
        cardLayout.show(contentPanel, "clientes");
    }//GEN-LAST:event_itemClientesActionPerformed

    private void itemEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEmpleadosActionPerformed
        cardLayout.show(contentPanel, "empleados");
    }//GEN-LAST:event_itemEmpleadosActionPerformed

    private void itemProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemProveedoresActionPerformed
        cardLayout.show(contentPanel, "proveedores");
    }//GEN-LAST:event_itemProveedoresActionPerformed

    private void itemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemUsuariosActionPerformed
        cardLayout.show(contentPanel, "usuarios");
    }//GEN-LAST:event_itemUsuariosActionPerformed

    private void itemLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLogoutActionPerformed
        dispose();
        new LoginFrame().setVisible(true);
    }//GEN-LAST:event_itemLogoutActionPerformed

    public MainFrame(Usuario usuario) {
        this(usuario, null);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JMenuItem itemCategorias;
    private javax.swing.JMenuItem itemClientes;
    private javax.swing.JMenuItem itemDashboard;
    private javax.swing.JMenuItem itemEmpleados;
    private javax.swing.JMenuItem itemInventario;
    private javax.swing.JMenuItem itemLogout;
    private javax.swing.JMenuItem itemPos;
    private javax.swing.JMenuItem itemProveedores;
    private javax.swing.JMenuItem itemUsuarios;
    private javax.swing.JMenu menuAlmacen;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuEntidades;
    private javax.swing.JMenu menuSistema;
    private javax.swing.JMenu menuVentas;
    // End of variables declaration//GEN-END:variables
}

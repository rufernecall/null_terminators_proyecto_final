package com.temporal.proyectofinal.view;

import com.temporal.proyectofinal.dao.ClienteDAO;
import com.temporal.proyectofinal.model.Cliente;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Panel de Clientes con conexion directa a Supabase y CRUD funcional
 * @author rufernecall
 */
public class ClientesPanel extends javax.swing.JPanel {

    private ClienteDAO cDAO;
    private DefaultTableModel modelo;
    private List<Cliente> listaActual;

    public ClientesPanel() {
        initComponents();
        cDAO = new ClienteDAO();
        modelo = (DefaultTableModel) tablaClientes.getModel();
        listarClientes("");
    }
    
    private void listarClientes(String filtro) {
        try {
            modelo.setRowCount(0);
            listaActual = cDAO.listar(); 
            for (Cliente c : listaActual) {
                if (c.getNombre().toLowerCase().contains(filtro.toLowerCase()) || 
                    c.getDocumentoIdentidad().toLowerCase().contains(filtro.toLowerCase())) {
                    modelo.addRow(new Object[]{
                        c.getId(),
                        c.getDocumentoIdentidad(),
                        c.getNombre(),
                        c.getTelefono(),
                        c.getEmail()
                    });
                }
            }
        } catch (Exception e) {
            System.out.println("error listando clientes: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();

        setBackground(new java.awt.Color(30, 30, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Directorio de Clientes");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(40, 167, 69));
        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("+ Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(88, 157, 246));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(125, 58, 58));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID", "DNI / RUC", "Nombre", "Telefono", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaClientes);

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
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {
        listarClientes(txtBuscar.getText());
    }

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {
        ClienteForm form = new ClienteForm(null, null);
        form.setVisible(true);
        if (form.isGuardado()) listarClientes("");
    }

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tablaClientes.getSelectedRow();
        if (row != -1) {
            Long id = (Long) tablaClientes.getValueAt(row, 0);
            Cliente c = listaActual.stream().filter(cl -> cl.getId().equals(id)).findFirst().orElse(null);
            if (c != null) {
                ClienteForm form = new ClienteForm(null, c);
                form.setVisible(true);
                if (form.isGuardado()) listarClientes("");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente.");
        }
    }

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tablaClientes.getSelectedRow();
        if (row != -1) {
            if (JOptionPane.showConfirmDialog(this, "¿Eliminar cliente?") == JOptionPane.YES_OPTION) {
                Long id = (Long) tablaClientes.getValueAt(row, 0);
                if (cDAO.eliminar(id)) listarClientes("");
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}

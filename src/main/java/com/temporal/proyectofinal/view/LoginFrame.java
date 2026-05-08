package com.temporal.proyectofinal.view;

import com.temporal.proyectofinal.dao.UsuarioDAO;
import com.temporal.proyectofinal.model.Usuario;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Pantalla de login moderna y redondeada compatible con NetBeans Designer
 * @author rufernecall
 */
public class LoginFrame extends JFrame {

    private int xMouse, yMouse;
    private UsuarioDAO usuarioDAO;

    public LoginFrame() {
        this.usuarioDAO = new UsuarioDAO();
        initComponents();
        txtUser.putClientProperty("JTextField.placeholderText", "Ingrese su usuario...");
        txtPass.putClientProperty("JTextField.placeholderText", "••••••••");
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
            }
        };
        titleBar = new javax.swing.JPanel();
        lblCerrar = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblSub = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lblPass = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        lblStatus = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        mainPanel.setBackground(new java.awt.Color(20, 20, 28));
        mainPanel.setPreferredSize(new java.awt.Dimension(400, 520));
        mainPanel.setLayout(null);

        titleBar.setOpaque(false);
        titleBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                titleBarMouseDragged(evt);
            }
        });
        titleBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                titleBarMousePressed(evt);
            }
        });
        mainPanel.add(titleBar);
        titleBar.setBounds(0, 0, 400, 40);

        lblCerrar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCerrar.setForeground(new java.awt.Color(128, 128, 128));
        lblCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCerrar.setText("×");
        lblCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarMouseClicked(evt);
            }
        });
        mainPanel.add(lblCerrar);
        lblCerrar.setBounds(355, 10, 30, 30);

        lblLogo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setText("TEMPORAL");
        mainPanel.add(lblLogo);
        lblLogo.setBounds(0, 70, 400, 40);

        lblSub.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSub.setForeground(new java.awt.Color(50, 120, 255));
        lblSub.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub.setText("SISTEMA POS CLOUD");
        mainPanel.add(lblSub);
        lblSub.setBounds(0, 110, 400, 20);

        lblUser.setForeground(new java.awt.Color(150, 150, 160));
        lblUser.setText("USUARIO");
        mainPanel.add(lblUser);
        lblUser.setBounds(50, 180, 300, 20);
        mainPanel.add(txtUser);
        txtUser.setBounds(50, 205, 300, 45);

        lblPass.setForeground(new java.awt.Color(150, 150, 160));
        lblPass.setText("CONTRASEÑA");
        mainPanel.add(lblPass);
        lblPass.setBounds(50, 270, 300, 20);
        mainPanel.add(txtPass);
        txtPass.setBounds(50, 295, 300, 45);

        lblStatus.setForeground(new java.awt.Color(50, 120, 255));
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainPanel.add(lblStatus);
        lblStatus.setBounds(50, 350, 300, 20);

        btnIngresar.setBackground(new java.awt.Color(50, 100, 255));
        btnIngresar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setText("ACCEDER AL SISTEMA");
        btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        mainPanel.add(btnIngresar);
        btnIngresar.setBounds(50, 385, 300, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblCerrarMouseClicked

    private void titleBarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleBarMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_titleBarMousePressed

    private void titleBarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleBarMouseDragged
        setLocation(evt.getXOnScreen() - xMouse, evt.getYOnScreen() - yMouse);
    }//GEN-LAST:event_titleBarMouseDragged

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        iniciarLogin();
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void iniciarLogin() {
        String user = txtUser.getText();
        String pass = new String(txtPass.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa los campos porfa.");
            return;
        }

        btnIngresar.setEnabled(false);
        txtUser.setEditable(false);
        txtPass.setEditable(false);

        SwingWorker<MainFrame, String> worker = new SwingWorker<>() {
            @Override
            protected MainFrame doInBackground() throws Exception {
                publish("Chequeando credenciales...");
                Usuario u = usuarioDAO.login(user, pass);
                if (u == null) return null;

                publish("Cargando el almacen...");
                return new MainFrame(u, msg -> publish(msg));
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                lblStatus.setText(chunks.get(chunks.size() - 1));
            }

            @Override
            protected void done() {
                try {
                    MainFrame main = get();
                    if (main != null) {
                        main.setVisible(true);
                        dispose();
                    } else {
                        lblStatus.setText("");
                        JOptionPane.showMessageDialog(LoginFrame.this, "Datos incorrectos.");
                        btnIngresar.setEnabled(true);
                        txtUser.setEditable(true);
                        txtPass.setEditable(true);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    btnIngresar.setEnabled(true);
                }
            }
        };
        worker.execute();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel lblCerrar;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSub;
    private javax.swing.JLabel lblUser;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel titleBar;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}

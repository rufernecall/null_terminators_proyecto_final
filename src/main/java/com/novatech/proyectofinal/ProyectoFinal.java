package com.novatech.proyectofinal;

import com.formdev.flatlaf.FlatDarkLaf;
import com.novatech.proyectofinal.view.LoginFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Color;

/**
 * Clase principal del proyecto.
 * 
 * @author rufernecall
 */
public class ProyectoFinal {

    public static void main(String[] args) {
        configurarLaf();

        // Cargar conexion en segundo plano
        new Thread(() -> {
            com.novatech.proyectofinal.dao.DatabaseConnection.getConnection();
        }).start();

        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }

    private static void configurarLaf() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());

            // Configuracion de bordes y redondeo
            UIManager.put("Component.arc", 15);
            UIManager.put("Button.arc", 15);
            UIManager.put("TextComponent.arc", 15);
            UIManager.put("CheckBox.arc", 5);
            UIManager.put("ProgressBar.arc", 999);
            UIManager.put("ScrollBar.thumbArc", 999);

            UIManager.put("Component.focusWidth", 1);
            UIManager.put("Button.innerFocusWidth", 0);

            UIManager.put("ScrollBar.track", new Color(30, 30, 35));
            UIManager.put("TabbedPane.selectedBackground", new Color(50, 100, 255));

        } catch (Exception ex) {
            System.err.println("Error al cargar el tema: " + ex.getMessage());
        }
    }
}

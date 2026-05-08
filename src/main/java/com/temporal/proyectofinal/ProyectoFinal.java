package com.temporal.proyectofinal;

import com.formdev.flatlaf.FlatDarkLaf;
import com.temporal.proyectofinal.view.LoginFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Color;

/**
 * Punto de entrada con Estetica Premium (Bordes redondeados y profundidad)
 * @author rufernecall
 */
public class ProyectoFinal {

    public static void main(String[] args) {
        configurarEstetica();
        
        // --- CALENTAMIENTO (WARM-UP) EN SEGUNDO PLANO ---
        // Iniciamos la conexion a la DB mientras carga la UI del Login
        new Thread(() -> {
            System.out.println(">>> Calentando motores: Iniciando conexion con Supabase...");
            com.temporal.proyectofinal.dao.DatabaseConnection.getConnection();
        }).start();

        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }

    private static void configurarEstetica() {
        try {
            // Usamos FlatDarkLaf que es mas moderno que Darcula
            UIManager.setLookAndFeel(new FlatDarkLaf());
            
            // --- PROPIEDADES DE REDONDEO ---
            UIManager.put("Component.arc", 15);
            UIManager.put("Button.arc", 15);
            UIManager.put("TextComponent.arc", 15);
            UIManager.put("CheckBox.arc", 5);
            UIManager.put("ProgressBar.arc", 999);
            UIManager.put("ScrollBar.thumbArc", 999);
            
            // --- PROFUNDIDAD Y SOMBRAS  ---
            UIManager.put("Component.focusWidth", 1);
            UIManager.put("Button.innerFocusWidth", 0);
            
            // --- PERSONALIZACION DE COLORES  ---
            UIManager.put("ScrollBar.track", new Color(30, 30, 35));
            UIManager.put("TabbedPane.selectedBackground", new Color(50, 100, 255));
            
        } catch (Exception ex) {
            System.err.println("Fallo al aplicar estetica premium.");
        }
    }
}

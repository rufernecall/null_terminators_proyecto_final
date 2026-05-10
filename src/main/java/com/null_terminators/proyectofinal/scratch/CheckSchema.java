package com.null_terminators.proyectofinal.scratch;

import com.null_terminators.proyectofinal.dao.DatabaseConnection;
import java.sql.*;

public class CheckSchema {
    public static void main(String[] args) {
        String sql = "SELECT table_schema, column_name, data_type FROM information_schema.columns WHERE table_name = 'detalles_venta'";
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("Columnas de detalles_venta por esquema:");
            while (rs.next()) {
                System.out.println("[" + rs.getString("table_schema") + "] - " + rs.getString("column_name") + " (" + rs.getString("data_type") + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

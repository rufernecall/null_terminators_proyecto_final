package com.temporal.proyectofinal.controller;

import com.temporal.proyectofinal.dao.DatabaseConnection;
import com.temporal.proyectofinal.dao.VentasDAO;
import com.temporal.proyectofinal.model.OrdenVenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador para la generacion de reportes y estadisticas
 * @author rufernecall
 */
public class ReporteController {

    private final VentasDAO ventasDAO;

    public ReporteController() {
        this.ventasDAO = new VentasDAO();
    }

    /**
     * Obtiene las estadisticas generales del sistema mediante el SP de Postgres
     */
    public Map<String, Object> getEstadisticasGenerales() {
        Map<String, Object> stats = new HashMap<>();
        try {
            Connection con = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM get_dashboard_stats()";
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    stats.put("ventasHoy", rs.getDouble("ventas_hoy"));
                    stats.put("stockTotal", rs.getLong("stock_total"));
                    stats.put("clientesTotal", rs.getLong("clientes_total"));
                    stats.put("stockCritico", rs.getLong("stock_critico"));
                }
            }
        } catch (Exception e) {
            System.err.println("Error en ReporteController.getEstadisticas: " + e.getMessage());
        }
        return stats;
    }

    /**
     * Obtiene las ultimas ventas para el dashboard
     */
    public List<OrdenVenta> getUltimasVentas(int limite) {
        return ventasDAO.getUltimasVentas(limite);
    }

    public List<OrdenVenta> listarVentas() {
        return ventasDAO.listar();
    }
}

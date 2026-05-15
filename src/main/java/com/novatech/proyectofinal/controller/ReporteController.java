package com.novatech.proyectofinal.controller;

import com.novatech.proyectofinal.dao.DatabaseConnection;
import com.novatech.proyectofinal.dao.VentasDAO;
import com.novatech.proyectofinal.model.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador para los reportes.
 * 
 * @author rufernecall
 */
public class ReporteController {

    private final VentasDAO ventasDAO;

    public ReporteController() {
        this.ventasDAO = new VentasDAO();
    }

    // Obtener estadisticas del dashboard
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

    // Ultimas ventas
    public List<Venta> getUltimasVentas(int limite) {
        return ventasDAO.getUltimasVentas(limite);
    }

    public List<Venta> listarVentas() {
        return ventasDAO.listar();
    }

    // Top 10 productos
    public Map<String, Integer> getTopProductos() {
        Map<String, Integer> data = new HashMap<>();
        String sql = "SELECT p.nombre, SUM(dv.cantidad) as total " +
                     "FROM detalles_venta dv " +
                     "JOIN productos p ON dv.producto_id = p.id " +
                     "GROUP BY p.nombre " +
                     "ORDER BY total DESC LIMIT 10";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                data.put(rs.getString("nombre"), rs.getInt("total"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return data;
    }

    // Ventas por vendedor
    public Map<String, Double> getVentasPorVendedor() {
        Map<String, Double> data = new HashMap<>();
        String sql = "SELECT e.nombre, SUM(v.total) as total " +
                     "FROM ventas v " +
                     "JOIN empleados e ON v.vendedor_id = e.id " +
                     "GROUP BY e.nombre " +
                     "ORDER BY total DESC";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                data.put(rs.getString("nombre"), rs.getDouble("total"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return data;
    }
}

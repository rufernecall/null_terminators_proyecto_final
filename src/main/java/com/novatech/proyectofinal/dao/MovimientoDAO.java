package com.novatech.proyectofinal.dao;

import com.novatech.proyectofinal.model.Movimiento;
import com.novatech.proyectofinal.model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la tabla 'movimientos' existente en la base de datos.
 * @author rufernecall
 */
public class MovimientoDAO {

    public List<Movimiento> listarTodo() {
        List<Movimiento> lista = new ArrayList<>();
        String sql = "SELECT m.*, p.nombre as producto_nombre " +
                     "FROM movimientos m " +
                     "JOIN productos p ON m.producto_id = p.id " +
                     "ORDER BY m.fecha DESC";

        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Movimiento m = new Movimiento();
                m.setId(rs.getLong("id"));
                m.setCantidad(rs.getInt("cantidad"));
                m.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                m.setMotivo(rs.getString("motivo"));
                m.setTipo(rs.getString("tipo"));
                
                Producto p = new Producto();
                p.setId(rs.getLong("producto_id"));
                p.setNombre(rs.getString("producto_nombre"));
                m.setProducto(p);
                
                lista.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar movimientos de la tabla: " + e.getMessage());
        }
        return lista;
    }
}

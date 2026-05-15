package com.novatech.proyectofinal.dao;

import com.novatech.proyectofinal.model.Comprobante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la gestion de Comprobantes y Correlativos
 * 
 * @author rufernecall
 */
public class FinanzasDAO {

    public int obtenerSiguienteNumero(String tipo, String serie) {
        String sql = "SELECT COALESCE(MAX(numero), 0) + 1 FROM comprobantes WHERE tipo = ? AND serie = ?";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tipo);
            ps.setString(2, serie);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener correlativo: " + e.getMessage());
        }
        return 1;
    }

    public boolean registrarComprobante(Comprobante c) {
        String sql = "INSERT INTO comprobantes (tipo, serie, numero, fecha, monto_total, estado, venta_id, compra_id) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getTipo());
            ps.setString(2, c.getSerie());
            ps.setInt(3, c.getNumero());
            ps.setTimestamp(4, Timestamp.valueOf(c.getFecha()));
            ps.setDouble(5, c.getMontoTotal());
            ps.setString(6, c.getEstado());

            if (c.getVentaId() != null)
                ps.setLong(7, c.getVentaId());
            else
                ps.setNull(7, Types.BIGINT);

            if (c.getCompraId() != null)
                ps.setLong(8, c.getCompraId());
            else
                ps.setNull(8, Types.BIGINT);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar comprobante: " + e.getMessage());
            return false;
        }
    }

    public List<Comprobante> listar() {
        List<Comprobante> lista = new ArrayList<>();
        String sql = "SELECT * FROM comprobantes ORDER BY fecha DESC";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Comprobante c = new Comprobante();
                c.setId(rs.getLong("id"));
                c.setTipo(rs.getString("tipo"));
                c.setSerie(rs.getString("serie"));
                c.setNumero(rs.getInt("numero"));
                c.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                c.setMontoTotal(rs.getDouble("monto_total"));
                c.setEstado(rs.getString("estado"));
                
                long vId = rs.getLong("venta_id");
                if (!rs.wasNull()) c.setVentaId(vId);
                
                long cId = rs.getLong("compra_id");
                if (!rs.wasNull()) c.setCompraId(cId);
                
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar comprobantes: " + e.getMessage());
        }
        return lista;
    }
}

package com.temporal.proyectofinal.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.temporal.proyectofinal.model.DetalleOrdenVenta;
import com.temporal.proyectofinal.model.OrdenVenta;
import com.temporal.proyectofinal.model.Cliente;
import com.temporal.proyectofinal.model.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de Ventas optimizado con Stored Procedures (registrar_venta_pro)
 * @author rufernecall
 */
public class VentasDAO {
    
    public boolean registrarVenta(OrdenVenta orden, List<DetalleOrdenVenta> detalles) {
        // Preparamos los detalles en formato JSON para el SP
        JsonArray detallesJson = new JsonArray();
        for (DetalleOrdenVenta d : detalles) {
            JsonObject item = new JsonObject();
            item.addProperty("producto_id", d.getProducto().getId());
            item.addProperty("cantidad", d.getCantidad());
            item.addProperty("precio_unitario", d.getPrecioUnitario());
            item.addProperty("subtotal", d.getSubtotal());
            detallesJson.add(item);
        }

        // Llamamos al SP que hace todo en un solo paso
        String sql = "SELECT registrar_venta_pro(?, ?, ?, ?::jsonb)";
        
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, orden.getCliente().getId());
            
            if (orden.getVendedor() != null) ps.setLong(2, orden.getVendedor().getId());
            else ps.setNull(2, Types.BIGINT);
            
            ps.setDouble(3, orden.getTotal());
            ps.setString(4, detallesJson.toString());
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    orden.setId(rs.getLong(1)); // Guardamos el ID generado
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("error al registrar venta pro: " + e.getMessage());
        }
        return false;
    }

    // Listar para reportes (Sigue igual, pero ahora es mas rapido por el servidor)
    public List<OrdenVenta> listar() {
        List<OrdenVenta> lista = new ArrayList<>();
        String sql = "SELECT o.*, c.nombre as cliente_nombre, e.nombre as vendedor_nombre " +
                     "FROM ordenes_venta o " +
                     "JOIN clientes c ON o.cliente_id = c.id " +
                     "LEFT JOIN empleados e ON o.vendedor_id = e.id " +
                     "ORDER BY o.fecha DESC";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                OrdenVenta o = new OrdenVenta();
                o.setId(rs.getLong("id"));
                o.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                o.setTotal(rs.getDouble("total"));
                o.setEstado(rs.getString("estado"));
                
                Cliente cli = new Cliente();
                cli.setNombre(rs.getString("cliente_nombre"));
                o.setCliente(cli);
                
                if (rs.getString("vendedor_nombre") != null) {
                    Empleado emp = new Empleado();
                    emp.setNombre(rs.getString("vendedor_nombre"));
                    o.setVendedor(emp);
                }
                lista.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Ultimas ventas para el dashboard (Usa el limite para no saturar)
    public List<OrdenVenta> getUltimasVentas(int limite) {
        List<OrdenVenta> lista = new ArrayList<>();
        String sql = "SELECT o.*, c.nombre as cliente_nombre FROM ordenes_venta o " +
                     "JOIN clientes c ON o.cliente_id = c.id " +
                     "ORDER BY o.fecha DESC LIMIT ?";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, limite);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrdenVenta o = new OrdenVenta();
                    o.setId(rs.getLong("id"));
                    o.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                    o.setTotal(rs.getDouble("total"));
                    
                    Cliente cli = new Cliente();
                    cli.setNombre(rs.getString("cliente_nombre"));
                    o.setCliente(cli);
                    lista.add(o);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // El total del dia, ahora podriamos usar el SP del dashboard si quisieramos,
    // pero lo dejamos aca por si se necesita solo este dato.
    public double getTotalVentasDia() {
        String sql = "SELECT SUM(total) FROM ordenes_venta WHERE fecha::date = CURRENT_DATE";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}

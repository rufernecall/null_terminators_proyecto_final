package com.novatech.proyectofinal.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.novatech.proyectofinal.model.DetalleVenta;
import com.novatech.proyectofinal.model.Venta;
import com.novatech.proyectofinal.model.Cliente;
import com.novatech.proyectofinal.model.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para manejar las ventas.
 * 
 * @author rufernecall
 */
public class VentasDAO {

    public boolean registrarVenta(Venta orden, List<DetalleVenta> detalles) {
        // Preparamos los detalles en formato JSON para el SP
        JsonArray detallesJson = new JsonArray();
        for (DetalleVenta d : detalles) {
            JsonObject item = new JsonObject();
            item.addProperty("producto_id", d.getProducto().getId());
            item.addProperty("cantidad", d.getCantidad());
            item.addProperty("precio_unitario", d.getPrecioUnitario());
            item.addProperty("subtotal", d.getSubtotal());
            detallesJson.add(item);
        }

        // Llamamos al SP con casts explicitos para evitar ambiguedad de tipos
        String sql = "SELECT registrar_venta_pro(?, ?, ?::numeric, ?::jsonb, ?::text)";

        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, orden.getCliente().getId());

            if (orden.getVendedor() != null)
                ps.setLong(2, orden.getVendedor().getId());
            else
                ps.setNull(2, Types.BIGINT);

            ps.setDouble(3, orden.getTotal());
            ps.setString(4, detallesJson.toString());
            
            // Pasamos el tipo de documento para la generacion automatica
            ps.setString(5, (String) orden.getMetadata().getOrDefault("tipoDoc", "BOLETA"));

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

    public List<Venta> listar() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT o.*, c.nombre as cliente_nombre, e.nombre as vendedor_nombre " +
                "FROM ventas o " +
                "JOIN clientes c ON o.cliente_id = c.id " +
                "LEFT JOIN empleados e ON o.vendedor_id = e.id " +
                "ORDER BY o.fecha DESC";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Venta o = new Venta();
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
    public List<Venta> getUltimasVentas(int limite) {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT o.*, c.nombre as cliente_nombre FROM ventas o " +
                "JOIN clientes c ON o.cliente_id = c.id " +
                "ORDER BY o.fecha DESC LIMIT ?";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, limite);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Venta o = new Venta();
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

    public double getTotalVentasDia() {
        String sql = "SELECT SUM(total) FROM ventas WHERE fecha::date = CURRENT_DATE";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            if (rs.next())
                return rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public Venta buscarPorId(Long id) {
        String sql = "SELECT o.*, c.nombre as cliente_nombre, e.nombre as vendedor_nombre " +
                "FROM ventas o " +
                "JOIN clientes c ON o.cliente_id = c.id " +
                "LEFT JOIN empleados e ON o.vendedor_id = e.id " +
                "WHERE o.id = ?";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Venta o = new Venta();
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
                    return o;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DetalleVenta> listarDetalles(Long ventaId) {
        List<DetalleVenta> detalles = new ArrayList<>();
        String sql = "SELECT d.*, p.nombre as producto_nombre " +
                "FROM detalles_venta d " +
                "JOIN productos p ON d.producto_id = p.id " +
                "WHERE d.venta_id = ?";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, ventaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetalleVenta d = new DetalleVenta();
                    d.setId(rs.getLong("id"));
                    d.setCantidad(rs.getInt("cantidad"));
                    d.setPrecioUnitario(rs.getDouble("precio_unitario"));
                    d.setSubtotal(rs.getDouble("subtotal"));

                    com.novatech.proyectofinal.model.Producto p = new com.novatech.proyectofinal.model.Producto();
                    p.setId(rs.getLong("producto_id"));
                    p.setNombre(rs.getString("producto_nombre"));
                    d.setProducto(p);

                    detalles.add(d);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }
}

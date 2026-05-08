package com.temporal.proyectofinal.dao;

import com.temporal.proyectofinal.model.Categoria;
import com.temporal.proyectofinal.model.Producto;
import com.temporal.proyectofinal.model.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para Productos con contadores de Stock Real y Valorizacion
 * @author rufernecall
 */
public class ProductoDAO {

    public List<Producto> listar() {
        return buscar("");
    }

    public List<Producto> buscar(String filtro) {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT p.*, c.nombre as cat_nombre, pr.nombre as prov_nombre " +
                "FROM productos p " +
                "INNER JOIN categorias c ON p.categoria_id = c.id " +
                "LEFT JOIN proveedores pr ON p.proveedor_id = pr.id " +
                "WHERE p.nombre ILIKE ? " +
                "ORDER BY p.id DESC";

        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + filtro + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto p = new Producto();
                    p.setId(rs.getLong("id"));
                    p.setNombre(rs.getString("nombre"));
                    p.setDescripcion(rs.getString("descripcion"));
                    p.setPrecio(rs.getDouble("precio"));
                    p.setStock(rs.getInt("stock"));
                    p.setImagenUrl(rs.getString("imagen_url"));
                    Categoria cat = new Categoria();
                    cat.setId(rs.getLong("categoria_id"));
                    cat.setNombre(rs.getString("cat_nombre"));
                    p.setCategoria(cat);
                    Proveedor prov = new Proveedor();
                    prov.setId(rs.getLong("proveedor_id"));
                    prov.setNombre(rs.getString("prov_nombre"));
                    p.setProveedor(prov);
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println("fallo al traer productos: " + e.getMessage());
        }
        return lista;
    }

    public boolean insertar(Producto p) {
        String sql = "INSERT INTO productos (nombre, descripcion, precio, stock, categoria_id, proveedor_id, imagen_url) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setLong(5, p.getCategoria().getId());
            if (p.getProveedor() != null) ps.setLong(6, p.getProveedor().getId());
            else ps.setNull(6, Types.BIGINT);
            ps.setString(7, p.getImagenUrl());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Producto p) {
        String sql = "UPDATE productos SET nombre=?, descripcion=?, precio=?, stock=?, categoria_id=?, proveedor_id=?, imagen_url=? WHERE id=?";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setLong(5, p.getCategoria().getId());
            if (p.getProveedor() != null) ps.setLong(6, p.getProveedor().getId());
            else ps.setNull(6, Types.BIGINT);
            ps.setString(7, p.getImagenUrl());
            ps.setLong(8, p.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarStock(Long id, int nuevoStock) {
        String sql = "UPDATE productos SET stock = ? WHERE id = ?";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nuevoStock);
            ps.setLong(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(Long id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getTotalProductos() {
        String sql = "SELECT COUNT(*) FROM productos";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalStockGlobal() {
        String sql = "SELECT SUM(stock) FROM productos";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Calcula el valor total de la mercaderia en almacen
    public double getValorInventarioTotal() {
        String sql = "SELECT SUM(precio * stock) FROM productos";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public int getStockCriticoCount() {
        String sql = "SELECT COUNT(*) FROM productos WHERE stock < 5";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

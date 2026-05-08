package com.temporal.proyectofinal.dao;

import com.temporal.proyectofinal.model.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para Proveedores optimizado para conexion persistente
 * @author rufernecall
 */
public class ProveedorDAO {
    
    public List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedores ORDER BY nombre ASC";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId(rs.getLong("id"));
                p.setNombre(rs.getString("nombre"));
                p.setRuc(rs.getString("ruc"));
                p.setEmail(rs.getString("email"));
                p.setTelefono(rs.getString("telefono"));
                p.setDireccion(rs.getString("direccion"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("error proveedores: " + e.getMessage());
        }
        return lista;
    }

    public boolean insertar(Proveedor p) {
        String sql = "INSERT INTO proveedores (nombre, ruc, email, telefono, direccion) VALUES (?, ?, ?, ?, ?)";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getRuc());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getTelefono());
            ps.setString(5, p.getDireccion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Proveedor p) {
        String sql = "UPDATE proveedores SET nombre=?, ruc=?, email=?, telefono=?, direccion=? WHERE id=?";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getRuc());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getTelefono());
            ps.setString(5, p.getDireccion());
            ps.setLong(6, p.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(Long id) {
        String sql = "DELETE FROM proveedores WHERE id = ?";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

package com.temporal.proyectofinal.dao;

import com.temporal.proyectofinal.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para Clientes optimizado para conexion persistente
 * @author rufernecall
 */
public class ClienteDAO {
    
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes ORDER BY nombre ASC";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getLong("id"));
                c.setNombre(rs.getString("nombre"));
                // Intentamos con documento_identidad que es el nombre mas probable en la DB
                c.setDocumentoIdentidad(rs.getString("documento_identidad")); 
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("error clientes: " + e.getMessage());
        }
        return lista;
    }

    public boolean insertar(Cliente c) {
        String sql = "INSERT INTO clientes (nombre, documento_identidad, telefono, direccion) VALUES (?, ?, ?, ?)";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDocumentoIdentidad());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getDireccion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Cliente c) {
        String sql = "UPDATE clientes SET nombre=?, documento_identidad=?, telefono=?, direccion=? WHERE id=?";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDocumentoIdentidad());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getDireccion());
            ps.setLong(5, c.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(Long id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getTotalClientes() {
        String sql = "SELECT COUNT(*) FROM clientes";
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

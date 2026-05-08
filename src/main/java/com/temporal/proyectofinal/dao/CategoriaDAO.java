package com.temporal.proyectofinal.dao;

import com.temporal.proyectofinal.model.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para Categorias con CRUD Completo (Persistente)
 * @author rufernecall
 */
public class CategoriaDAO {
    
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categorias ORDER BY nombre ASC";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getLong("id"));
                c.setNombre(rs.getString("nombre"));
                c.setActivo(rs.getBoolean("activo"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("error categorias: " + e.getMessage());
        }
        return lista;
    }
    
    public boolean insertar(Categoria c) {
        String sql = "INSERT INTO categorias (nombre, activo) VALUES (?, ?)";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setBoolean(2, c.isActivo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Categoria c) {
        String sql = "UPDATE categorias SET nombre = ?, activo = ? WHERE id = ?";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setBoolean(2, c.isActivo());
            ps.setLong(3, c.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(Long id) {
        String sql = "DELETE FROM categorias WHERE id = ?";
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

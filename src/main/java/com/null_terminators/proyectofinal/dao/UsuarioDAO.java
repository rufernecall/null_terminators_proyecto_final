package com.null_terminators.proyectofinal.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.null_terminators.proyectofinal.model.Empleado;
import com.null_terminators.proyectofinal.model.Usuario;

/**
 * DAO para Usuarios con CRUD completo (Persistente)
 * 
 * @author rufernecall
 */
public class UsuarioDAO {

    public Usuario login(String username, String password) {
        String sql = "SELECT u.*, e.nombre as emp_nombre, e.cargo as emp_cargo " +
                "FROM usuarios u " +
                "LEFT JOIN empleados e ON u.empleado_id = e.id " +
                "WHERE u.username = ? AND u.password = ? AND u.activo = true";

        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getLong("id"));
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));
                    u.setRol(rs.getString("rol"));
                    u.setActivo(rs.getBoolean("activo"));

                    if (rs.getLong("empleado_id") != 0) {
                        Empleado e = new Empleado();
                        e.setId(rs.getLong("empleado_id"));
                        e.setNombre(rs.getString("emp_nombre"));
                        e.setCargo(rs.getString("emp_cargo"));
                        u.setEmpleado(e);
                    }
                    return u;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error login: " + e.getMessage());
        }
        return null;
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT u.*, e.nombre as emp_nombre FROM usuarios u " +
                "LEFT JOIN empleados e ON u.empleado_id = e.id ORDER BY u.username ASC";

        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getLong("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRol(rs.getString("rol"));
                u.setActivo(rs.getBoolean("activo"));

                if (rs.getLong("empleado_id") != 0) {
                    Empleado e = new Empleado();
                    e.setId(rs.getLong("empleado_id"));
                    e.setNombre(rs.getString("emp_nombre"));
                    u.setEmpleado(e);
                }
                lista.add(u);
            }
        } catch (SQLException e) {
            System.err.println("error list: " + e.getMessage());
        }
        return lista;
    }

    public boolean insertar(Usuario u) {
        String sql = "INSERT INTO usuarios (username, password, rol, activo, empleado_id) VALUES (?, ?, ?, ?, ?)";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getRol());
            ps.setBoolean(4, u.isActivo());
            if (u.getEmpleado() != null)
                ps.setLong(5, u.getEmpleado().getId());
            else
                ps.setNull(5, Types.BIGINT);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Usuario u) {
        String sql = "UPDATE usuarios SET username=?, password=?, rol=?, activo=?, empleado_id=? WHERE id=?";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getRol());
            ps.setBoolean(4, u.isActivo());
            if (u.getEmpleado() != null)
                ps.setLong(5, u.getEmpleado().getId());
            else
                ps.setNull(5, Types.BIGINT);
            ps.setLong(6, u.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(Long id) {
        String sql = "DELETE FROM usuarios WHERE id=?";
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

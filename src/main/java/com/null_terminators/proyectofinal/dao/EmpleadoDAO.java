package com.null_terminators.proyectofinal.dao;

import com.null_terminators.proyectofinal.model.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para Empleados optimizado para conexion persistente
 * @author rufernecall
 */
public class EmpleadoDAO {
    
    public List<Empleado> listar() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados ORDER BY nombre ASC";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Empleado e = new Empleado();
                e.setId(rs.getLong("id"));
                e.setNombre(rs.getString("nombre"));
                e.setCargo(rs.getString("cargo"));
                e.setTelefono(rs.getString("telefono"));
                e.setSueldo(rs.getDouble("sueldo"));
                lista.add(e);
            }
        } catch (SQLException ex) {
            System.err.println("error empleados: " + ex.getMessage());
        }
        return lista;
    }

    public boolean insertar(Empleado e) {
        String sql = "INSERT INTO empleados (nombre, cargo, telefono, sueldo) VALUES (?, ?, ?, ?)";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getCargo());
            ps.setString(3, e.getTelefono());
            ps.setDouble(4, e.getSueldo());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Empleado e) {
        String sql = "UPDATE empleados SET nombre=?, cargo=?, telefono=?, sueldo=? WHERE id=?";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getCargo());
            ps.setString(3, e.getTelefono());
            ps.setDouble(4, e.getSueldo());
            ps.setLong(5, e.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(Long id) {
        String sql = "DELETE FROM empleados WHERE id = ?";
        Connection con = DatabaseConnection.getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.temporal.proyectofinal.dao;

import com.temporal.proyectofinal.model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rufernecall
 */
public class ProductoCacheDAO {
    
    // este metodo es el q vamos a usar en la pantalla de ventas
    // para q la busqueda sea instantanea (0ms)
    public List<Producto> buscar(String texto) {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos_cache WHERE nombre LIKE ? OR categoria LIKE ?";
        
        try (Connection con = LocalCacheManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, "%" + texto + "%");
            ps.setString(2, "%" + texto + "%");
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto p = new Producto();
                    p.setId(rs.getLong("id"));
                    p.setNombre(rs.getString("nombre"));
                    p.setPrecio(rs.getDouble("precio"));
                    p.setStock(rs.getInt("stock"));
                    // guardamos el nombre de la cat como string en el cache x simplicidad
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            System.out.println("error buscando en cache: " + e.getMessage());
        }
        return lista;
    }
}

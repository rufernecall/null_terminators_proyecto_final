package com.temporal.proyectofinal.service;

import com.temporal.proyectofinal.dao.CategoriaDAO;
import com.temporal.proyectofinal.dao.ClienteDAO;
import com.temporal.proyectofinal.dao.LocalCacheManager;
import com.temporal.proyectofinal.dao.ProductoDAO;
import com.temporal.proyectofinal.model.Producto;
import java.sql.*;
import java.util.List;

/**
 * Servicio de sincronizacion robusto con manejo de errores de red
 * @author rufernecall
 */
public class Sincronizador {
    
    private final ProductoDAO productoDAO;
    private final ClienteDAO clienteDAO;
    private final CategoriaDAO categoriaDAO;

    public Sincronizador() {
        this.productoDAO = new ProductoDAO();
        this.clienteDAO = new ClienteDAO();
        this.categoriaDAO = new CategoriaDAO();
    }
    
    public void sincronizarTodo() {
        System.out.println("Intentando sincronizar con la nube...");
        
        try (Connection localCon = LocalCacheManager.getConnection()) {
            localCon.setAutoCommit(false);
            
            sincronizarProductos(localCon);
            sincronizarClientes(localCon);
            sincronizarCategorias(localCon);
            
            localCon.commit();
            System.out.println("Sincronizacion terminada.");
        } catch (SQLException e) {
            System.out.println("Error en transaccion local: " + e.getMessage());
        }
    }
    
    private void sincronizarProductos(Connection con) throws SQLException {
        List<Producto> lista = productoDAO.listar();
        if (lista == null) return; // Evita NullPointerException si falla la DB
        
        try (Statement st = con.createStatement()) { st.execute("DELETE FROM productos_cache"); }
        String sql = "INSERT INTO productos_cache (id, nombre, precio, stock, categoria) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (Producto p : lista) {
                ps.setLong(1, p.getId());
                ps.setString(2, p.getNombre());
                ps.setDouble(3, p.getPrecio());
                ps.setInt(4, p.getStock());
                ps.setString(5, p.getCategoria() != null ? p.getCategoria().getNombre() : "General");
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void sincronizarClientes(Connection con) throws SQLException {
        List<com.temporal.proyectofinal.model.Cliente> lista = clienteDAO.listar();
        if (lista == null) return;
        
        try (Statement st = con.createStatement()) { st.execute("DELETE FROM clientes_cache"); }
        String sql = "INSERT INTO clientes_cache (id, nombre, documento, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (com.temporal.proyectofinal.model.Cliente c : lista) {
                ps.setLong(1, c.getId());
                ps.setString(2, c.getNombre());
                ps.setString(3, c.getDocumentoIdentidad());
                ps.setString(4, c.getEmail());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void sincronizarCategorias(Connection con) throws SQLException {
        List<com.temporal.proyectofinal.model.Categoria> lista = categoriaDAO.listar();
        if (lista == null) return;
        
        try (Statement st = con.createStatement()) { st.execute("DELETE FROM categorias_cache"); }
        String sql = "INSERT INTO categorias_cache (id, nombre) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (com.temporal.proyectofinal.model.Categoria c : lista) {
                ps.setLong(1, c.getId());
                ps.setString(2, c.getNombre());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}

package com.novatech.proyectofinal.dao;

import com.novatech.proyectofinal.model.Compra;
import com.novatech.proyectofinal.model.DetalleCompra;
import java.sql.*;
import java.util.List;

/**
 * DAO para gestionar las adquisiciones (Compras) de productos
 * @author rufernecall
 */
public class ComprasDAO {

    public boolean registrarCompra(Compra compra, List<DetalleCompra> detalles) {
        String sqlCompra = "INSERT INTO compras (proveedor_id, responsable_id, total, fecha, estado) VALUES (?, ?, ?, ?, ?) RETURNING id";
        String sqlDetalle = "INSERT INTO detalles_compra (compra_id, producto_id, cantidad, costo_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        String sqlUpdateStock = "UPDATE productos SET stock = stock + ? WHERE id = ?";
        String sqlMov = "INSERT INTO movimientos (producto_id, cantidad, tipo, motivo, fecha) VALUES (?, ?, 'ENTRADA', ?, ?)";

        Connection con = DatabaseConnection.getConnection();
        try {
            con.setAutoCommit(false); // Iniciamos transaccion

            // 1. Insertar Cabecera
            try (PreparedStatement ps = con.prepareStatement(sqlCompra)) {
                ps.setLong(1, compra.getProveedor().getId());
                ps.setLong(2, compra.getResponsable().getId());
                ps.setDouble(3, compra.getTotal());
                ps.setTimestamp(4, Timestamp.valueOf(compra.getFecha()));
                ps.setString(5, compra.getEstado());

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        compra.setId(rs.getLong(1));
                    } else {
                        con.rollback();
                        return false;
                    }
                }
            }

            // 2. Insertar Detalles, Actualizar Stock y Registrar Movimientos
            
            try (PreparedStatement psDet = con.prepareStatement(sqlDetalle);
                 PreparedStatement psStock = con.prepareStatement(sqlUpdateStock);
                 PreparedStatement psMov = con.prepareStatement(sqlMov)) {
                
                for (DetalleCompra d : detalles) {
                    // Detalle
                    psDet.setLong(1, compra.getId());
                    psDet.setLong(2, d.getProducto().getId());
                    psDet.setInt(3, d.getCantidad());
                    psDet.setDouble(4, d.getCostoUnitario());
                    psDet.setDouble(5, d.getSubtotal());
                    psDet.addBatch();

                    // Stock
                    psStock.setInt(1, d.getCantidad());
                    psStock.setLong(2, d.getProducto().getId());
                    psStock.addBatch();
                    
                    // Movimiento
                    psMov.setLong(1, d.getProducto().getId());
                    psMov.setInt(2, d.getCantidad());
                    psMov.setString(3, "COMPRA REGISTRADA #" + compra.getId());
                    psMov.setTimestamp(4, Timestamp.valueOf(compra.getFecha()));
                    psMov.addBatch();
                }
                psDet.executeBatch();
                psStock.executeBatch();
                psMov.executeBatch();
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ex) {}
            System.err.println("Error al registrar compra: " + e.getMessage());
            return false;
        } finally {
            try { con.setAutoCommit(true); } catch (SQLException e) {}
        }
    }
}

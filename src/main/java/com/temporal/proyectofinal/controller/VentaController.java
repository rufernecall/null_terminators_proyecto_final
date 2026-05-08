package com.temporal.proyectofinal.controller;

import com.temporal.proyectofinal.dao.ProductoDAO;
import com.temporal.proyectofinal.dao.VentasDAO;
import com.temporal.proyectofinal.model.DetalleOrdenVenta;
import com.temporal.proyectofinal.model.Empleado;
import com.temporal.proyectofinal.model.OrdenVenta;
import com.temporal.proyectofinal.model.Producto;
import com.temporal.proyectofinal.util.ReporteUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de Ventas con Auditoria (Sincronizado)
 * @author rufernecall
 */
public class VentaController {

    private final VentasDAO ventasDAO;
    private final ProductoDAO productoDAO;

    private List<DetalleOrdenVenta> carrito;
    private double totalVenta;

    public VentaController() {
        this.ventasDAO = new VentasDAO();
        this.productoDAO = new ProductoDAO();
        this.carrito = new ArrayList<>();
        this.totalVenta = 0.0;
    }

    public void agregarProducto(Producto p, int cantidad) {
        double subtotal = p.getPrecio() * cantidad;
        DetalleOrdenVenta detalle = new DetalleOrdenVenta(null, null, p, cantidad, p.getPrecio(), subtotal);
        carrito.add(detalle);
        totalVenta += subtotal;
    }
    
    public void eliminarProducto(int index) {
        if (index >= 0 && index < carrito.size()) {
            DetalleOrdenVenta eliminado = carrito.remove(index);
            totalVenta -= eliminado.getSubtotal();
        }
    }

    public void nuevaVenta() {
        carrito.clear();
        totalVenta = 0.0;
    }

    public boolean finalizarVenta(com.temporal.proyectofinal.model.Cliente cliente, Empleado vendedor, String metodoPago) {
        if (carrito.isEmpty())
            return false;

        OrdenVenta orden = new OrdenVenta();
        orden.setCliente(cliente);
        orden.setVendedor(vendedor); // Asignamos el vendedor para auditoria
        orden.setFecha(LocalDateTime.now());
        orden.setEstado("COMPLETADO");
        orden.setTotal(totalVenta);

        // Registrar en BD (Supabase)
        boolean exito = ventasDAO.registrarVenta(orden, carrito);

        if (exito) {
            try {
                ReporteUtil.generarBoletaPDF(orden, carrito);
            } catch (Exception e) {
                System.out.println("Error al generar PDF: " + e.getMessage());
            }
            nuevaVenta();
        }

        return exito;
    }

    public List<DetalleOrdenVenta> getCarrito() {
        return carrito;
    }

    public double getTotalVenta() {
        return totalVenta;
    }
}

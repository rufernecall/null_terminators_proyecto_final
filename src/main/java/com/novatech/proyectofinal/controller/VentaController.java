package com.novatech.proyectofinal.controller;

import com.novatech.proyectofinal.dao.ProductoDAO;
import com.novatech.proyectofinal.dao.VentasDAO;
import com.novatech.proyectofinal.model.DetalleVenta;
import com.novatech.proyectofinal.model.Empleado;
import com.novatech.proyectofinal.model.Venta;
import com.novatech.proyectofinal.model.Producto;
import com.novatech.proyectofinal.service.FinanzasService;
import com.novatech.proyectofinal.util.ReporteUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para gestionar las ventas.
 * 
 * @author rufernecall
 */
public class VentaController {

    private final VentasDAO ventasDAO;
    private final ProductoDAO productoDAO;

    private List<DetalleVenta> carrito;
    private double totalVenta;

    private final FinanzasService finanzasService;

    public VentaController() {
        this.ventasDAO = new VentasDAO();
        this.productoDAO = new ProductoDAO();
        this.finanzasService = new FinanzasService();
        this.carrito = new ArrayList<>();
        this.totalVenta = 0.0;
    }

    public void agregarProducto(Producto p, int cantidad) {
        double subtotal = p.getPrecio() * cantidad;
        DetalleVenta detalle = new DetalleVenta(null, null, p, cantidad, p.getPrecio(), subtotal);
        carrito.add(detalle);
        totalVenta += subtotal;
    }

    public void eliminarProducto(int index) {
        if (index >= 0 && index < carrito.size()) {
            DetalleVenta eliminado = carrito.remove(index);
            totalVenta -= eliminado.getSubtotal();
        }
    }

    public void nuevaVenta() {
        carrito.clear();
        totalVenta = 0.0;
    }

    public boolean finalizarVenta(com.novatech.proyectofinal.model.Cliente cliente, Empleado vendedor,
            String metodoPago, String tipoComprobante) {
        if (carrito.isEmpty())
            return false;

        Venta orden = new Venta();
        orden.setCliente(cliente);
        orden.setVendedor(vendedor);
        orden.setFecha(LocalDateTime.now());
        orden.setEstado("COMPLETADO");
        orden.setTotal(totalVenta);
        
        orden.getMetadata().put("tipoDoc", tipoComprobante);

        // Registrar venta
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

    public List<DetalleVenta> getCarrito() {
        return carrito;
    }

    public double getTotalVenta() {
        return totalVenta;
    }
}

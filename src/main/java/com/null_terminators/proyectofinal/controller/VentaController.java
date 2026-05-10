package com.null_terminators.proyectofinal.controller;

import com.null_terminators.proyectofinal.dao.ProductoDAO;
import com.null_terminators.proyectofinal.dao.VentasDAO;
import com.null_terminators.proyectofinal.model.DetalleVenta;
import com.null_terminators.proyectofinal.model.Empleado;
import com.null_terminators.proyectofinal.model.Venta;
import com.null_terminators.proyectofinal.model.Producto;
import com.null_terminators.proyectofinal.service.FinanzasService;
import com.null_terminators.proyectofinal.util.ReporteUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de Ventas con Auditoria (Sincronizado)
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

    public boolean finalizarVenta(com.null_terminators.proyectofinal.model.Cliente cliente, Empleado vendedor,
            String metodoPago, String tipoComprobante) {
        if (carrito.isEmpty())
            return false;

        Venta orden = new Venta();
        orden.setCliente(cliente);
        orden.setVendedor(vendedor); // Asignamos el vendedor para auditoria
        orden.setFecha(LocalDateTime.now());
        orden.setEstado("COMPLETADO");
        orden.setTotal(totalVenta);
        
        // Guardamos el tipo de documento para que el SP lo procese
        orden.getMetadata().put("tipoDoc", tipoComprobante);

        // Registrar en BD (Supabase) - Ahora el SP genera el comprobante
        boolean exito = ventasDAO.registrarVenta(orden, carrito);

        if (exito) {
            // El comprobante ya fue generado por el Stored Procedure
            // finanzasService.generarComprobanteVenta(orden, tipoComprobante);

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

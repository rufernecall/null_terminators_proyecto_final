package com.null_terminators.proyectofinal.model;

/**
 * Modelo DetalleCompra para el desglose de adquisiciones
 * @author rufernecall
 */
public class DetalleCompra {
    private Long id;
    private Compra compra;
    private Producto producto;
    private int cantidad;
    private double costoUnitario;
    private double subtotal;

    public DetalleCompra() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Compra getCompra() { return compra; }
    public void setCompra(Compra compra) { this.compra = compra; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getCostoUnitario() { return costoUnitario; }
    public void setCostoUnitario(double costoUnitario) { this.costoUnitario = costoUnitario; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}

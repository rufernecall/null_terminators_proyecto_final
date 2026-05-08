package com.temporal.proyectofinal.model;

public class DetalleOrdenVenta {

    private Long id;
    private OrdenVenta orden;
    private Producto producto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;

    public DetalleOrdenVenta() {
    }

    public DetalleOrdenVenta(Long id, OrdenVenta orden, Producto producto, Integer cantidad, Double precioUnitario, Double subtotal) {
        this.id = id;
        this.orden = orden;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdenVenta getOrden() {
        return orden;
    }

    public void setOrden(OrdenVenta orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}

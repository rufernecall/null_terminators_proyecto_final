package com.temporal.proyectofinal.model;

import java.time.LocalDateTime;

public class Movimiento {

    private Long id;
    private Producto producto;
    private String tipo; // ENTRADA o SALIDA
    private Integer cantidad;
    private LocalDateTime fecha;
    private String motivo;

    public Movimiento() {
    }

    public Movimiento(Long id, Producto producto, String tipo, Integer cantidad, LocalDateTime fecha, String motivo) {
        this.id = id;
        this.producto = producto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.motivo = motivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}

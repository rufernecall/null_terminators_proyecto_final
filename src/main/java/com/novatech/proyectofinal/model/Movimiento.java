package com.novatech.proyectofinal.model;

import java.time.LocalDateTime;

/**
 * Representa un movimiento de stock vinculado a la tabla 'movimientos' de la BD.
 * @author rufernecall
 */
public class Movimiento {
    private Long id;
    private Producto producto;
    private Integer cantidad;
    private LocalDateTime fecha;
    private String motivo;
    private String tipo; // ENTRADA o SALIDA

    public Movimiento() {}

    public Movimiento(Long id, Producto producto, Integer cantidad, LocalDateTime fecha, String motivo, String tipo) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.motivo = motivo;
        this.tipo = tipo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}

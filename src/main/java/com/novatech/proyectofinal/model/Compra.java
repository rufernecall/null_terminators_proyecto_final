package com.novatech.proyectofinal.model;

import java.time.LocalDateTime;

/**
 * Modelo Compra para gestionar adquisiciones de stock
 * @author rufernecall
 */
public class Compra {
    private Long id;
    private Proveedor proveedor;
    private Empleado responsable;
    private LocalDateTime fecha;
    private double total;
    private String estado;

    public Compra() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Proveedor getProveedor() { return proveedor; }
    public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }

    public Empleado getResponsable() { return responsable; }
    public void setResponsable(Empleado responsable) { this.responsable = responsable; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

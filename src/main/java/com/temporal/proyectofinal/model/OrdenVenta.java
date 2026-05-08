package com.temporal.proyectofinal.model;

import java.time.LocalDateTime;

/**
 * Modelo OrdenVenta con Auditoria de Vendedor
 * @author rufernecall
 */
public class OrdenVenta {
    private Long id;
    private Cliente cliente;
    private Empleado vendedor; // Nueva relacion para auditoria
    private LocalDateTime fecha;
    private double total;
    private String estado;

    public OrdenVenta() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Empleado getVendedor() { return vendedor; }
    public void setVendedor(Empleado vendedor) { this.vendedor = vendedor; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

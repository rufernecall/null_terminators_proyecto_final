package com.novatech.proyectofinal.model;

import java.time.LocalDateTime;

/**
 * Modelo para la gestion de documentos legales (Facturas/Boletas)
 * @author rufernecall
 */
public class Comprobante {
    private Long id;
    private String tipo; // BOLETA, FACTURA
    private String serie; // Ej: B001, F001
    private int numero;   // Correlativo
    private LocalDateTime fecha;
    private double montoTotal;
    private String estado; // EMITIDO, ANULADO
    private Long ventaId;  // Opcional, si viene de una venta
    private Long compraId; // Opcional, si viene de una compra

    public Comprobante() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getSerie() { return serie; }
    public void setSerie(String serie) { this.serie = serie; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public double getMontoTotal() { return montoTotal; }
    public void setMontoTotal(double montoTotal) { this.montoTotal = montoTotal; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Long getVentaId() { return ventaId; }
    public void setVentaId(Long ventaId) { this.ventaId = ventaId; }

    public Long getCompraId() { return compraId; }
    public void setCompraId(Long compraId) { this.compraId = compraId; }
}

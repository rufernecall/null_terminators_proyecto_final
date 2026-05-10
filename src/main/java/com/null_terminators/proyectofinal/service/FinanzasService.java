package com.null_terminators.proyectofinal.service;

import com.null_terminators.proyectofinal.dao.FinanzasDAO;
import com.null_terminators.proyectofinal.model.Comprobante;
import com.null_terminators.proyectofinal.model.Compra;
import com.null_terminators.proyectofinal.model.Venta;
import java.time.LocalDateTime;

/**
 * Servicio para la gestion de facturacion y documentos legales
 * 
 * @author rufernecall
 */
public class FinanzasService {

    private final FinanzasDAO finanzasDAO;

    public FinanzasService() {
        this.finanzasDAO = new FinanzasDAO();
    }

    public Comprobante generarComprobanteVenta(Venta venta, String tipo) {
        Comprobante c = new Comprobante();
        c.setTipo(tipo);

        // Asignacion de serie segun tipo
        String serie = tipo.equalsIgnoreCase("FACTURA") ? "F001" : "B001";
        c.setSerie(serie);

        // Obtener siguiente correlativo
        int siguiente = finanzasDAO.obtenerSiguienteNumero(tipo, serie);
        c.setNumero(siguiente);

        c.setFecha(LocalDateTime.now());
        c.setMontoTotal(venta.getTotal());
        c.setEstado("EMITIDO");
        c.setVentaId(venta.getId());

        if (finanzasDAO.registrarComprobante(c)) {
            return c;
        }
        return null;
    }

    public Comprobante generarComprobanteCompra(Compra compra, String tipo) {
        Comprobante c = new Comprobante();
        c.setTipo(tipo);
        c.setSerie("ADQ1"); // Serie para adquisiciones

        int siguiente = finanzasDAO.obtenerSiguienteNumero(tipo, c.getSerie());
        c.setNumero(siguiente);

        c.setFecha(LocalDateTime.now());
        c.setMontoTotal(compra.getTotal());
        c.setEstado("EMITIDO");
        c.setCompraId(compra.getId());

        if (finanzasDAO.registrarComprobante(c)) {
            return c;
        }
        return null;
    }
}

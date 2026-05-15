package com.novatech.proyectofinal.controller;

import com.novatech.proyectofinal.dao.FinanzasDAO;
import com.novatech.proyectofinal.model.Comprobante;
import com.novatech.proyectofinal.model.Venta;
import com.novatech.proyectofinal.model.Compra;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controlador para la gestion financiera y comprobantes.
 * 
 * @author rufernecall
 */
public class FinanzasController {

    private final FinanzasDAO finanzasDAO;

    public FinanzasController() {
        this.finanzasDAO = new FinanzasDAO();
    }

    public List<Comprobante> listarComprobantes() {
        return finanzasDAO.listar();
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
        c.setSerie("ADQ1");

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

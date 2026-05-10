package com.null_terminators.proyectofinal.controller;

import com.null_terminators.proyectofinal.model.Cliente;
import com.null_terminators.proyectofinal.model.Proveedor;
import com.null_terminators.proyectofinal.service.ComercialService;
import java.util.List;

/**
 * Controlador Comercial vinculado al Servicio
 * @author rufernecall
 */
public class ComercialController {

    private final ComercialService service;

    public ComercialController() {
        this.service = new ComercialService();
    }

    public List<Cliente> listarClientes() {
        return service.listarClientes();
    }

    public List<Proveedor> listarProveedores() {
        return service.listarProveedores();
    }

    public boolean guardarCliente(Cliente c) {
        return service.guardarCliente(c);
    }

    public boolean guardarProveedor(Proveedor p) {
        return service.guardarProveedor(p);
    }

    public boolean eliminarCliente(Long id) {
        return service.eliminarCliente(id);
    }

    public boolean eliminarProveedor(Long id) {
        return service.eliminarProveedor(id);
    }
}

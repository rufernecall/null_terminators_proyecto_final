package com.temporal.proyectofinal.service;

import com.temporal.proyectofinal.dao.ClienteDAO;
import com.temporal.proyectofinal.dao.ProveedorDAO;
import com.temporal.proyectofinal.model.Cliente;
import com.temporal.proyectofinal.model.Proveedor;
import java.util.List;

/**
 * Servicio para la gestion de clientes y proveedores
 * @author rufernecall
 */
public class ComercialService {

    private final ClienteDAO clienteDAO;
    private final ProveedorDAO proveedorDAO;

    public ComercialService() {
        this.clienteDAO = new ClienteDAO();
        this.proveedorDAO = new ProveedorDAO();
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listar();
    }

    public List<Proveedor> listarProveedores() {
        return proveedorDAO.listar();
    }

    public boolean guardarCliente(Cliente c) {
        if (c.getId() == null) {
            return clienteDAO.insertar(c);
        } else {
            return clienteDAO.actualizar(c);
        }
    }

    public boolean guardarProveedor(Proveedor p) {
        if (p.getId() == null) {
            return proveedorDAO.insertar(p);
        } else {
            return proveedorDAO.actualizar(p);
        }
    }

    public boolean eliminarCliente(Long id) {
        return clienteDAO.eliminar(id);
    }

    public boolean eliminarProveedor(Long id) {
        return proveedorDAO.eliminar(id);
    }
}

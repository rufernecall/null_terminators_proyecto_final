package com.novatech.proyectofinal.service;

import com.novatech.proyectofinal.dao.ClienteDAO;
import com.novatech.proyectofinal.dao.ProveedorDAO;
import com.novatech.proyectofinal.dao.ComprasDAO;
import com.novatech.proyectofinal.model.Cliente;
import com.novatech.proyectofinal.model.Proveedor;
import com.novatech.proyectofinal.model.Compra;
import com.novatech.proyectofinal.model.DetalleCompra;
import java.util.List;

/**
 * Servicio para la gestion de clientes, proveedores y adquisiciones
 * @author rufernecall
 */
public class ComercialService {

    private final ClienteDAO clienteDAO;
    private final ProveedorDAO proveedorDAO;
    private final ComprasDAO comprasDAO;

    public ComercialService() {
        this.clienteDAO = new ClienteDAO();
        this.proveedorDAO = new ProveedorDAO();
        this.comprasDAO = new ComprasDAO();
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

    public boolean registrarCompra(Compra compra, List<DetalleCompra> detalles) {
        return comprasDAO.registrarCompra(compra, detalles);
    }
}

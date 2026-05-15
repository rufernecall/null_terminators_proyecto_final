package com.novatech.proyectofinal.service;

import com.novatech.proyectofinal.dao.EmpleadoDAO;
import com.novatech.proyectofinal.dao.UsuarioDAO;
import com.novatech.proyectofinal.model.Empleado;
import com.novatech.proyectofinal.model.Usuario;
import java.util.List;

/**
 * Servicio para la gestion de usuarios y empleados.
 * 
 * @author rufernecall
 */
public class UsuarioService {

    private final UsuarioDAO usuarioDAO;
    private final EmpleadoDAO empleadoDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
        this.empleadoDAO = new EmpleadoDAO();
    }

    public Usuario login(String username, String password) {
        return usuarioDAO.login(username, password);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listar();
    }

    public List<Empleado> listarEmpleados() {
        return empleadoDAO.listar();
    }

    public boolean guardarUsuario(Usuario u) {
        if (u.getId() == null) {
            return usuarioDAO.insertar(u);
        } else {
            return usuarioDAO.actualizar(u);
        }
    }

    public boolean guardarEmpleado(Empleado e) {
        if (e.getId() == null) {
            return empleadoDAO.insertar(e);
        } else {
            return empleadoDAO.actualizar(e);
        }
    }

    public boolean eliminarUsuario(Long id) {
        return usuarioDAO.eliminar(id);
    }

    public boolean eliminarEmpleado(Long id) {
        return empleadoDAO.eliminar(id);
    }
}

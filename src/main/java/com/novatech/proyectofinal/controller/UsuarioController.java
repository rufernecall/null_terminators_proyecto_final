package com.novatech.proyectofinal.controller;

import java.util.List;
import com.novatech.proyectofinal.dao.EmpleadoDAO;
import com.novatech.proyectofinal.dao.UsuarioDAO;
import com.novatech.proyectofinal.model.Empleado;
import com.novatech.proyectofinal.model.Usuario;

/**
 * Controlador para gestionar usuarios y empleados.
 * 
 * @author rufernecall
 */
public class UsuarioController {

    private final UsuarioDAO usuarioDAO;
    private final EmpleadoDAO empleadoDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
        this.empleadoDAO = new EmpleadoDAO();
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listar();
    }

    public Usuario login(String user, String pass) {
        return usuarioDAO.login(user, pass);
    }

    public boolean guardarUsuario(Usuario u) {
        if (u.getId() == null) {
            return usuarioDAO.insertar(u);
        } else {
            return usuarioDAO.actualizar(u);
        }
    }

    public boolean eliminarUsuario(Long id) {
        return usuarioDAO.eliminar(id);
    }

    public List<Empleado> listarEmpleados() {
        return empleadoDAO.listar();
    }

    public boolean guardarEmpleado(Empleado e) {
        if (e.getId() == null) {
            return empleadoDAO.insertar(e);
        } else {
            return empleadoDAO.actualizar(e);
        }
    }

    public boolean eliminarEmpleado(Long id) {
        return empleadoDAO.eliminar(id);
    }
}

package com.null_terminators.proyectofinal.service;

import com.null_terminators.proyectofinal.dao.EmpleadoDAO;
import com.null_terminators.proyectofinal.dao.UsuarioDAO;
import com.null_terminators.proyectofinal.model.Empleado;
import com.null_terminators.proyectofinal.model.Usuario;
import java.util.List;

/**
 * Servicio para la gestion de usuarios, empleados y seguridad
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
        // Aca se podria anadir logica de hashing en el futuro
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

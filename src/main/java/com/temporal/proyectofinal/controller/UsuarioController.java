package com.temporal.proyectofinal.controller;

import com.temporal.proyectofinal.model.Empleado;
import com.temporal.proyectofinal.model.Usuario;
import com.temporal.proyectofinal.service.UsuarioService;
import java.util.List;

/**
 * Controlador de Usuarios optimizado mediante Servicios
 * @author rufernecall
 */
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController() {
        this.service = new UsuarioService();
    }

    public List<Usuario> listarUsuarios() {
        return service.listarUsuarios();
    }

    public Usuario login(String user, String pass) {
        return service.login(user, pass);
    }

    public boolean guardarUsuario(Usuario u) {
        return service.guardarUsuario(u);
    }

    public boolean eliminarUsuario(Long id) {
        return service.eliminarUsuario(id);
    }

    public List<Empleado> listarEmpleados() {
        return service.listarEmpleados();
    }

    public boolean guardarEmpleado(Empleado e) {
        return service.guardarEmpleado(e);
    }

    public boolean eliminarEmpleado(Long id) {
        return service.eliminarEmpleado(id);
    }
}

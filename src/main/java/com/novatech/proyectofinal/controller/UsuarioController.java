package com.novatech.proyectofinal.controller;

import java.util.List;

import com.novatech.proyectofinal.model.Empleado;
import com.novatech.proyectofinal.model.Usuario;
import com.novatech.proyectofinal.service.UsuarioService;

/**
 * Controlador para gestionar usuarios y empleados.
 * 
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

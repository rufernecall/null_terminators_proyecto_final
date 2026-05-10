package com.null_terminators.proyectofinal.controller;

import java.util.List;

import com.null_terminators.proyectofinal.model.Empleado;
import com.null_terminators.proyectofinal.model.Usuario;
import com.null_terminators.proyectofinal.service.UsuarioService;

/**
 * Controlador de Usuarios optimizado mediante Servicios
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

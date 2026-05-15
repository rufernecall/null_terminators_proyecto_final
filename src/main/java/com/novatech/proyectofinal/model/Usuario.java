package com.novatech.proyectofinal.model;

/**
 * Modelo para gestionar los accesos vinculados a un empleado
 * 
 * @author rufernecall
 */
public class Usuario {
    private Long id;
    private String username;
    private String password;
    private String rol;
    private boolean activo;
    private Empleado empleado; // Vinculo con la persona fisica

    public Usuario() {
    }

    public Usuario(Long id, String username, String password, String rol, boolean activo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}

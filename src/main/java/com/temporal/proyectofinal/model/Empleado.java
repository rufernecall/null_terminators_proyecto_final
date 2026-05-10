package com.temporal.proyectofinal.model;

/**
 * Modelo para gestionar el personal (Optimizado)
 * @author rufernecall
 */
public class Empleado {
    private Long id;
    private String nombre;
    private String cargo;
    private String telefono;
    private String dni;
    private double sueldo;

    public Empleado() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public double getSueldo() { return sueldo; }
    public void setSueldo(double sueldo) { this.sueldo = sueldo; }

    @Override
    public String toString() {
        return nombre != null ? nombre : "Sin Nombre";
    }
}

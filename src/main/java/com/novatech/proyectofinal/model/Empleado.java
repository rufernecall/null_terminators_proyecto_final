package com.novatech.proyectofinal.model;

/**
 * Modelo para gestionar el personal (Refactorizado con Herencia)
 * @author rufernecall
 */
public class Empleado extends Persona {
    private String cargo;
    private double sueldo;

    public Empleado() {}

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public double getSueldo() { return sueldo; }
    public void setSueldo(double sueldo) { this.sueldo = sueldo; }

    // Mapeo de dni a documento de la clase Persona
    public String getDni() { return getDocumento(); }
    public void setDni(String dni) { setDocumento(dni); }
}

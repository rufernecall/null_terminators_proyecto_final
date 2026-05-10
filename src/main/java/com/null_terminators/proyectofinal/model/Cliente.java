package com.null_terminators.proyectofinal.model;

/**
 * Modelo para gestionar los clientes (Refactorizado con Herencia)
 * @author rufernecall
 */
public class Cliente extends Persona {

    private String email;
    private String direccion;

    public Cliente() {
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    // Mapeo de documentoIdentidad a documento de Persona
    public String getDocumentoIdentidad() { return getDocumento(); }
    public void setDocumentoIdentidad(String dni) { setDocumento(dni); }
}

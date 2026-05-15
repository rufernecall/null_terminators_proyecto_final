package com.novatech.proyectofinal.model;

/**
 * Clase base para todas las personas del sistema (Herencia)
 * @author rufernecall
 */
public abstract class Persona {
    protected Long id;
    protected String nombre;
    protected String documento;
    protected String telefono;

    public Persona() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return nombre != null ? nombre : "Sin Nombre";
    }
}

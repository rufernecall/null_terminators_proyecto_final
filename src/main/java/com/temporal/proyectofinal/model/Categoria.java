package com.temporal.proyectofinal.model;

/**
 * Modelo Categoria ajustado al esquema de Supabase
 * @author rufernecall
 */
public class Categoria {
    private Long id;
    private String nombre;
    private boolean activo;

    public Categoria() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    
    @Override
    public String toString() {
        return nombre;
    }
}

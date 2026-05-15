package com.novatech.proyectofinal.controller;

import java.util.List;

import com.novatech.proyectofinal.model.Categoria;
import com.novatech.proyectofinal.model.Producto;
import com.novatech.proyectofinal.service.InventarioService;

/**
 * Controlador para la gestion del inventario.
 * 
 * @author rufernecall
 */
public class InventarioController {

    private final InventarioService service;

    public InventarioController() {
        this.service = new InventarioService();
    }

    public List<Producto> listarProductos() {
        return service.obtenerTodosLosProductos();
    }

    public List<Categoria> listarCategorias() {
        return service.obtenerTodasLasCategorias();
    }

    public boolean guardarProducto(Producto p) {
        return service.guardarProducto(p);
    }

    public boolean eliminarProducto(Long id) {
        return service.eliminarProducto(id);
    }

    public int getTotalProductos() {
        return service.obtenerTodosLosProductos().size();
    }

    public int getTotalStockGlobal() {
        return service.obtenerTodosLosProductos().stream().mapToInt(Producto::getStock).sum();
    }

    public double getValorInventarioTotal() {
        return service.obtenerTodosLosProductos().stream()
                .mapToDouble(p -> p.getPrecio() * p.getStock()).sum();
    }
}

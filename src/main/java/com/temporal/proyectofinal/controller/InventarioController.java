package com.temporal.proyectofinal.controller;

import com.temporal.proyectofinal.model.Categoria;
import com.temporal.proyectofinal.model.Producto;
import com.temporal.proyectofinal.service.InventarioService;
import java.util.List;

/**
 * Controlador de Inventario optimizado mediante Servicios
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

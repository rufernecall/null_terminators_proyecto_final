package com.temporal.proyectofinal.service;

import com.temporal.proyectofinal.dao.CategoriaDAO;
import com.temporal.proyectofinal.dao.ProductoDAO;
import com.temporal.proyectofinal.model.Categoria;
import com.temporal.proyectofinal.model.Producto;
import java.util.List;

/**
 * Servicio para la gestion de productos y categorias
 * @author rufernecall
 */
public class InventarioService {

    private final ProductoDAO productoDAO;
    private final CategoriaDAO categoriaDAO;

    public InventarioService() {
        this.productoDAO = new ProductoDAO();
        this.categoriaDAO = new CategoriaDAO();
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoDAO.listar();
    }

    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaDAO.listar();
    }

    public boolean guardarProducto(Producto p) {
        // Regla de negocio: No permitir stock negativo
        if (p.getStock() < 0) return false;
        
        if (p.getId() == null) {
            return productoDAO.insertar(p);
        } else {
            return productoDAO.actualizar(p);
        }
    }

    public boolean eliminarProducto(Long id) {
        return productoDAO.eliminar(id);
    }
}

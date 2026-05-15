package com.novatech.proyectofinal.controller;

import java.util.List;
import com.novatech.proyectofinal.dao.CategoriaDAO;
import com.novatech.proyectofinal.dao.ProductoDAO;
import com.novatech.proyectofinal.dao.MovimientoDAO;
import com.novatech.proyectofinal.model.Categoria;
import com.novatech.proyectofinal.model.Producto;
import com.novatech.proyectofinal.model.Movimiento;

/**
 * Controlador para la gestion del inventario.
 * 
 * @author rufernecall
 */
public class InventarioController {

    private final ProductoDAO productoDAO;
    private final CategoriaDAO categoriaDAO;
    private final MovimientoDAO movimientoDAO;

    public InventarioController() {
        this.productoDAO = new ProductoDAO();
        this.categoriaDAO = new CategoriaDAO();
        this.movimientoDAO = new MovimientoDAO();
    }

    public List<Producto> listarProductos() {
        return productoDAO.listar();
    }

    public List<Movimiento> listarMovimientos() {
        return movimientoDAO.listarTodo();
    }

    public List<Categoria> listarCategorias() {
        return categoriaDAO.listar();
    }

    public boolean guardarProducto(Producto p) {
        // Validacion de negocio: No permitir stock negativo
        if (p.getStock() < 0) {
            return false;
        }

        if (p.getId() == null) {
            return productoDAO.insertar(p);
        } else {
            return productoDAO.actualizar(p);
        }
    }

    public boolean eliminarProducto(Long id) {
        return productoDAO.eliminar(id);
    }

    // Gestion de Categorias
    public boolean guardarCategoria(Categoria c) {
        if (c.getId() == null) {
            return categoriaDAO.insertar(c);
        } else {
            return categoriaDAO.actualizar(c);
        }
    }

    public boolean eliminarCategoria(Long id) {
        return categoriaDAO.eliminar(id);
    }

    public int getTotalProductos() {
        return productoDAO.getTotalProductos();
    }

    public int getTotalStockGlobal() {
        return productoDAO.getTotalStockGlobal();
    }

    public double getValorInventarioTotal() {
        return productoDAO.getValorInventarioTotal();
    }
}

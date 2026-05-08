package com.temporal.proyectofinal.util;

import com.temporal.proyectofinal.dao.CategoriaDAO;
import com.temporal.proyectofinal.dao.ProductoDAO;
import com.temporal.proyectofinal.dao.ProveedorDAO;
import com.temporal.proyectofinal.dao.EmpleadoDAO;
import com.temporal.proyectofinal.model.Categoria;
import com.temporal.proyectofinal.model.Producto;
import com.temporal.proyectofinal.model.Proveedor;
import com.temporal.proyectofinal.model.Empleado;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.util.function.Consumer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Almacen centralizado de datos en memoria (Optimizado)
 * @author rufernecall
 */
public class DataStore {
    
    private static DataStore instance;
    
    private List<Categoria> categorias = new ArrayList<>();
    private List<Proveedor> proveedores = new ArrayList<>();
    private List<Producto> productos = new ArrayList<>();
    private List<Empleado> empleados = new ArrayList<>();
    private Map<String, ImageIcon> imageCache = new HashMap<>();
    private ExecutorService imageExecutor = Executors.newFixedThreadPool(4);
    
    private DataStore() {}
    
    public static DataStore getInstance() {
        if (instance == null) instance = new DataStore();
        return instance;
    }
    
    public void init(Consumer<String> progressCallback) {
        progressCallback.accept("Sincronizando Categorias...");
        this.categorias = new CategoriaDAO().listar();
        
        progressCallback.accept("Sincronizando Proveedores...");
        this.proveedores = new ProveedorDAO().listar();
        
        progressCallback.accept("Sincronizando Catalogo...");
        this.productos = new ProductoDAO().listar();
        
        progressCallback.accept("Sincronizando Personal...");
        this.empleados = new EmpleadoDAO().listar();
        
        // Lanzamos la precarga de imagenes en background
        precargarImagenes();
    }

    private void precargarImagenes() {
        for (Producto p : productos) {
            String url = p.getImagenUrl();
            if (url != null && !url.isEmpty() && !imageCache.containsKey(url)) {
                imageExecutor.submit(() -> {
                    try {
                        Image img = ImageIO.read(new URL(url));
                        if (img != null) {
                            Image scaled = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                            imageCache.put(url, new ImageIcon(scaled));
                        }
                    } catch (Exception e) {
                        // Silencioso si falla una imagen
                    }
                });
            }
        }
    }

    public ImageIcon getCachedImage(String url) {
        return imageCache.get(url);
    }

    public List<Categoria> getCategorias() { return categorias; }
    public List<Proveedor> getProveedores() { return proveedores; }
    public List<Producto> getProductos() { return productos; }
    public List<Empleado> getEmpleados() { return empleados; }
    
    public void refrescarCategorias() { this.categorias = new CategoriaDAO().listar(); }
    public void refrescarProveedores() { this.proveedores = new ProveedorDAO().listar(); }
    public void refrescarProductos() { this.productos = new ProductoDAO().listar(); }
    public void refrescarEmpleados() { this.empleados = new EmpleadoDAO().listar(); }
}

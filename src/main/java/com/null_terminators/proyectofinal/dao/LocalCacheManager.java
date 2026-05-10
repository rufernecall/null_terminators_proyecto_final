/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.null_terminators.proyectofinal.dao;

import java.sql.*;

/**
 *
 * @author rufernecall
 */
public class LocalCacheManager {

    // este es para el archivo local de sqlite, asi no tarda tanto como supabase
    private static final String URL = "jdbc:sqlite:cache_local.db";
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // cargar el driver de sqlite
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(URL);
                crearTablas(); // crear las tablas si no existen
            }
        } catch (Exception e) {
            System.out.println("error con el sqlite local: " + e.getMessage());
        }
        return connection;
    }

    // crear las tablas locales para q funcione como una copia de supabase
    private static void crearTablas() {
        String sqlProductos = "CREATE TABLE IF NOT EXISTS productos_cache (" +
                "id INTEGER PRIMARY KEY, " +
                "nombre TEXT, " +
                "precio REAL, " +
                "stock INTEGER, " +
                "categoria TEXT" +
                ")";

        String sqlClientes = "CREATE TABLE IF NOT EXISTS clientes_cache (" +
                "id INTEGER PRIMARY KEY, " +
                "nombre TEXT, " +
                "documento TEXT, " +
                "email TEXT" +
                ")";

        String sqlCategorias = "CREATE TABLE IF NOT EXISTS categorias_cache (" +
                "id INTEGER PRIMARY KEY, " +
                "nombre TEXT" +
                ")";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sqlProductos);
            stmt.execute(sqlClientes);
            stmt.execute(sqlCategorias);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

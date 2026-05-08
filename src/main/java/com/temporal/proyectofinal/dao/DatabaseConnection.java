package com.temporal.proyectofinal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestor de Conexion Persistente con Cierre Automatico Protegido
 * 
 * @author rufernecall
 */
public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://aws-1-us-west-2.pooler.supabase.com:6543/postgres?user=postgres.nwhcwwdfhfubnnvwhckl&password=mangopelado123";
    private static final String USER = "postgres.nwhcwwdfhfubnnvwhckl";
    private static final String PASSWORD = "mangopelado123";

    private static Connection connection = null;
    private static long lastUsedTime = System.currentTimeMillis();
    private static final long IDLE_TIMEOUT = 60000; // 1 minuto de inactividad
    private static java.util.Timer idleTimer = new java.util.Timer(true);

    static {
        // Cierre automatico al cerrar app
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            shutdown();
        }));

        // Timer de vigilancia para optimizacion de recursos
        idleTimer.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                checkIdle();
            }
        }, 10000, 10000); // Revisa cada 10 segundos
    }

    private DatabaseConnection() {
    }

    public synchronized static Connection getConnection() {
        try {
            lastUsedTime = System.currentTimeMillis(); // Actualizamos marca de tiempo
            if (connection == null || connection.isClosed() || !connection.isValid(2)) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("------------- Conexion establecida con Supabase.");
            }
        } catch (Exception e) {
            System.err.println("Error critico de conexion: " + e.getMessage());
        }
        return connection;
    }

    private synchronized static void checkIdle() {
        if (connection != null) {
            try {
                if (!connection.isClosed() && (System.currentTimeMillis() - lastUsedTime) > IDLE_TIMEOUT) {
                    connection.close();
                    connection = null;
                    System.out.println(">>> Conexion cerrada por inactividad (Ahorro de recursos).");
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion por inactividad: " + e.getMessage());
            }
        }
    }

    /**
     * Cierre automatico al salir
     */
    public static void shutdown() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    System.out.println(">>> Conexion con Supabase cerrada limpiamente.");
                }
            } catch (SQLException e) {
                // Silencioso al apagar
            }
        }
    }
}

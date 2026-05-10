package com.null_terminators.proyectofinal.dao;

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
    private static final long IDLE_TIMEOUT = 300000; // 5 minutos de inactividad
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
        }, 30000, 30000); // Revisa cada 30 segundos
    }

    private DatabaseConnection() {
    }

    public synchronized static Connection getConnection() {
        try {
            String motivo = "";
            if (connection == null) motivo = "Inicializacion";
            else if (connection.isClosed()) motivo = "Conexion cerrada";
            else if (!connection.isValid(1)) motivo = "Timeout/Invalida";

            if (!motivo.isEmpty()) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println(">>> Conexion establecida con Supabase [" + motivo + "]");
            }
            lastUsedTime = System.currentTimeMillis();
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
                    System.out.println(">>> Conexion liberada por inactividad (Ahorro de recursos).");
                }
            } catch (SQLException e) {
                // Silencioso
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

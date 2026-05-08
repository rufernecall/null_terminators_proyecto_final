package com.temporal.proyectofinal.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;

/**
 * Utilitario para subir archivos al Storage de Supabase
 * 
 * @author rufernecall
 */
public class SupabaseStorageUtil {

    // TODO: Reemplazar con tus credenciales reales de la consola de Supabase
    private static final String SUPABASE_URL = "https://nwhcwwdfhfubnnvwhckl.supabase.co";
    private static final String SUPABASE_KEY = "sb_publishable_xyb1ORWCw9P0mqJKVJqZrw_yWiIBJ2x";
    private static final String BUCKET_NAME = "productos";

    /**
     * Sube un archivo y devuelve la URL publica
     */
    public static String subirImagen(File archivo) throws IOException, InterruptedException {
        String fileName = System.currentTimeMillis() + "_" + archivo.getName().replaceAll("\\s+", "_");
        String uploadUrl = SUPABASE_URL + "/storage/v1/object/" + BUCKET_NAME + "/" + fileName;

        byte[] fileContent = Files.readAllBytes(archivo.toPath());
        String contentType = Files.probeContentType(archivo.toPath());
        if (contentType == null)
            contentType = "image/jpeg";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uploadUrl))
                .header("Authorization", "Bearer " + SUPABASE_KEY)
                .header("apikey", SUPABASE_KEY)
                .header("Content-Type", contentType)
                .POST(HttpRequest.BodyPublishers.ofByteArray(fileContent))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            // Devolvemos la URL publica generada
            return SUPABASE_URL + "/storage/v1/object/public/" + BUCKET_NAME + "/" + fileName;
        } else {
            throw new IOException("Fallo la subida a Supabase: " + response.body());
        }
    }
}

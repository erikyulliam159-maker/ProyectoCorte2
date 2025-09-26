/**
 * Clase TemaService
 * Proyecto: proyectocorte2frontninos
 * Paquete: co.edu.unbosque.service
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import com.google.gson.Gson;
import co.edu.unbosque.model.TemarioDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class TemaService.
 */
public class TemaService {

    /** The Constant httpClient. */
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(5)).build();

    /**
     * Do post.
     *
     * @param url the url
     * @param json the json
     * @return the string
     */
    public static String doPost(String url, String json) {
        HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(url)).setHeader("User-Agent", "Java 11 HttpClient Bot")
                .header("Content-Type", "application/json").build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return response.statusCode() + "\n" + response.body();
    }

    /**
     * Do get all.
     *
     * @param url the url
     * @return the array list
     */
    public static ArrayList<TemarioDTO> doGetAll(String url) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot").header("Content-Type", "application/json").build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        String json = response.body();
        Gson g = new Gson();
        TemarioDTO[] temps = g.fromJson(json, TemarioDTO[].class);
        return new ArrayList<>(Arrays.asList(temps));
    }

    /**
     * Do delete.
     *
     * @param url the url
     * @return the string
     */
    public static String doDelete(String url) {
        HttpRequest request = HttpRequest.newBuilder().DELETE()
                .uri(URI.create(url)).setHeader("User-Agent", "Java 11 HttpClient Bot")
                .header("Content-Type", "application/json").build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return response.statusCode() + "\n" + response.body();
    }

    /**
     * Do put.
     *
     * @param url the url
     * @param json the json
     * @return the string
     */
    public static String doPut(String url, String json) {
        HttpRequest request = HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(url)).setHeader("User-Agent", "Java 11 HttpClient Bot")
                .header("Content-Type", "application/json").build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return response.statusCode() + "\n" + response.body();
    }
}
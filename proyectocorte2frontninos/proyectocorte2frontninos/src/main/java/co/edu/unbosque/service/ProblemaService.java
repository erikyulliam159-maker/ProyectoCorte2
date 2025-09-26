/**
 * Clase ProblemaService
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
import co.edu.unbosque.model.ProblemaDTO;
import jakarta.enterprise.context.ApplicationScoped;

// TODO: Auto-generated Javadoc
/**
 * The Class ProblemaService.
 */
@ApplicationScoped
public class ProblemaService {

    /** The Constant httpClient. */
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    /** The Constant BASE. */
    private static final String BASE = "http://localhost:8081/docente/problema";

    /**
     * Do get all.
     *
     * @return the array list
     */
    public ArrayList<ProblemaDTO> doGetAll() {
        String url = BASE + "/getall";
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        System.out.println("GET All status code -> " + response.statusCode());

        if (response.statusCode() == 200) {
            String json = response.body();
            Gson g = new Gson();
            ProblemaDTO[] problemasArray = g.fromJson(json, ProblemaDTO[].class);
            return new ArrayList<>(Arrays.asList(problemasArray));
        } else {
            return new ArrayList<>();
        }
    }


    /**
     * Do get by id.
     *
     * @param id the id
     * @return the problema DTO
     */
    public ProblemaDTO doGetById(Long id) {
        String url = BASE + "/getbyid/" + id;
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return null;
        }

        System.out.println("GET By ID status code -> " + response.statusCode());

        if (response.statusCode() == 200) {
            String json = response.body();
            Gson g = new Gson();
            return g.fromJson(json, ProblemaDTO.class);
        } else {
            return null;
        }
    }


    /**
     * Do post.
     *
     * @param problema the problema
     * @return the string
     */
    public String doPost(ProblemaDTO problema) {
        Gson g = new Gson();
        String json = g.toJson(problema);
        String url = BASE + "/createjson";

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return "500|Error en la conexión: " + e.getMessage();
        }

        System.out.println("POST status code -> " + response.statusCode());
        return response.statusCode() + "|" + response.body();
    }

    /**
     * Do put.
     *
     * @param id the id
     * @param problema the problema
     * @return the string
     */
    public String doPut(Long id, ProblemaDTO problema) {
        Gson g = new Gson();
        String json = g.toJson(problema);
        String url = BASE + "/updatejson?id=" + id;

        HttpRequest request = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return "500|Error en la conexión: " + e.getMessage();
        }

        System.out.println("PUT status code -> " + response.statusCode());
        return response.statusCode() + "|" + response.body();
    }

    
    /**
     * Do delete.
     *
     * @param id the id
     * @return the string
     */
    public String doDelete(Long id) {
        String url = BASE + "/deletebyid/" + id;

        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return "500|Error en la conexión: " + e.getMessage();
        }

        System.out.println("DELETE status code -> " + response.statusCode());
        return response.statusCode() + "|" + response.body();
    }

    /**
     * Do search.
     *
     * @param termino the termino
     * @return the array list
     */
    public ArrayList<ProblemaDTO> doSearch(String termino) {
        String url = BASE + "/buscar?q=" + termino;

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        System.out.println("SEARCH status code -> " + response.statusCode());

        if (response.statusCode() == 200) {
            String json = response.body();
            Gson g = new Gson();
            ProblemaDTO[] problemasArray = g.fromJson(json, ProblemaDTO[].class);
            return new ArrayList<>(Arrays.asList(problemasArray));
        } else {
            return new ArrayList<>();
        }
    }
}
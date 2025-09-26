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
import co.edu.unbosque.model.EventoDTO;

public class EventoService {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(5)).build();

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

    public static ArrayList<EventoDTO> doGetAll(String url) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot").header("Content-Type", "application/json").build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return new ArrayList<>(); // Retorna lista vacía si hay error
        }
        if (response == null || response.body() == null || response.body().isBlank()) {
            return new ArrayList<>(); // Retorna lista vacía si no hay datos
        }
        String json = response.body();
        Gson g = new Gson();
        EventoDTO[] temps = g.fromJson(json, EventoDTO[].class);
        if (temps == null) {
            return new ArrayList<>(); // Retorna lista vacía si el JSON está vacío o mal formado
        }
        return new ArrayList<>(Arrays.asList(temps));
    }

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
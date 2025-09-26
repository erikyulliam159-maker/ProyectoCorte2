/**
 * Clase EstudianteService
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
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.UUID;

import com.google.gson.Gson;
import co.edu.unbosque.model.EstudianteDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class EstudianteService.
 */
public class EstudianteService {

	/** The Constant httpClient. */
	private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(5)).build();

	/**
	 * Do post multipart.
	 *
	 * @param url the url
	 * @param dto the dto
	 * @param imagenPath the imagen path
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public static String doPostMultipart(String url, EstudianteDTO dto, Path imagenPath) throws IOException, InterruptedException {
	    String boundary = "----WebKitFormBoundary" + UUID.randomUUID().toString().replace("-", "");

	    StringBuilder sb = new StringBuilder();
	    sb.append("--").append(boundary).append("\r\n");
	    sb.append("Content-Disposition: form-data; name=\"username\"\r\n\r\n");
	    sb.append(dto.getUsername()).append("\r\n");

	    sb.append("--").append(boundary).append("\r\n");
	    sb.append("Content-Disposition: form-data; name=\"password\"\r\n\r\n");
	    sb.append(dto.getPassword()).append("\r\n");

	    sb.append("--").append(boundary).append("\r\n");
	    sb.append("Content-Disposition: form-data; name=\"nombreCompleto\"\r\n\r\n");
	    sb.append(dto.getNombreCompleto()).append("\r\n");

	    sb.append("--").append(boundary).append("\r\n");
	    sb.append("Content-Disposition: form-data; name=\"email\"\r\n\r\n");
	    sb.append(dto.getEmail()).append("\r\n");

	    byte[] bodyStart = sb.toString().getBytes();
	    byte[] bodyEnd = ("\r\n--" + boundary + "--\r\n").getBytes();

	    byte[] imagenBytes = imagenPath != null ? Files.readAllBytes(imagenPath) : new byte[0];
	    byte[] imagenPart = new byte[0];

	    if (imagenPath != null) {
	        StringBuilder imgSb = new StringBuilder();
	        imgSb.append("--").append(boundary).append("\r\n");
	        imgSb.append("Content-Disposition: form-data; name=\"file\"; filename=\"")
	             .append(imagenPath.getFileName()).append("\"\r\n");
	        imgSb.append("Content-Type: ").append(Files.probeContentType(imagenPath)).append("\r\n\r\n");
	        imagenPart = imgSb.toString().getBytes();
	    }

	    byte[] body;
	    if (imagenPath != null) {
	        body = new byte[bodyStart.length + imagenPart.length + imagenBytes.length + bodyEnd.length];
	        System.arraycopy(bodyStart, 0, body, 0, bodyStart.length);
	        System.arraycopy(imagenPart, 0, body, bodyStart.length, imagenPart.length);
	        System.arraycopy(imagenBytes, 0, body, bodyStart.length + imagenPart.length, imagenBytes.length);
	        System.arraycopy(bodyEnd, 0, body, bodyStart.length + imagenPart.length + imagenBytes.length, bodyEnd.length);
	    } else {
	        body = new byte[bodyStart.length + bodyEnd.length];
	        System.arraycopy(bodyStart, 0, body, 0, bodyStart.length);
	        System.arraycopy(bodyEnd, 0, body, bodyStart.length, bodyEnd.length);
	    }

	    HttpRequest request = HttpRequest.newBuilder()
	        .uri(URI.create(url))
	        .header("Content-Type", "multipart/form-data; boundary=" + boundary)
	        .POST(HttpRequest.BodyPublishers.ofByteArray(body))
	        .build();

	    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
	    return response.body();
	}
	
	/**
	 * Do get all.
	 *
	 * @param url the url
	 * @return the array list
	 */
	public static ArrayList<EstudianteDTO> doGetAll(String url) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.setHeader("User-Agent", "Java 11 HttpClient Bot").header("Content-Type", "application/json").build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		System.out.println("get status code -> " + response.statusCode());
		String json = response.body();
		Gson g = new Gson();
		EstudianteDTO[] temps = g.fromJson(json, EstudianteDTO[].class);
		return new ArrayList<>(Arrays.asList(temps));
	}

	/**
	 * Do get.
	 *
	 * @param url the url
	 * @return the string
	 */
	public static String doGet(String url) {
		 HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
		            .setHeader("User-Agent", "Java 11 HttpClient Bot")
		            .header("Content-Type", "application/json").build();

		    HttpResponse<String> response = null;
		    try {
		        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		    } catch (InterruptedException | IOException e) {
		        e.printStackTrace();
		        return "500|Error en la conexión: " + e.getMessage();
		    }
		    
		    System.out.println("get status code -> " + response.statusCode());
		    return response.statusCode() + "|" + response.body();
	}
}
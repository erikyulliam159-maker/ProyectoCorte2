/**
 * Clase DocenteService
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
import co.edu.unbosque.model.DocenteDTO;
import co.edu.unbosque.model.LibroDTO;
import co.edu.unbosque.model.ProblemaDTO;
import co.edu.unbosque.model.TemarioDTO;


import java.nio.file.Files;
import java.nio.file.Path;

import java.util.UUID;

// TODO: Auto-generated Javadoc
/**
 * The Class DocenteService.
 */
public class DocenteService {

	/** The Constant httpClient. */
	private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(5)).build();

	/** The Constant BASE_URL. */
	private static final String BASE_URL = "http://localhost:8080/proyectocorte2back/docente";
	
	/** The Constant gson. */
	private static final Gson gson = new Gson();


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

		try {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("POST Status Code: " + response.statusCode());
			return response.statusCode() + "|" + response.body();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
			return "500|Error: " + e.getMessage();
		}
	}

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
	public static String doPostMultipart(String url, DocenteDTO dto, Path imagenPath) throws IOException, InterruptedException {
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

		try {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("PUT Status Code: " + response.statusCode());
			return response.statusCode() + "|" + response.body();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
			return "500|Error: " + e.getMessage();
		}
	}

	/**
	 * Do delete.
	 *
	 * @param url the url
	 * @return the string
	 */
	public static String doDelete(String url) {
		HttpRequest request = HttpRequest.newBuilder().DELETE().uri(URI.create(url))
				.setHeader("User-Agent", "Java 11 HttpClient Bot").header("Content-Type", "application/json").build();

		try {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("DELETE Status Code: " + response.statusCode());
			return response.statusCode() + "|" + response.body();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
			return "500|Error: " + e.getMessage();
		}
	}

	/**
	 * Do get all.
	 *
	 * @param url the url
	 * @return the array list
	 */
	public static ArrayList<DocenteDTO> doGetAll(String url) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.setHeader("User-Agent", "Java 11 HttpClient Bot").header("Content-Type", "application/json").build();

		try {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("GET Status Code: " + response.statusCode());
			String json = response.body();
			DocenteDTO[] temps = gson.fromJson(json, DocenteDTO[].class);
			return new ArrayList<>(Arrays.asList(temps));
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}


	/**
	 * Obtener todos temarios.
	 *
	 * @return the array list
	 */
	public ArrayList<TemarioDTO> obtenerTodosTemarios() {
		try {
			String url = BASE_URL + "/temario/getall";
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
					.setHeader("User-Agent", "Java 11 HttpClient Bot").header("Content-Type", "application/json")
					.build();

			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			String json = response.body();
			TemarioDTO[] temarios = gson.fromJson(json, TemarioDTO[].class);
			return new ArrayList<>(Arrays.asList(temarios));
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	/**
	 * Crear temario.
	 *
	 * @param temario the temario
	 * @return true, if successful
	 */
	public boolean crearTemario(TemarioDTO temario) {
		try {
			String url = BASE_URL + "/temario/createjson";
			String json = gson.toJson(temario);
			String response = doPost(url, json);
			return response.startsWith("201|");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Eliminar temario.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean eliminarTemario(Long id) {
		try {
			String url = BASE_URL + "/temario/deletebyid/" + id;
			String response = doDelete(url);
			return response.startsWith("202|");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * Obtener todos problemas.
	 *
	 * @return the array list
	 */
	public ArrayList<ProblemaDTO> obtenerTodosProblemas() {
		try {
			String url = BASE_URL + "/problema/getall";
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
					.setHeader("User-Agent", "Java 11 HttpClient Bot").header("Content-Type", "application/json")
					.build();

			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			String json = response.body();
			ProblemaDTO[] problemas = gson.fromJson(json, ProblemaDTO[].class);
			return new ArrayList<>(Arrays.asList(problemas));
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	/**
	 * Crear problema.
	 *
	 * @param problema the problema
	 * @return true, if successful
	 */
	public boolean crearProblema(ProblemaDTO problema) {
		try {
			String url = BASE_URL + "/problema/createjson";
			String json = gson.toJson(problema);
			String response = doPost(url, json);
			return response.startsWith("201|");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Eliminar problema.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean eliminarProblema(Long id) {
		try {
			String url = BASE_URL + "/problema/deletebyid/" + id;
			String response = doDelete(url);
			return response.startsWith("202|");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Obtener todos libros.
	 *
	 * @return the array list
	 */
	// Métodos específicos para Libros
	public ArrayList<LibroDTO> obtenerTodosLibros() {
		try {
			String url = BASE_URL + "/libro/getall";
			HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
					.setHeader("User-Agent", "Java 11 HttpClient Bot").header("Content-Type", "application/json")
					.build();

			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			String json = response.body();
			LibroDTO[] libros = gson.fromJson(json, LibroDTO[].class);
			return new ArrayList<>(Arrays.asList(libros));
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	/**
	 * Crear libro.
	 *
	 * @param libro the libro
	 * @return true, if successful
	 */
	public boolean crearLibro(LibroDTO libro) {
		try {
			String url = BASE_URL + "/libro/createjson";
			String json = gson.toJson(libro);
			String response = doPost(url, json);
			return response.startsWith("201|");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Eliminar libro.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean eliminarLibro(Long id) {
		try {
			String url = BASE_URL + "/libro/deletebyid/" + id;
			String response = doDelete(url);
			return response.startsWith("202|");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Do get all.
	 *
	 * @param <T> the generic type
	 * @param url the url
	 * @param clazz the clazz
	 * @return the array list
	 */
	// Métodos genéricos para diferentes tipos de DTO
	public static <T> ArrayList<T> doGetAll(String url, Class<T[]> clazz) {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url))
				.setHeader("User-Agent", "Java 11 HttpClient Bot").header("Content-Type", "application/json").build();

		try {
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("GET Status Code: " + response.statusCode());
			String json = response.body();
			T[] temps = gson.fromJson(json, clazz);
			return new ArrayList<>(Arrays.asList(temps));
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
}
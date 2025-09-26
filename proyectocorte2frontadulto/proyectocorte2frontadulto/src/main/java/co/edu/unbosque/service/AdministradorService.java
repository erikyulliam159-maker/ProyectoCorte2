/**
 * Clase AdministradorService
 * Proyecto: proyectocorte2frontadulto
 * Paquete: co.edu.unbosque.service
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.service;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;

import co.edu.unbosque.controller.HttpClientSynchronous;
import co.edu.unbosque.model.DocenteDTO;
import co.edu.unbosque.model.EstudianteDTO;
import co.edu.unbosque.model.EventoDTO;
import co.edu.unbosque.model.LinkValiosoDTO;
import jakarta.servlet.http.Part;

// TODO: Auto-generated Javadoc
/**
 * The Class AdministradorService.
 */
public class AdministradorService {
	
	/** The Constant BASE_URL. */
	private static final String BASE_URL = "http://localhost:8081/admin";
	
	/** The Constant gson. */
	private static final Gson gson = new Gson();

	/**
	 * Obtener todos eventos.
	 *
	 * @return the array list
	 */
	public ArrayList<DocenteDTO> obtenerTodosEventos() {
		try {
			String url = BASE_URL + "/evento/getall";
			return DocenteService.doGetAll(url);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	/**
	 * Crear evento.
	 *
	 * @param evento the evento
	 * @return true, if successful
	 */
	public boolean crearEvento(EventoDTO evento) {
		try {
			String url = BASE_URL + "/evento/create";
			String json = gson.toJson(evento);
			String response = DocenteService.doPost(url, json);
			return response.startsWith("201|");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Actualizar evento.
	 *
	 * @param id the id
	 * @param evento the evento
	 * @return true, if successful
	 */
	public boolean actualizarEvento(Long id, EventoDTO evento) {
		try {
			String url = BASE_URL + "/evento/update?id=" + id;
			String json = gson.toJson(evento);
			String response = DocenteService.doPut(url, json);
			return response.startsWith("202|");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Eliminar evento.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean eliminarEvento(Long id) {
		try {
			String url = BASE_URL + "/evento/deletebyid/" + id;
			String response = DocenteService.doDelete(url);
			return response.startsWith("202|");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Obtener todos links.
	 *
	 * @return the list
	 */
	public List<LinkValiosoDTO> obtenerTodosLinks() {
		try {
			String url = BASE_URL + "/linkvalioso/getall";

			return new ArrayList<>();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	/**
	 * Crear link.
	 *
	 * @param link the link
	 * @return true, if successful
	 */
	public boolean crearLink(LinkValiosoDTO link) {
		try {
			String url = BASE_URL + "/linkvalioso/create";
			String json = gson.toJson(link);
			String response = DocenteService.doPost(url, json);
			return response.startsWith("201|");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Eliminar link.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean eliminarLink(Long id) {
		try {
			String url = BASE_URL + "/linkvalioso/deletebyid/" + id;
			String response = DocenteService.doDelete(url);
			return response.startsWith("200|");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Obtener todos docentes.
	 *
	 * @return the list
	 */
	public List<DocenteDTO> obtenerTodosDocentes() {
		try {
			String url = BASE_URL + "/docente/getall";
			return DocenteService.doGetAll(url);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	/**
	 * Actualizar docente con imagen.
	 *
	 * @param docente the docente
	 * @param fotoPerfilPart the foto perfil part
	 * @return true, if successful
	 */
	public boolean actualizarDocenteConImagen(DocenteDTO docente, Part fotoPerfilPart) {
		try {
			String boundary = "----WebKitFormBoundary" + UUID.randomUUID().toString().replace("-", "");
			List<byte[]> byteArrays = new ArrayList<>();

			byteArrays.add(("--" + boundary + "\r\n").getBytes());
			byteArrays
					.add(("Content-Disposition: form-data; name=\"id\"\r\n\r\n" + docente.getId() + "\r\n").getBytes());

			byteArrays.add(("--" + boundary + "\r\n").getBytes());
			byteArrays
					.add(("Content-Disposition: form-data; name=\"username\"\r\n\r\n" + docente.getUsername() + "\r\n")
							.getBytes());

			byteArrays.add(("--" + boundary + "\r\n").getBytes());
			byteArrays
					.add(("Content-Disposition: form-data; name=\"password\"\r\n\r\n" + docente.getPassword() + "\r\n")
							.getBytes());

			byteArrays.add(("--" + boundary + "\r\n").getBytes());
			byteArrays.add(("Content-Disposition: form-data; name=\"nombreCompleto\"\r\n\r\n"
					+ docente.getNombreCompleto() + "\r\n").getBytes());

			byteArrays.add(("--" + boundary + "\r\n").getBytes());
			byteArrays.add(("Content-Disposition: form-data; name=\"email\"\r\n\r\n" + docente.getEmail() + "\r\n")
					.getBytes());

			if (fotoPerfilPart != null && fotoPerfilPart.getSize() > 0) {
				byteArrays.add(("--" + boundary + "\r\n").getBytes());
				byteArrays.add(("Content-Disposition: form-data; name=\"file\"; filename=\""
						+ fotoPerfilPart.getSubmittedFileName() + "\"\r\n").getBytes());
				byteArrays.add(("Content-Type: " + fotoPerfilPart.getContentType() + "\r\n\r\n").getBytes());
				try (InputStream is = fotoPerfilPart.getInputStream()) {
					byteArrays.add(is.readAllBytes());
				}
				byteArrays.add("\r\n".getBytes());
			}

			byteArrays.add(("--" + boundary + "--\r\n").getBytes());

			BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofByteArrays(byteArrays);

			HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8081/admin/docente/update"))
					.header("Content-Type", "multipart/form-data; boundary=" + boundary).PUT(bodyPublisher).build();

			HttpClient httpClient = HttpClient.newBuilder().build();
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			return response.statusCode() == 200;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Eliminar docente.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean eliminarDocente(Long id) {
		try {
			String url = BASE_URL + "/docente/deletebyid/" + id;
			String response = DocenteService.doDelete(url);
			return response.startsWith("200|");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Obtener todos estudiantes.
	 *
	 * @return the list
	 */
	public List<EstudianteDTO> obtenerTodosEstudiantes() {
		try {
			String url = BASE_URL + "/estudiante/getall";
			return EstudianteService.doGetAll(url);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	/**
	 * Actualizar estudiante con imagen.
	 *
	 * @param estudiante the estudiante
	 * @param fotoPerfilPart the foto perfil part
	 * @return true, if successful
	 */
	public boolean actualizarEstudianteConImagen(EstudianteDTO estudiante, Part fotoPerfilPart) {
		try {
			String boundary = "----WebKitFormBoundary" + UUID.randomUUID().toString().replace("-", "");
			List<byte[]> byteArrays = new ArrayList<>();

			// Campos de texto
			byteArrays.add(("--" + boundary + "\r\n").getBytes());
			byteArrays.add(
					("Content-Disposition: form-data; name=\"id\"\r\n\r\n" + estudiante.getId() + "\r\n").getBytes());

			byteArrays.add(("--" + boundary + "\r\n").getBytes());
			byteArrays.add(
					("Content-Disposition: form-data; name=\"username\"\r\n\r\n" + estudiante.getUsername() + "\r\n")
							.getBytes());

			byteArrays.add(("--" + boundary + "\r\n").getBytes());
			byteArrays.add(
					("Content-Disposition: form-data; name=\"password\"\r\n\r\n" + estudiante.getPassword() + "\r\n")
							.getBytes());

			byteArrays.add(("--" + boundary + "\r\n").getBytes());
			byteArrays.add(("Content-Disposition: form-data; name=\"nombreCompleto\"\r\n\r\n"
					+ estudiante.getNombreCompleto() + "\r\n").getBytes());

			byteArrays.add(("--" + boundary + "\r\n").getBytes());
			byteArrays.add(("Content-Disposition: form-data; name=\"email\"\r\n\r\n" + estudiante.getEmail() + "\r\n")
					.getBytes());

			if (fotoPerfilPart != null && fotoPerfilPart.getSize() > 0) {
				byteArrays.add(("--" + boundary + "\r\n").getBytes());
				byteArrays.add(("Content-Disposition: form-data; name=\"file\"; filename=\""
						+ fotoPerfilPart.getSubmittedFileName() + "\"\r\n").getBytes());
				byteArrays.add(("Content-Type: " + fotoPerfilPart.getContentType() + "\r\n\r\n").getBytes());
				try (InputStream is = fotoPerfilPart.getInputStream()) {
					byteArrays.add(is.readAllBytes());
				}
				byteArrays.add("\r\n".getBytes());
			}

			byteArrays.add(("--" + boundary + "--\r\n").getBytes());

			BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofByteArrays(byteArrays);

			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("http://localhost:8081/admin/estudiante/update"))
					.header("Content-Type", "multipart/form-data; boundary=" + boundary).PUT(bodyPublisher).build();

			HttpClient httpClient = HttpClient.newBuilder().build();
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			return response.statusCode() == 200;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Eliminar estudiante.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean eliminarEstudiante(Long id) {
		try {
			String url = BASE_URL + "/estudiante/deletebyid/" + id;
			String response = DocenteService.doDelete(url);
			return response.startsWith("200|");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
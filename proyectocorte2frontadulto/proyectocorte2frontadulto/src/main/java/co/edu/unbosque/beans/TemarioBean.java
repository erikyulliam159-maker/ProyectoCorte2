/**
 * Clase TemarioBean
 * Proyecto: proyectocorte2frontadulto
 * Paquete: co.edu.unbosque.beans
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.beans;

import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import co.edu.unbosque.model.TemarioDTO;
import co.edu.unbosque.service.TemaService;
import co.edu.unbosque.model.DetalleSubtemaDTO;
import co.edu.unbosque.model.SubtemaDTO;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;

import com.google.gson.Gson;

// TODO: Auto-generated Javadoc
/**
 * The Class TemarioBean.
 */
@Named("temarioBean")
@ViewScoped

public class TemarioBean implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The temarios. */
	private List<TemarioDTO> temarios = new ArrayList<>();
	
	/** The nuevo temario. */
	private TemarioDTO nuevoTemario = new TemarioDTO();
	
	/** The nuevo subtema. */
	private SubtemaDTO nuevoSubtema = new SubtemaDTO();
	
	/** The message. */
	private String message;
	
	/** The message type. */
	private String messageType;

	/**
	 * Inicializa el.
	 */
	@PostConstruct
	public void init() {
		cargarTemarios();
	}

	/**
	 * Instantiates a new temario bean.
	 */
	public TemarioBean() {
		nuevoTemario = new TemarioDTO();
		nuevoSubtema = new SubtemaDTO();
		nuevoSubtema.setDetalle(new DetalleSubtemaDTO());
	}

	/**
	 * Cargar temarios.
	 */
	public void cargarTemarios() {
		try {
			temarios = TemaService.doGetAll("http://localhost:8081/docente/temario/getall");
			message = null;
		} catch (Exception e) {
			message = "Error al cargar temarios: " + e.getMessage();
			messageType = "error";
		}
	}

	/**
	 * Eliminar temario.
	 *
	 * @param id the id
	 */
	public void eliminarTemario(Long id) {
		try {
			String respuesta = TemaService.doDelete("http://localhost:8081/docente/temario/deletebyid/" + id);
			if (respuesta.startsWith("202") || respuesta.startsWith("200")) {
				message = "Temario eliminado exitosamente";
				messageType = "success";
				cargarTemarios();
			} else {
				message = "Error al eliminar temario";
				messageType = "error";
			}
		} catch (Exception e) {
			message = "Error: " + e.getMessage();
			messageType = "error";
		}
	}

	/**
	 * Actualizar temario.
	 *
	 * @param temario the temario
	 */
	public void actualizarTemario(TemarioDTO temario) {
		this.nuevoTemario = clonarTemario(temario);
		nuevoSubtema = new SubtemaDTO();
		nuevoSubtema.setDetalle(new DetalleSubtemaDTO());
	}

	/**
	 * Guardar actualizacion.
	 */
	public void guardarActualizacion() {
		try {
			Gson gson = new Gson();
			String json = gson.toJson(nuevoTemario);
			System.out.println("ID a actualizar: " + nuevoTemario.getId());
			System.out.println("JSON enviado: " + json);
			String respuesta = TemaService
					.doPut("http://localhost:8081/docente/temario/updatejson?id=" + nuevoTemario.getId(), json);
			System.out.println("Respuesta backend: " + respuesta);
			if (respuesta.startsWith("202") || respuesta.startsWith("200")) {

				message = "Temario actualizado exitosamente";
				messageType = "success";
				cargarTemarios();
				nuevoTemario = new TemarioDTO();
				nuevoSubtema = new SubtemaDTO();
				nuevoSubtema.setDetalle(new DetalleSubtemaDTO());
			} else {
				message = "Error al actualizar temario";
				messageType = "error";
			}
		} catch (Exception e) {
			message = "Error: " + e.getMessage();
			messageType = "error";
		}
	}


	/**
	 * Clonar temario.
	 *
	 * @param temario the temario
	 * @return the temario DTO
	 */
	private TemarioDTO clonarTemario(TemarioDTO temario) {
		Gson gson = new Gson();
		String json = gson.toJson(temario);
		return gson.fromJson(json, TemarioDTO.class);
	}

	/**
	 * Agregar subtema.
	 */
	public void agregarSubtema() {
		System.out.println("Agregando subtema...");
		if (nuevoTemario.getSubtemas() == null) {
			nuevoTemario.setSubtemas(new ArrayList<>());
		}
		nuevoSubtema.setTemarioTitulo(nuevoTemario.getTitulo());
		if (nuevoSubtema.getDetalle() != null) {
			nuevoSubtema.getDetalle().setSubtemaNombre(nuevoSubtema.getNombre());
		}

		SubtemaDTO subtemaClonado = clonarSubtema(nuevoSubtema);
		nuevoTemario.getSubtemas().add(subtemaClonado);
		System.out.println("Subtemas agregados: " + nuevoTemario.getSubtemas().size());

		nuevoSubtema = new SubtemaDTO();
		nuevoSubtema.setDetalle(new DetalleSubtemaDTO());
	}

	/**
	 * Eliminar subtema.
	 *
	 * @param index the index
	 */
	public void eliminarSubtema(int index) {
		if (nuevoTemario.getSubtemas() != null && index >= 0 && index < nuevoTemario.getSubtemas().size()) {
			nuevoTemario.getSubtemas().remove(index);
		}
	}

	/**
	 * Clonar subtema.
	 *
	 * @param subtema the subtema
	 * @return the subtema DTO
	 */
	private SubtemaDTO clonarSubtema(SubtemaDTO subtema) {
		Gson gson = new Gson();
		String json = gson.toJson(subtema);
		return gson.fromJson(json, SubtemaDTO.class);
	}

	/**
	 * Crear temario.
	 */
	// ...existing code...
	public void crearTemario() {
		try {

			if (nuevoTemario.getSubtemas() == null) {
				nuevoTemario.setSubtemas(new ArrayList<>());
			}

			nuevoTemario.setId(null);
			for (SubtemaDTO subtema : nuevoTemario.getSubtemas()) {
				subtema.setId(null);
				subtema.setTemarioTitulo(nuevoTemario.getTitulo());
				if (subtema.getDetalle() != null) {
					subtema.getDetalle().setId(null);
					subtema.getDetalle().setSubtemaNombre(subtema.getNombre());
				}
			}

			URL url = new URL("http://localhost:8081/docente/temario/createjson");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			Gson gson = new Gson();
			String json = gson.toJson(nuevoTemario);
			System.out.println("JSON enviado: " + json);

			try (OutputStream os = conn.getOutputStream()) {
				os.write(json.getBytes());
			}

			int status = conn.getResponseCode();
			if (status == 200 || status == 201) {
				message = "Temario creado exitosamente!";
				messageType = "success";
				cargarTemarios();

				nuevoTemario = new TemarioDTO();
				nuevoSubtema = new SubtemaDTO();
				nuevoSubtema.setDetalle(new DetalleSubtemaDTO());
			} else {
				message = "Error al crear temario: " + conn.getResponseMessage();
				messageType = "error";
			}
			conn.disconnect();
		} catch (Exception e) {
			message = "Error: " + e.getMessage();
			messageType = "error";
		}
	}

	// ...existing code...
	/**
	 * Gets the temarios.
	 *
	 * @return the temarios
	 */
	// Getters y Setters
	public List<TemarioDTO> getTemarios() {
		return temarios;
	}

	/**
	 * Gets the nuevo temario.
	 *
	 * @return the nuevo temario
	 */
	public TemarioDTO getNuevoTemario() {
		return nuevoTemario;
	}

	/**
	 * Sets the nuevo temario.
	 *
	 * @param nuevoTemario the new nuevo temario
	 */
	public void setNuevoTemario(TemarioDTO nuevoTemario) {
		this.nuevoTemario = nuevoTemario;
	}

	/**
	 * Gets the nuevo subtema.
	 *
	 * @return the nuevo subtema
	 */
	public SubtemaDTO getNuevoSubtema() {
		if (nuevoSubtema.getDetalle() == null) {
			nuevoSubtema.setDetalle(new DetalleSubtemaDTO());
		}
		return nuevoSubtema;
	}

	/**
	 * Sets the nuevo subtema.
	 *
	 * @param nuevoSubtema the new nuevo subtema
	 */
	public void setNuevoSubtema(SubtemaDTO nuevoSubtema) {
		this.nuevoSubtema = nuevoSubtema;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the message type.
	 *
	 * @return the message type
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * Sets the message type.
	 *
	 * @param messageType the new message type
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
}

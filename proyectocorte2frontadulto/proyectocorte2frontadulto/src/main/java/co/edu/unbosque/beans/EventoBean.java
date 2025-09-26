/**
 * Clase EventoBean
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

import co.edu.unbosque.model.EventoDTO;
import co.edu.unbosque.service.EventoService;
import co.edu.unbosque.util.LocalDateAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.time.LocalDate;

// TODO: Auto-generated Javadoc
/**
 * The Class EventoBean.
 */
@Named("eventoBean")
@ViewScoped
public class EventoBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The eventos. */
	private List<EventoDTO> eventos;
	
	/** The nuevo evento. */
	private EventoDTO nuevoEvento;
	
	/** The evento seleccionado. */
	private EventoDTO eventoSeleccionado;

	/** The base url. */
	private final String BASE_URL = "http://localhost:8081/admin/evento";
	
	/** The gson. */
	private final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

	/**
	 * Inicializa el.
	 */
	@PostConstruct
	public void init() {
		
		eventos = new ArrayList<>();
		nuevoEvento = new EventoDTO();
		eventoSeleccionado = new EventoDTO();
		cargarEventos();
	}

	/**
	 * Cargar eventos.
	 */
	public void cargarEventos() {
		try {
			eventos = EventoService.doGetAll(BASE_URL + "/getall");
		
			if (eventos == null) {
				eventos = new ArrayList<>();
			}
		} catch (Exception e) {
			eventos = new ArrayList<>();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar los eventos"));
		}
	}

	/**
	 * Agregar evento.
	 */
	public void agregarEvento() {
		System.out.println("Agregar evento invocado");
		try {
			String json = gson.toJson(nuevoEvento);
			System.out.println("JSON enviado: " + json);
			String respuesta = EventoService.doPost(BASE_URL + "/create", json);
			System.out.println("Respuesta backend: " + respuesta);
			if (respuesta.startsWith("201")) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Evento agregado correctamente"));
				cargarEventos();
				nuevoEvento = new EventoDTO();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", respuesta));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo agregar el evento"));
			e.printStackTrace();
		}
	}

	/**
	 * Actualizar evento.
	 *
	 * @param evento the evento
	 */
	public void actualizarEvento(EventoDTO evento) {
		try {
			String json = gson.toJson(evento);
			String respuesta = EventoService.doPut(BASE_URL + "/update?id=" + evento.getId(), json);
			if (respuesta.startsWith("202") || respuesta.startsWith("200")) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Evento actualizado correctamente"));
				cargarEventos();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", respuesta));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el evento"));
		}
	}

	/**
	 * Eliminar evento.
	 *
	 * @param evento the evento
	 */
	public void eliminarEvento(EventoDTO evento) {
		try {
			String respuesta = EventoService.doDelete(BASE_URL + "/deletebyid/" + evento.getId());
			if (respuesta.startsWith("202") || respuesta.startsWith("200")) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Evento eliminado correctamente"));
				cargarEventos();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", respuesta));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el evento"));
		}
	}

	/**
	 * Gets the eventos.
	 *
	 * @return the eventos
	 */
	// Getters y setters
	public List<EventoDTO> getEventos() {
		return eventos;
	}

	/**
	 * Sets the eventos.
	 *
	 * @param eventos the new eventos
	 */
	public void setEventos(List<EventoDTO> eventos) {
		this.eventos = eventos;
	}

	/**
	 * Gets the nuevo evento.
	 *
	 * @return the nuevo evento
	 */
	public EventoDTO getNuevoEvento() {
		return nuevoEvento;
	}

	/**
	 * Sets the nuevo evento.
	 *
	 * @param nuevoEvento the new nuevo evento
	 */
	public void setNuevoEvento(EventoDTO nuevoEvento) {
		this.nuevoEvento = nuevoEvento;
	}

	/**
	 * Gets the evento seleccionado.
	 *
	 * @return the evento seleccionado
	 */
	public EventoDTO getEventoSeleccionado() {
		return eventoSeleccionado;
	}

	/**
	 * Sets the evento seleccionado.
	 *
	 * @param eventoSeleccionado the new evento seleccionado
	 */
	public void setEventoSeleccionado(EventoDTO eventoSeleccionado) {
		this.eventoSeleccionado = eventoSeleccionado;
	}
}
/**
 * Clase ProblemaBean
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

import co.edu.unbosque.model.ProblemaDTO;
import co.edu.unbosque.service.ProblemaService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

// TODO: Auto-generated Javadoc
/**
 * The Class ProblemaBean.
 */
@Named("problemaBean")
@ViewScoped
public class ProblemaBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The problemas. */
	private List<ProblemaDTO> problemas;
	
	/** The problema seleccionado. */
	private ProblemaDTO problemaSeleccionado;
	
	/** The problema service. */
	private ProblemaService problemaService;

	/**
	 * Inicializa el.
	 */
	@PostConstruct
	public void init() {
		problemas = new ArrayList<>();
		problemaSeleccionado = new ProblemaDTO();
		problemaService = new ProblemaService();
		cargarProblemas();
	}

	/**
	 * Cargar problemas.
	 */
	public void cargarProblemas() {
		problemas = problemaService.doGetAll();
	}

	/**
	 * Guardar problema.
	 */
	public void guardarProblema() {
		try {
			if (problemaSeleccionado.getId() == null) {
				String respuesta = problemaService.doPost(problemaSeleccionado);
				if (respuesta.startsWith("201")) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Problema creado correctamente"));
					cargarProblemas();
					problemaSeleccionado = new ProblemaDTO();
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", respuesta));
				}
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo crear el problema"));
		}
	}

	/**
	 * Editar problema.
	 *
	 * @param problema the problema
	 */
	public void editarProblema(ProblemaDTO problema) {
		this.problemaSeleccionado = new ProblemaDTO(problema.getId(), problema.getTitulo(), problema.getDificultad(),
				problema.getTema(), problema.getJuez(), problema.getUrl());
	}

	/**
	 * Guardar edicion.
	 */
	public void guardarEdicion() {
		try {
			if (problemaSeleccionado.getId() != null) {
				String respuesta = problemaService.doPut(problemaSeleccionado.getId(), problemaSeleccionado);
				if (respuesta.startsWith("202")) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Éxito", "Problema actualizado correctamente"));
					cargarProblemas();
					problemaSeleccionado = new ProblemaDTO();
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", respuesta));
				}
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el problema"));
		}
	}

	/**
	 * Eliminar problema.
	 *
	 * @param problema the problema
	 */
	public void eliminarProblema(ProblemaDTO problema) {
		try {
			String respuesta = problemaService.doDelete(problema.getId());
			if (respuesta.startsWith("202")) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Problema eliminado correctamente"));
				cargarProblemas();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", respuesta));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el problema"));
		}
	}

	/**
	 * Cancelar edicion.
	 */
	public void cancelarEdicion() {
		problemaSeleccionado = new ProblemaDTO();
	}

	// Getters y setters

	/**
	 * Gets the problemas.
	 *
	 * @return the problemas
	 */
	public List<ProblemaDTO> getProblemas() {
		return problemas;
	}

	/**
	 * Sets the problemas.
	 *
	 * @param problemas the new problemas
	 */
	public void setProblemas(List<ProblemaDTO> problemas) {
		this.problemas = problemas;
	}

	/**
	 * Gets the problema seleccionado.
	 *
	 * @return the problema seleccionado
	 */
	public ProblemaDTO getProblemaSeleccionado() {
		return problemaSeleccionado;
	}

	/**
	 * Sets the problema seleccionado.
	 *
	 * @param problemaSeleccionado the new problema seleccionado
	 */
	public void setProblemaSeleccionado(ProblemaDTO problemaSeleccionado) {
		this.problemaSeleccionado = problemaSeleccionado;
	}
}
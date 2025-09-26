/**
 * Clase LinkValiosoBean
 * Proyecto: proyectocorte2frontninos
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

import co.edu.unbosque.model.LinkValiosoDTO;
import co.edu.unbosque.service.LinkValiosoService;
import com.google.gson.Gson;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

// TODO: Auto-generated Javadoc
/**
 * The Class LinkValiosoBean.
 */
@Named("linkValiosoBean")
@ViewScoped
public class LinkValiosoBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The links. */
    private List<LinkValiosoDTO> links;
    
    /** The nuevo link. */
    private LinkValiosoDTO nuevoLink;
    
    /** The link seleccionado. */
    private LinkValiosoDTO linkSeleccionado;

    /** The base url. */
    private final String BASE_URL = "http://localhost:8081/admin/linkvalioso";

    /**
     * Inicializa el.
     */
    @PostConstruct
    public void init() {
        links = new ArrayList<>();
        nuevoLink = new LinkValiosoDTO();
        linkSeleccionado = new LinkValiosoDTO();
        cargarLinks();
    }

    /**
     * Cargar links.
     */
    public void cargarLinks() {
        try {
            links = LinkValiosoService.doGetAll(BASE_URL + "/getall");
            if (links == null) {
                links = new ArrayList<>();
            }
        } catch (Exception e) {
            links = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar los links"));
        }
    }

    /**
     * Agregar link.
     */
    public void agregarLink() {
        try {
            Gson g = new Gson();
            String json = g.toJson(nuevoLink);
            String respuesta = LinkValiosoService.doPost(BASE_URL + "/create", json);
            if (respuesta.startsWith("201")) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Link agregado correctamente"));
                cargarLinks();
                nuevoLink = new LinkValiosoDTO();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", respuesta));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo agregar el link"));
        }
    }

    /**
     * Actualizar link.
     *
     * @param link the link
     */
    public void actualizarLink(LinkValiosoDTO link) {
        try {
            Gson g = new Gson();
            String json = g.toJson(link);
            String respuesta = LinkValiosoService.doPut(BASE_URL + "/update?id=" + link.getId(), json);
            if (respuesta.startsWith("200")) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Link actualizado correctamente"));
                cargarLinks();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", respuesta));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el link"));
        }
    }

    /**
     * Eliminar link.
     *
     * @param link the link
     */
    public void eliminarLink(LinkValiosoDTO link) {
        try {
            String respuesta = LinkValiosoService.doDelete(BASE_URL + "/deletebyid/" + link.getId());
            if (respuesta.startsWith("200")) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Link eliminado correctamente"));
                cargarLinks();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", respuesta));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el link"));
        }
    }

    
    /**
     * Gets the links.
     *
     * @return the links
     */
    public List<LinkValiosoDTO> getLinks() {
        return links;
    }

    /**
     * Sets the links.
     *
     * @param links the new links
     */
    public void setLinks(List<LinkValiosoDTO> links) {
        this.links = links;
    }

    /**
     * Gets the nuevo link.
     *
     * @return the nuevo link
     */
    public LinkValiosoDTO getNuevoLink() {
        return nuevoLink;
    }

    /**
     * Sets the nuevo link.
     *
     * @param nuevoLink the new nuevo link
     */
    public void setNuevoLink(LinkValiosoDTO nuevoLink) {
        this.nuevoLink = nuevoLink;
    }

    /**
     * Gets the link seleccionado.
     *
     * @return the link seleccionado
     */
    public LinkValiosoDTO getLinkSeleccionado() {
        return linkSeleccionado;
    }

    /**
     * Sets the link seleccionado.
     *
     * @param linkSeleccionado the new link seleccionado
     */
    public void setLinkSeleccionado(LinkValiosoDTO linkSeleccionado) {
        this.linkSeleccionado = linkSeleccionado;
    }
}
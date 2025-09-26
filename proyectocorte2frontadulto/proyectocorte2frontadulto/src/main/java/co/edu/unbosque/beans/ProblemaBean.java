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

@Named("problemaBean")
@ViewScoped
public class ProblemaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ProblemaDTO> problemas;
    private ProblemaDTO problemaSeleccionado;
    private ProblemaService problemaService;

    @PostConstruct
    public void init() {
        problemas = new ArrayList<>();
        problemaSeleccionado = new ProblemaDTO();
        problemaService = new ProblemaService();
        cargarProblemas();
    }

    public void cargarProblemas() {
        problemas = problemaService.doGetAll();
    }

    // Crear problema (solo si id es null)
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

    // Seleccionar para editar
    public void editarProblema(ProblemaDTO problema) {
        this.problemaSeleccionado = new ProblemaDTO(
            problema.getId(),
            problema.getTitulo(),
            problema.getDificultad(),
            problema.getTema(),
            problema.getJuez(),
            problema.getUrl()
        );
    }

    // Guardar edición (solo si id no es null)
    public void guardarEdicion() {
        try {
            if (problemaSeleccionado.getId() != null) {
                String respuesta = problemaService.doPut(problemaSeleccionado.getId(), problemaSeleccionado);
                if (respuesta.startsWith("202")) {
                    FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Problema actualizado correctamente"));
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

    // Eliminar problema
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

    // Cancelar edición o creación
    public void cancelarEdicion() {
        problemaSeleccionado = new ProblemaDTO();
    }

    // Getters y setters

    public List<ProblemaDTO> getProblemas() {
        return problemas;
    }

    public void setProblemas(List<ProblemaDTO> problemas) {
        this.problemas = problemas;
    }

    public ProblemaDTO getProblemaSeleccionado() {
        return problemaSeleccionado;
    }

    public void setProblemaSeleccionado(ProblemaDTO problemaSeleccionado) {
        this.problemaSeleccionado = problemaSeleccionado;
    }
}
/**
 * Clase AdminBean
 * Proyecto: proyectocorte2frontninos
 * Paquete: co.edu.unbosque.beans
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.beans;

import jakarta.inject.Named;
import jakarta.servlet.http.Part;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.DocenteDTO;
import co.edu.unbosque.model.EstudianteDTO;
import co.edu.unbosque.service.AdministradorService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminBean.
 */
@Named("adminBean")
@ViewScoped
public class AdminBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The usuarios. */
    private List<Object> usuarios;
    
    /** The admin service. */
    private AdministradorService adminService;
    
    /** The filtro rol. */
    private String filtroRol = "Estudiante"; 
    
    /** The foto perfil part. */
    private Part fotoPerfilPart;

    /**
     * Inicializa el.
     */
    @PostConstruct
    public void init() {
        adminService = new AdministradorService();
        usuarios = new ArrayList<>();
        cargarUsuariosPorRol();
    }

    /**
     * Cargar usuarios por rol.
     */
    public void cargarUsuariosPorRol() {
        usuarios.clear();
        if ("Docente".equals(filtroRol)) {
            usuarios.addAll(adminService.obtenerTodosDocentes());
        } else if ("Estudiante".equals(filtroRol)) {
            usuarios.addAll(adminService.obtenerTodosEstudiantes());
        }
    }

    /**
     * Actualizar usuario.
     *
     * @param usuario the usuario
     */
    public void actualizarUsuario(Object usuario) {
        boolean exito = false;
        try {
            if (usuario instanceof DocenteDTO) {
                exito = adminService.actualizarDocenteConImagen((DocenteDTO) usuario, fotoPerfilPart);
            } else if (usuario instanceof EstudianteDTO) {
                exito = adminService.actualizarEstudianteConImagen((EstudianteDTO) usuario, fotoPerfilPart);
            }
            if (exito) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario actualizado correctamente"));
                cargarUsuariosPorRol();
                fotoPerfilPart = null; 
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el usuario"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el usuario: " + e.getMessage()));
        }
    }
    
    /**
     * Eliminar usuario.
     *
     * @param usuario the usuario
     */
    public void eliminarUsuario(Object usuario) {
        boolean exito = false;
        if (usuario instanceof DocenteDTO) {
            exito = adminService.eliminarDocente(((DocenteDTO) usuario).getId());
        } else if (usuario instanceof EstudianteDTO) {
            exito = adminService.eliminarEstudiante(((EstudianteDTO) usuario).getId());
        }
        if (exito) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario eliminado correctamente"));
            cargarUsuariosPorRol();
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el usuario"));
        }
    }

    
    /**
     * Gets the usuarios.
     *
     * @return the usuarios
     */
    public List<Object> getUsuarios() {
        return usuarios;
    }

    /**
     * Sets the usuarios.
     *
     * @param usuarios the new usuarios
     */
    public void setUsuarios(List<Object> usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * Gets the filtro rol.
     *
     * @return the filtro rol
     */
    public String getFiltroRol() {
        return filtroRol;
    }

    /**
     * Sets the filtro rol.
     *
     * @param filtroRol the new filtro rol
     */
    public void setFiltroRol(String filtroRol) {
        this.filtroRol = filtroRol;
    }
    
    /**
     * Gets the foto perfil part.
     *
     * @return the foto perfil part
     */
    public Part getFotoPerfilPart() {
        return fotoPerfilPart;
    }

    /**
     * Sets the foto perfil part.
     *
     * @param fotoPerfilPart the new foto perfil part
     */
    public void setFotoPerfilPart(Part fotoPerfilPart) {
        this.fotoPerfilPart = fotoPerfilPart;
    }
}
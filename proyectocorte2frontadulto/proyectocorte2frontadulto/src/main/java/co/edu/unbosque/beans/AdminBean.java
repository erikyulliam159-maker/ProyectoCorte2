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

@Named("adminBean")
@ViewScoped
public class AdminBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Object> usuarios;
    private AdministradorService adminService;
    private String filtroRol = "Estudiante"; // Valor por defecto
    private Part fotoPerfilPart;

    @PostConstruct
    public void init() {
        adminService = new AdministradorService();
        usuarios = new ArrayList<>();
        cargarUsuariosPorRol();
    }

    public void cargarUsuariosPorRol() {
        usuarios.clear();
        if ("Docente".equals(filtroRol)) {
            usuarios.addAll(adminService.obtenerTodosDocentes());
        } else if ("Estudiante".equals(filtroRol)) {
            usuarios.addAll(adminService.obtenerTodosEstudiantes());
        }
    }

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
                fotoPerfilPart = null; // Limpia el archivo después de actualizar
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el usuario"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el usuario: " + e.getMessage()));
        }
    }
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

    // Getters y setters
    public List<Object> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Object> usuarios) {
        this.usuarios = usuarios;
    }

    public String getFiltroRol() {
        return filtroRol;
    }

    public void setFiltroRol(String filtroRol) {
        this.filtroRol = filtroRol;
    }
    public Part getFotoPerfilPart() {
        return fotoPerfilPart;
    }

    public void setFotoPerfilPart(Part fotoPerfilPart) {
        this.fotoPerfilPart = fotoPerfilPart;
    }
}
/**
 * Clase LoginBean
 * Proyecto: proyectocorte2frontninos
 * Paquete: co.edu.unbosque.beans
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.beans;

import java.io.Serializable;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import co.edu.unbosque.model.UsuarioDTO;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginBean.
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The username. */
    private String username;
    
    /** The password. */
    private String password;
    
    /** The message. */
    private String message;
    
    /** The message type. */
    private String messageType;
    
    /** The usuario logueado. */
    private UsuarioDTO usuarioLogueado;
    
    /** The tipo usuario. */
    private String tipoUsuario = "estudiante";
    
    /** The api url. */
    private final String API_URL = "http://localhost:8081/login";

    /**
     * Login.
     *
     * @return the string
     */
    public String login() {
        try {
            if (username == null || username.trim().isEmpty()) {
                addErrorMessage("El nombre de usuario es obligatorio");
                return null;
            }

            if (password == null || password.trim().isEmpty()) {
                addErrorMessage("La contraseña es obligatoria");
                return null;
            }
            String endpoint = getEndpointByUserType();
            
            HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

            String formData = "username=" + URLEncoder.encode(username, StandardCharsets.UTF_8) +
                            "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8);

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + endpoint))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(formData))
                .timeout(Duration.ofSeconds(10))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            return handleLoginResponse(response);
            
        } catch (Exception e) {
            addErrorMessage("Error de conexión: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets the endpoint by user type.
     *
     * @return the endpoint by user type
     */
    private String getEndpointByUserType() {
        switch (tipoUsuario) {
            case "docente":
                return "/checkloginDocente";
            case "administrador":
                return "/checkloginAdministrador";
            case "estudiante":
            default:
                return "/checkloginEstudiante";
        }
    }

    /**
     * Handle login response.
     *
     * @param response the response
     * @return the string
     */
    private String handleLoginResponse(HttpResponse<String> response) {
        int statusCode = response.statusCode();
        
        switch (statusCode) {
            case 202:
                return handleSuccessfulLogin();
            case 404:
                addErrorMessage("El usuario no existe");
                return null;
            case 401:
                addErrorMessage("Contraseña incorrecta");
                return null;
            default:
                addErrorMessage("Error en el servidor. Código: " + statusCode);
                return null;
        }
    }

    /**
     * Handle successful login.
     *
     * @return the string
     */
    private String handleSuccessfulLogin() {
        addSuccessMessage("Inicio de sesión exitoso");
        
        usuarioLogueado = new UsuarioDTO();
        usuarioLogueado.setUsername(username);
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("usuarioLogueado", usuarioLogueado);
        context.getExternalContext().getSessionMap().put("tipoUsuario", tipoUsuario);
   
        switch (tipoUsuario) {
            case "docente":
                return "Docente.xhtml?faces-redirect=true"; // CAMBIADO
            case "administrador":
                return "Admin.xhtml?faces-redirect=true";
            case "estudiante":
            default:
                return "pantallaprincipal.xhtml?faces-redirect=true";
        }
    }

    /**
     * Agrega el error message.
     *
     * @param errorMessage the error message
     */
    private void addErrorMessage(String errorMessage) {
        this.message = errorMessage;
        this.messageType = "error";
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", errorMessage));
    }

    /**
     * Agrega el success message.
     *
     * @param successMessage the success message
     */
    private void addSuccessMessage(String successMessage) {
        this.message = successMessage;
        this.messageType = "success";
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", successMessage));
    }

    /**
     * Logout.
     *
     * @return the string
     */
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        usuarioLogueado = null;
        username = null;
        password = null;
        tipoUsuario = "estudiante";
        return "/index.xhtml?faces-redirect=true";
    }

    /**
     * Checks if is logged in.
     *
     * @return true, if is logged in
     */
    public boolean isLoggedIn() {
        return usuarioLogueado != null;
    }


    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
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

    /**
     * Gets the usuario logueado.
     *
     * @return the usuario logueado
     */
    public UsuarioDTO getUsuarioLogueado() {
        return usuarioLogueado;
    }

    /**
     * Sets the usuario logueado.
     *
     * @param usuarioLogueado the new usuario logueado
     */
    public void setUsuarioLogueado(UsuarioDTO usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    /**
     * Gets the tipo usuario.
     *
     * @return the tipo usuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Sets the tipo usuario.
     *
     * @param tipoUsuario the new tipo usuario
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
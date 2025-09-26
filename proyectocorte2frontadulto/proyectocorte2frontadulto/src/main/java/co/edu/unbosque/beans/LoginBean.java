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

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String message;
    private String messageType;
    private UsuarioDTO usuarioLogueado;
    private String tipoUsuario = "estudiante";
    private final String API_URL = "http://localhost:8081/login";

    public String login() {
        try {
            // Validaciones básicas
            if (username == null || username.trim().isEmpty()) {
                addErrorMessage("El nombre de usuario es obligatorio");
                return null;
            }

            if (password == null || password.trim().isEmpty()) {
                addErrorMessage("La contraseña es obligatoria");
                return null;
            }

            // Determinar el endpoint según el tipo de usuario
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

    private String handleSuccessfulLogin() {
        addSuccessMessage("Inicio de sesión exitoso");
        
        usuarioLogueado = new UsuarioDTO();
        usuarioLogueado.setUsername(username);
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("usuarioLogueado", usuarioLogueado);
        context.getExternalContext().getSessionMap().put("tipoUsuario", tipoUsuario);
        
        // CAMBIADO: Redirigir a Docente.xhtml para usuarios docentes
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

    private void addErrorMessage(String errorMessage) {
        this.message = errorMessage;
        this.messageType = "error";
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", errorMessage));
    }

    private void addSuccessMessage(String successMessage) {
        this.message = successMessage;
        this.messageType = "success";
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", successMessage));
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        usuarioLogueado = null;
        username = null;
        password = null;
        tipoUsuario = "estudiante";
        return "/index.xhtml?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return usuarioLogueado != null;
    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public UsuarioDTO getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(UsuarioDTO usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
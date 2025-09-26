/**
 * Clase RegisterBean
 * Proyecto: proyectocorte2frontadulto
 * Paquete: co.edu.unbosque.beans
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.beans;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.primefaces.model.file.UploadedFile;

import co.edu.unbosque.model.DocenteDTO;
import co.edu.unbosque.model.EstudianteDTO;
import co.edu.unbosque.service.DocenteService;
import co.edu.unbosque.service.EstudianteService;
import com.google.gson.Gson;

// TODO: Auto-generated Javadoc
/**
 * The Class RegisterBean.
 */
@Named
@ViewScoped
public class RegisterBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The estudiante. */
	private EstudianteDTO estudiante;
	
	/** The docente. */
	private DocenteDTO docente;
	
	/** The tipo usuario. */
	private String tipoUsuario = "estudiante";
	
	/** The message. */
	private String message;
	
	/** The message type. */
	private String messageType;
	
	/** The foto perfil file. */
	private UploadedFile fotoPerfilFile;

	/**
	 * Instantiates a new register bean.
	 */
	public RegisterBean() {
		estudiante = new EstudianteDTO();
		docente = new DocenteDTO();
	}

	/**
	 * Register.
	 */
	public void register() {
	    try {
	        Path tempFile = null;
	        if (fotoPerfilFile != null && fotoPerfilFile.getContent() != null) {

	            tempFile = Files.createTempFile("upload_", "_" + fotoPerfilFile.getFileName());
	            Files.write(tempFile, fotoPerfilFile.getContent());
	        }

	        String respuesta = "";
	        if ("estudiante".equals(tipoUsuario)) {
	            String jsonDTO = new Gson().toJson(estudiante);
	            respuesta = EstudianteService.doPostMultipart(
	            	    "http://localhost:8081/registro/estudiante/crear",
	            	    estudiante,
	            	    tempFile
	            	);
	        } else {
	            String jsonDTO = new Gson().toJson(docente);
	            respuesta = DocenteService.doPostMultipart(
	            	    "http://localhost:8081/registro/docente/crear",
	            	    docente,
	            	    tempFile
	            	);
	        }

	  
	        if (tempFile != null) Files.deleteIfExists(tempFile);

	        if (respuesta.contains("exitosamente") || respuesta.contains("201")) {
	            message = "Usuario registrado exitosamente!";
	            messageType = "success";
	            estudiante = new EstudianteDTO();
	            docente = new DocenteDTO();
	            fotoPerfilFile = null;
	        } else {
	            message = "Error al registrar usuario: " + respuesta;
	            messageType = "error";
	        }
	    } catch (Exception e) {
	        message = "Error inesperado: " + e.getMessage();
	        messageType = "error";
	    }
	}

	
	/**
	 * Gets the foto perfil file.
	 *
	 * @return the foto perfil file
	 */
	public UploadedFile getFotoPerfilFile() {
		return fotoPerfilFile;
	}

	/**
	 * Sets the foto perfil file.
	 *
	 * @param fotoPerfilFile the new foto perfil file
	 */
	public void setFotoPerfilFile(UploadedFile fotoPerfilFile) {
		this.fotoPerfilFile = fotoPerfilFile;
	}

	/**
	 * Gets the estudiante.
	 *
	 * @return the estudiante
	 */
	public EstudianteDTO getEstudiante() {
		return estudiante;
	}

	/**
	 * Sets the estudiante.
	 *
	 * @param estudiante the new estudiante
	 */
	public void setEstudiante(EstudianteDTO estudiante) {
		this.estudiante = estudiante;
	}

	/**
	 * Gets the docente.
	 *
	 * @return the docente
	 */
	public DocenteDTO getDocente() {
		return docente;
	}

	/**
	 * Sets the docente.
	 *
	 * @param docente the new docente
	 */
	public void setDocente(DocenteDTO docente) {
		this.docente = docente;
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
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
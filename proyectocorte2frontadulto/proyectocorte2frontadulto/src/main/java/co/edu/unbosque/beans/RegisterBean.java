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

@Named
@ViewScoped
public class RegisterBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private EstudianteDTO estudiante;
	private DocenteDTO docente;
	private String tipoUsuario = "estudiante";
	private String message;
	private String messageType;
	private UploadedFile fotoPerfilFile;

	public RegisterBean() {
		estudiante = new EstudianteDTO();
		docente = new DocenteDTO();
	}

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


	public UploadedFile getFotoPerfilFile() {
		return fotoPerfilFile;
	}

	public void setFotoPerfilFile(UploadedFile fotoPerfilFile) {
		this.fotoPerfilFile = fotoPerfilFile;
	}

	public EstudianteDTO getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(EstudianteDTO estudiante) {
		this.estudiante = estudiante;
	}

	public DocenteDTO getDocente() {
		return docente;
	}

	public void setDocente(DocenteDTO docente) {
		this.docente = docente;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
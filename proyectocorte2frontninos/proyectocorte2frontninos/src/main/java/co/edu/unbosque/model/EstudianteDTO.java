/**
 * Clase EstudianteDTO
 * Proyecto: proyectocorte2frontninos
 * Paquete: co.edu.unbosque.model
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.model;




// TODO: Auto-generated Javadoc
/**
 * The Class EstudianteDTO.
 */
public class EstudianteDTO extends UsuarioDTO{

	
	 /**
 	 * Instantiates a new estudiante DTO.
 	 */
 	public EstudianteDTO() {
	        super();
	    }

	    /**
    	 * Instantiates a new estudiante DTO.
    	 *
    	 * @param username the username
    	 * @param password the password
    	 * @param nombreCompleto the nombre completo
    	 * @param email the email
    	 * @param fotoPerfil the foto perfil
    	 */
    	public EstudianteDTO(String username, String password, String nombreCompleto, String email, String fotoPerfil) {
	        super(username, password, nombreCompleto, email, fotoPerfil);
	    }
	    
	   
	
}

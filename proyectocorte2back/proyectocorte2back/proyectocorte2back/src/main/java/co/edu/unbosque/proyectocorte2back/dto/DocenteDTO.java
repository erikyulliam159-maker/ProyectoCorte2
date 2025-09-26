/**
 * Clase DocenteDTO
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.dto
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.dto;


// TODO: Auto-generated Javadoc
/**
 * The Class DocenteDTO.
 */
public class DocenteDTO extends UsuarioDTO {

	/**
	 * Instantiates a new docente DTO.
	 */
	public DocenteDTO() {
		super();
	}

	/**
	 * Instantiates a new docente DTO.
	 *
	 * @param username the username
	 * @param password the password
	 * @param nombreCompleto the nombre completo
	 * @param email the email
	 * @param fotoPerfil the foto perfil
	 */
	public DocenteDTO(String username, String password, String nombreCompleto, String email, String fotoPerfil) {
		super(username, password, nombreCompleto, email, fotoPerfil);

	}

}

/**
 * Clase DocenteDTO
 * Proyecto: proyectocorte2frontninos
 * Paquete: co.edu.unbosque.model
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.model;

import java.util.Objects;

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

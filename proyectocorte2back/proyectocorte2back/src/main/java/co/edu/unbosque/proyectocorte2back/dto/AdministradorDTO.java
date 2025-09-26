/**
 * Clase AdministradorDTO
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.dto
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.dto;

import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class AdministradorDTO.
 */
public class AdministradorDTO extends UsuarioDTO {
	
	/** The id. */
	private Long id;
	
	/**
	 * Instantiates a new administrador DTO.
	 */
	public AdministradorDTO() {
		super();
	}

	/**
	 * Instantiates a new administrador DTO.
	 *
	 * @param username the username
	 * @param password the password
	 * @param nombreCompleto the nombre completo
	 * @param email the email
	 * @param fotoPerfil the foto perfil
	 */
	public AdministradorDTO(String username, String password, String nombreCompleto, String email, String fotoPerfil) {
		super(username, password, nombreCompleto, email, fotoPerfil);

	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id);
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdministradorDTO other = (AdministradorDTO) obj;
		return Objects.equals(id, other.id);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
}

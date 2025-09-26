/**
 * Clase UsuarioDTO
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.dto
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.dto;

import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class UsuarioDTO.
 */
public class UsuarioDTO {
    
    /** The id. */
    private Long id;
    
    /** The username. */
    private String username;
    
    /** The password. */
    private String password;
    
    /** The nombre completo. */
    private String nombreCompleto;
    
    /** The email. */
    private String email;
    
    /** The foto perfil. */
    private String fotoPerfil; 
    
    
    /**
     * Instantiates a new usuario DTO.
     */
    public UsuarioDTO() {
		// TODO Auto-generated constructor stub
	}
    
    /**
     * Instantiates a new usuario DTO.
     *
     * @param username the username
     * @param password the password
     * @param nombreCompleto the nombre completo
     * @param email the email
     * @param fotoPerfil the foto perfil
     */
    public UsuarioDTO(String username, String password, String nombreCompleto, String email, String fotoPerfil) {
        this.username = username;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.fotoPerfil = fotoPerfil;
    }

    
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(email, fotoPerfil, id, nombreCompleto, password, username);
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioDTO other = (UsuarioDTO) obj;
		return Objects.equals(email, other.email) && Objects.equals(fotoPerfil, other.fotoPerfil)
				&& Objects.equals(id, other.id) && Objects.equals(nombreCompleto, other.nombreCompleto)
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
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
	 * Gets the nombre completo.
	 *
	 * @return the nombre completo
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * Sets the nombre completo.
	 *
	 * @param nombreCompleto the new nombre completo
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the foto perfil.
	 *
	 * @return the foto perfil
	 */
	public String getFotoPerfil() {
		return fotoPerfil;
	}

	/**
	 * Sets the foto perfil.
	 *
	 * @param fotoPerfil the new foto perfil
	 */
	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
    
    
}

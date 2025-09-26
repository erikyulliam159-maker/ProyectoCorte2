/**
 * Clase Administrador
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.entity
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Administrador.
 */
@Entity
@Table(name = "administradores")
public class Administrador extends Usuario {
	
	/** The id. */
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

	/**
	 * Instantiates a new administrador.
	 */
	public Administrador() {
		super();
	}

	/**
	 * Instantiates a new administrador.
	 *
	 * @param username the username
	 * @param password the password
	 * @param nombreCompleto the nombre completo
	 * @param email the email
	 * @param fotoPerfil the foto perfil
	 */
	public Administrador(String username, String password, String nombreCompleto, String email, String fotoPerfil) {
		super(username, password, nombreCompleto, email, fotoPerfil);

	}
	
	

	/**
	 * Instantiates a new administrador.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public Administrador(String username, String password) {
		super(username, password);
		
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
		Administrador other = (Administrador) obj;
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

/**
 * Clase Docente
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
 * The Class Docente.
 */
@Entity
@Table(name = "docentes")
public class Docente extends Usuario {


    /**
     * Instantiates a new docente.
     */
    public Docente() {
        super();
    }

    /**
     * Instantiates a new docente.
     *
     * @param username the username
     * @param password the password
     * @param nombreCompleto the nombre completo
     * @param email the email
     * @param fotoPerfil the foto perfil
     * @param especialidad the especialidad
     */
    public Docente(String username, String password, String nombreCompleto, String email, String fotoPerfil, String especialidad) {
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
		Docente other = (Docente) obj;
		return Objects.equals(id, other.id);
	}

	


}


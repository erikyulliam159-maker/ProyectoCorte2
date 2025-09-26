/**
 * Clase Usuario
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.entity
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Usuario.
 */
@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /** The username. */
    @Column(unique = true, nullable = false)
    protected String username;

    /** The password. */
    @Column(nullable = false)
    protected String password;

    /** The nombre completo. */
    @Column(nullable = false)
    protected String nombreCompleto;

    /** The email. */
    protected String email;

    /** The foto perfil. */
    protected String fotoPerfil; 

    /**
     * Instantiates a new usuario.
     */
    public Usuario() {
    }

    /**
     * Instantiates a new usuario.
     *
     * @param username the username
     * @param password the password
     * @param nombreCompleto the nombre completo
     * @param email the email
     * @param fotoPerfil the foto perfil
     */
    public Usuario(String username, String password, String nombreCompleto, String email, String fotoPerfil) {
        this.username = username;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.fotoPerfil = fotoPerfil;
    }
    
    

    /**
     * Instantiates a new usuario.
     *
     * @param username the username
     * @param password the password
     */
    public Usuario(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	// Getters y Setters
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

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    /**
     * Equals.
     *
     * @param obj the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(id, other.id) && Objects.equals(username, other.username);
    }
}

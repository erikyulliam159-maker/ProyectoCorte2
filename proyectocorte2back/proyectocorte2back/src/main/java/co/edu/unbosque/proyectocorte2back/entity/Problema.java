/**
 * Clase Problema
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.entity
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.entity;

import jakarta.persistence.*;
import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class Problema.
 */
@Entity
@Table(name = "problemas")
public class Problema {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The titulo. */
	@Column(nullable = false)
	private String titulo;

	/** The dificultad. */
	@Column(nullable = false)
	private int dificultad; 

	/** The tema. */
	@Column(nullable = false)
	private String tema; 

	/** The juez. */
	@Column(nullable = false)
	private String juez;

	/** The url. */
	@Column(nullable = false)
	private String url;

	/**
	 * Instantiates a new problema.
	 */
	public Problema() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new problema.
	 *
	 * @param titulo the titulo
	 * @param dificultad the dificultad
	 * @param tema the tema
	 * @param juez the juez
	 * @param url the url
	 */
	public Problema(String titulo, int dificultad, String tema, String juez, String url) {
		super();
		this.titulo = titulo;
		this.dificultad = dificultad;
		this.tema = tema;
		this.juez = juez;
		this.url = url;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(dificultad, id, juez, tema, titulo, url);
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
		Problema other = (Problema) obj;
		return dificultad == other.dificultad && Objects.equals(id, other.id) && Objects.equals(juez, other.juez)
				&& Objects.equals(tema, other.tema) && Objects.equals(titulo, other.titulo)
				&& Objects.equals(url, other.url);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	// Getters y setters
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
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo the new titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Gets the dificultad.
	 *
	 * @return the dificultad
	 */
	public int getDificultad() {
		return dificultad;
	}

	/**
	 * Sets the dificultad.
	 *
	 * @param dificultad the new dificultad
	 */
	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	/**
	 * Gets the tema.
	 *
	 * @return the tema
	 */
	public String getTema() {
		return tema;
	}

	/**
	 * Sets the tema.
	 *
	 * @param tema the new tema
	 */
	public void setTema(String tema) {
		this.tema = tema;
	}

	/**
	 * Gets the juez.
	 *
	 * @return the juez
	 */
	public String getJuez() {
		return juez;
	}

	/**
	 * Sets the juez.
	 *
	 * @param juez the new juez
	 */
	public void setJuez(String juez) {
		this.juez = juez;
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}

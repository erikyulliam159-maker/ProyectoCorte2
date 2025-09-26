/**
 * Clase Temario
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.entity
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class Temario.
 */
@Entity
@Table(name = "temarios")
public class Temario {

	/** The id. */
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

	/** The titulo. */
	private String titulo;
	
	/** The subtemas. */
	@OneToMany(mappedBy = "temario", cascade = CascadeType.ALL, // o {PERSIST, MERGE} si no quieres REMOVE
			orphanRemoval = true ,fetch = FetchType.LAZY)
	private List<Subtema> subtemas;

	/**
	 * Instantiates a new temario.
	 */
	public Temario() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new temario.
	 *
	 * @param titulo the titulo
	 * @param subtemas the subtemas
	 */
	public Temario(String titulo, List<Subtema> subtemas) {
		super();
		this.titulo = titulo;
		this.subtemas = subtemas;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, titulo);
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
		Temario other = (Temario) obj;
		return Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo);
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
	 * Gets the subtemas.
	 *
	 * @return the subtemas
	 */
	public List<Subtema> getSubtemas() {
		return subtemas;
	}

	/**
	 * Sets the subtemas.
	 *
	 * @param subtemas the new subtemas
	 */
	public void setSubtemas(List<Subtema> subtemas) {
		this.subtemas = subtemas;
	}

}

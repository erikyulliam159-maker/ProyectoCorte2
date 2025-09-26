/**
 * Clase DetalleSubtema
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.entity
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.entity;

import jakarta.persistence.*;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;



// TODO: Auto-generated Javadoc
/**
 * The Class DetalleSubtema.
 */
@Entity
@Table(name = "detalles_subtemas")
public class DetalleSubtema {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The descripcion. */
	@Lob
	private String descripcion;

	/** The url imagen. */
	private String urlImagen;

	/** The subtema. */
	@OneToOne(mappedBy = "detalle", cascade = CascadeType.ALL)
	@JsonIgnore 
	private Subtema subtema;


	/**
	 * Instantiates a new detalle subtema.
	 */
	public DetalleSubtema() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new detalle subtema.
	 *
	 * @param descripcion the descripcion
	 * @param urlImagen the url imagen
	 * @param subtema the subtema
	 */
	public DetalleSubtema(String descripcion, String urlImagen, Subtema subtema) {
		this.descripcion = descripcion;
		this.urlImagen = urlImagen;
		this.subtema = subtema;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, id, subtema, urlImagen);
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
		DetalleSubtema other = (DetalleSubtema) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(id, other.id)
				&& Objects.equals(subtema, other.subtema) && Objects.equals(urlImagen, other.urlImagen);
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
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the url imagen.
	 *
	 * @return the url imagen
	 */
	public String getUrlImagen() {
		return urlImagen;
	}

	/**
	 * Sets the url imagen.
	 *
	 * @param urlImagen the new url imagen
	 */
	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	/**
	 * Gets the subtema.
	 *
	 * @return the subtema
	 */
	public Subtema getSubtema() {
		return subtema;
	}

	/**
	 * Sets the subtema.
	 *
	 * @param subtema the new subtema
	 */
	public void setSubtema(Subtema subtema) {
		this.subtema = subtema;
	}

}

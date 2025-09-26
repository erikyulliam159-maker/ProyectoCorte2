/**
 * Clase DetalleSubtemaDTO
 * Proyecto: proyectocorte2frontninos
 * Paquete: co.edu.unbosque.model
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.model;

import java.util.Objects;



// TODO: Auto-generated Javadoc
/**
 * The Class DetalleSubtemaDTO.
 */
public class DetalleSubtemaDTO {

	/** The id. */
	private Long id;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The url imagen. */
	private String urlImagen;

	/** The subtema nombre. */
	private String subtemaNombre;

	/**
	 * Instantiates a new detalle subtema DTO.
	 */
	public DetalleSubtemaDTO() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Instantiates a new detalle subtema DTO.
	 *
	 * @param descripcion the descripcion
	 * @param urlImagen the url imagen
	 * @param subtemaNombre the subtema nombre
	 */
	public DetalleSubtemaDTO(String descripcion, String urlImagen, String subtemaNombre) {
		super();
		this.descripcion = descripcion;
		this.urlImagen = urlImagen;
		this.subtemaNombre = subtemaNombre;
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, id, subtemaNombre, urlImagen);
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
		DetalleSubtemaDTO other = (DetalleSubtemaDTO) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(id, other.id)
				&& Objects.equals(subtemaNombre, other.subtemaNombre) && Objects.equals(urlImagen, other.urlImagen);
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
	 * Gets the subtema nombre.
	 *
	 * @return the subtema nombre
	 */
	public String getSubtemaNombre() {
		return subtemaNombre;
	}
	
	/**
	 * Sets the subtema nombre.
	 *
	 * @param subtemaNombre the new subtema nombre
	 */
	public void setSubtemaNombre(String subtemaNombre) {
		this.subtemaNombre = subtemaNombre;
	}

	

}

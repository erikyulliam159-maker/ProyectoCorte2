/**
 * Clase DetalleSubtemaDTO
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.dto
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.dto;

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
	 */
	public DetalleSubtemaDTO(String descripcion, String urlImagen){
		super();
		this.descripcion = descripcion;
		this.urlImagen = urlImagen;
	
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
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, id, urlImagen);
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
				&& Objects.equals(urlImagen, other.urlImagen);
	}
	

	

}

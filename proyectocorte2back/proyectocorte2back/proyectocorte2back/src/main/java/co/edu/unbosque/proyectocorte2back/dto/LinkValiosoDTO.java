/**
 * Clase LinkValiosoDTO
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.dto
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.dto;

import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class LinkValiosoDTO.
 */
public class LinkValiosoDTO {
	
	/** The id. */
	private Long id;

	/** The nombre. */
	private String nombre;

	/** The tipo. */
	private String tipo;

	/** The url. */
	private String url;

	/** The imagen. */
	private String imagen;

	/**
	 * Instantiates a new link valioso DTO.
	 */
	public LinkValiosoDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new link valioso DTO.
	 *
	 * @param nombre the nombre
	 * @param tipo the tipo
	 * @param url the url
	 * @param imagen the imagen
	 */
	public LinkValiosoDTO(String nombre, String tipo, String url, String imagen) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.url = url;
		this.imagen = imagen;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, imagen, nombre, tipo, url);
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
		LinkValiosoDTO other = (LinkValiosoDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(imagen, other.imagen)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(tipo, other.tipo)
				&& Objects.equals(url, other.url);
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
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	/**
	 * Gets the imagen.
	 *
	 * @return the imagen
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * Sets the imagen.
	 *
	 * @param imagen the new imagen
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	

}

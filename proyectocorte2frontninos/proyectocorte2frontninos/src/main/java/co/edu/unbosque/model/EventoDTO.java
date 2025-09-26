/**
 * Clase EventoDTO
 * Proyecto: proyectocorte2frontninos
 * Paquete: co.edu.unbosque.model
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.model;

import java.time.LocalDate;

import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class EventoDTO.
 */
public class EventoDTO {

	/** The id. */
	private Long id;
	
	/** The titulo. */
	private String titulo;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The fecha. */
	private LocalDate fecha;
	
	/** The url. */
	private String url;

	/**
	 * Instantiates a new evento DTO.
	 */
	public EventoDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new evento DTO.
	 *
	 * @param id the id
	 * @param titulo the titulo
	 * @param descripcion the descripcion
	 * @param fecha the fecha
	 * @param url the url
	 */
	public EventoDTO(Long id, String titulo, String descripcion, LocalDate fecha, String url) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.url = url;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, fecha, id, titulo, url);
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
		EventoDTO other = (EventoDTO) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo)
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
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha the new fecha
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
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

/**
 * Clase TemarioDTO
 * Proyecto: proyectocorte2frontadulto
 * Paquete: co.edu.unbosque.model
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



// TODO: Auto-generated Javadoc
/**
 * The Class TemarioDTO.
 */
public class TemarioDTO {
	
	/** The id. */
	private Long id;
	
	/** The titulo. */
	private String titulo;
	
	/** The subtemas. */
	private List<SubtemaDTO> subtemas;
	
	/**
	 * Instantiates a new temario DTO.
	 */
	public TemarioDTO() {
		// TODO Auto-generated constructor stub
		 subtemas = new ArrayList<>();
	}

	/**
	 * Instantiates a new temario DTO.
	 *
	 * @param titulo the titulo
	 * @param subtemas the subtemas
	 */
	public TemarioDTO(String titulo, List<SubtemaDTO> subtemas) {
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
		TemarioDTO other = (TemarioDTO) obj;
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
	public List<SubtemaDTO> getSubtemas() {
		return subtemas;
	}

	/**
	 * Sets the subtemas.
	 *
	 * @param subtemas the new subtemas
	 */
	public void setSubtemas(List<SubtemaDTO> subtemas) {
		this.subtemas = subtemas;
	}

	
}

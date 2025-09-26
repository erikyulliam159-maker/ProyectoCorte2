/**
 * Clase SubtemaDTO
 * Proyecto: proyectocorte2frontninos
 * Paquete: co.edu.unbosque.model
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.model;

import java.util.Objects;



// TODO: Auto-generated Javadoc
/**
 * The Class SubtemaDTO.
 */
public class SubtemaDTO {
	
	/** The id. */
	private Long id;
	
	/** The nombre. */
	private String nombre;
	
	/** The tipo. */
	private String tipo;

	/** The temario titulo. */
	private String temarioTitulo; 

	/** The detalle. */
	private DetalleSubtemaDTO detalle;

	/**
	 * Instantiates a new subtema DTO.
	 */
	public SubtemaDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new subtema DTO.
	 *
	 * @param nombre the nombre
	 * @param tipo the tipo
	 * @param temarioTitulo the temario titulo
	 * @param detalle the detalle
	 */
	public SubtemaDTO(String nombre, String tipo, String temarioTitulo, DetalleSubtemaDTO detalle) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.temarioTitulo = temarioTitulo;
		this.detalle = detalle;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(detalle, id, nombre, temarioTitulo, tipo);
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
		SubtemaDTO other = (SubtemaDTO) obj;
		return Objects.equals(detalle, other.detalle) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(temarioTitulo, other.temarioTitulo)
				&& Objects.equals(tipo, other.tipo);
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
	 * Gets the temario titulo.
	 *
	 * @return the temario titulo
	 */
	public String getTemarioTitulo() {
		return temarioTitulo;
	}

	/**
	 * Sets the temario titulo.
	 *
	 * @param temarioTitulo the new temario titulo
	 */
	public void setTemarioTitulo(String temarioTitulo) {
		this.temarioTitulo = temarioTitulo;
	}

	/**
	 * Gets the detalle.
	 *
	 * @return the detalle
	 */
	public DetalleSubtemaDTO getDetalle() {
		return detalle;
	}

	/**
	 * Sets the detalle.
	 *
	 * @param detalle the new detalle
	 */
	public void setDetalle(DetalleSubtemaDTO detalle) {
		this.detalle = detalle;
	}
	
	

}

/**
 * Clase Subtema
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
 * The Class Subtema.
 */
@Entity
@Table(name = "subtemas")

public class Subtema {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   

    /** The nombre. */
    @Column(nullable = false)
    private String nombre; 

    /** The tipo. */
    @Column(nullable = false)
    private String tipo;   

    /** The temario. */
    @ManyToOne
    @JoinColumn(name = "temario_id")
    @JsonIgnore 
    private Temario temario;

    /** The detalle. */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "detalle_id")
    private DetalleSubtema detalle;


    /**
     * Instantiates a new subtema.
     */
    public Subtema() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new subtema.
	 *
	 * @param nombre the nombre
	 * @param tipo the tipo
	 * @param temario the temario
	 * @param detalle the detalle
	 */
	public Subtema(String nombre, String tipo, Temario temario, DetalleSubtema detalle) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.temario = temario;
		this.detalle = detalle;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, temario, tipo);
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
		Subtema other = (Subtema) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(temario, other.temario) && Objects.equals(tipo, other.tipo);
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
	 * Gets the temario.
	 *
	 * @return the temario
	 */
	public Temario getTemario() {
		return temario;
	}

	/**
	 * Sets the temario.
	 *
	 * @param temario the new temario
	 */
	public void setTemario(Temario temario) {
		this.temario = temario;
	}

	/**
	 * Gets the detalle.
	 *
	 * @return the detalle
	 */
	public DetalleSubtema getDetalle() {
		return detalle;
	}

	/**
	 * Sets the detalle.
	 *
	 * @param detalle the new detalle
	 */
	public void setDetalle(DetalleSubtema detalle) {
		this.detalle = detalle;
	}
	
	
    
}

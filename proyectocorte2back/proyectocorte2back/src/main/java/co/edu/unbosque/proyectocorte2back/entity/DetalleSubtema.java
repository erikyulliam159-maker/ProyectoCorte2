package co.edu.unbosque.proyectocorte2back.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "detalles_subtemas")
public class DetalleSubtema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	private String descripcion; 

	private String urlImagen; 

	@OneToOne
	@JoinColumn(name = "subtema_id", unique = true)
	private Subtema subtema;

	public DetalleSubtema() {
		// TODO Auto-generated constructor stub
	}

	public DetalleSubtema(String descripcion, String urlImagen, Subtema subtema) {
		this.descripcion = descripcion;
		this.urlImagen = urlImagen;
		this.subtema = subtema;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, id, subtema, urlImagen);
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public Subtema getSubtema() {
		return subtema;
	}

	public void setSubtema(Subtema subtema) {
		this.subtema = subtema;
	}

	
}

package co.edu.unbosque.proyectocorte2back.dto;

import java.util.Objects;

import co.edu.unbosque.proyectocorte2back.entity.Subtema;

public class DetalleSubtemaDTO {

	private Long id;
	private String descripcion; 
	private String urlImagen; 
	private Subtema subtema;
	
	public DetalleSubtemaDTO() {
		// TODO Auto-generated constructor stub
	}

	public DetalleSubtemaDTO(String descripcion, String urlImagen, Subtema subtema) {
		super();
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
		DetalleSubtemaDTO other = (DetalleSubtemaDTO) obj;
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

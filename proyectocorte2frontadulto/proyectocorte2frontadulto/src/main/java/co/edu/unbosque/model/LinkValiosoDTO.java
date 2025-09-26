package co.edu.unbosque.model;

import java.util.Objects;

public class LinkValiosoDTO {
	
	private Long id;

	private String nombre;

	private String tipo;

	private String url;

	private String imagen;

	public LinkValiosoDTO() {
		// TODO Auto-generated constructor stub
	}

	public LinkValiosoDTO(String nombre, String tipo, String url, String imagen) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.url = url;
		this.imagen = imagen;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, imagen, nombre, tipo, url);
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	

}

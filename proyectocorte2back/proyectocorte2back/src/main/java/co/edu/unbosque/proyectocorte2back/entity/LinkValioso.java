package co.edu.unbosque.proyectocorte2back.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "link_valioso")
public class LinkValioso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	private String tipo;

	private String url;

	private String imagen;

	public LinkValioso() {
		// TODO Auto-generated constructor stub
	}


	public LinkValioso(String nombre, String tipo, String url, String imagen) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.url = url;
		this.imagen = imagen;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof LinkValioso))
			return false;
		LinkValioso that = (LinkValioso) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	// Getters y Setters
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

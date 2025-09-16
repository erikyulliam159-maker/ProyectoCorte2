package co.edu.unbosque.proyectocorte2back.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "libros")
public class Libro {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false)
	private String autor;

	@Column(nullable = false)
	private int anio; 

	@Column(length = 1000)
	private String descripcion;

	private String imagenPortada;

	@Column(nullable = false)
	private String urlPdf;

	public Libro() {
	}

	public Libro(String titulo, String autor, int anio, String descripcion, String imagenPortada, String urlPdf,
			Docente profesor) {
		this.titulo = titulo;
		this.autor = autor;
		this.anio = anio;
		this.descripcion = descripcion;
		this.imagenPortada = imagenPortada;
		this.urlPdf = urlPdf;

	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Libro))
			return false;
		Libro libro = (Libro) o;
		return Objects.equals(id, libro.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagenPortada() {
		return imagenPortada;
	}

	public void setImagenPortada(String imagenPortada) {
		this.imagenPortada = imagenPortada;
	}

	public String getUrlPdf() {
		return urlPdf;
	}

	public void setUrlPdf(String urlPdf) {
		this.urlPdf = urlPdf;
	}

}

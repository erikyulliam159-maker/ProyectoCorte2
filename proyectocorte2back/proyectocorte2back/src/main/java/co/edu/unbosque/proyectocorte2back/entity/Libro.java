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
	private String pdf;
	
	@Column(nullable = false)
	private String urlpdf;

	public Libro() {
	}



	public Libro(String titulo, String autor, int anio, String descripcion, String imagenPortada, String pdf,
			String urlpdf) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.anio = anio;
		this.descripcion = descripcion;
		this.imagenPortada = imagenPortada;
		this.pdf = pdf;
		this.urlpdf = urlpdf;
	}



	@Override
	public int hashCode() {
		return Objects.hash(anio, autor, descripcion, id, imagenPortada, pdf, titulo, urlpdf);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return anio == other.anio && Objects.equals(autor, other.autor)
				&& Objects.equals(descripcion, other.descripcion) && Objects.equals(id, other.id)
				&& Objects.equals(imagenPortada, other.imagenPortada) && Objects.equals(pdf, other.pdf)
				&& Objects.equals(titulo, other.titulo) && Objects.equals(urlpdf, other.urlpdf);
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



	public String getPdf() {
		return pdf;
	}



	public void setPdf(String pdf) {
		this.pdf = pdf;
	}



	public String getUrlpdf() {
		return urlpdf;
	}



	public void setUrlpdf(String urlpdf) {
		this.urlpdf = urlpdf;
	}

}

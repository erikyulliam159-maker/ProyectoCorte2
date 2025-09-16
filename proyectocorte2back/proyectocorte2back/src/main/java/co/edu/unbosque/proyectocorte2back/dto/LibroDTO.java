package co.edu.unbosque.proyectocorte2back.dto;

import java.util.Objects;

public class LibroDTO {

	private Long id;
	private String titulo;
	private String autor;
	private int anio;
	private String descripcion;
	private String imagenPortada;
	private String urlPdf;
	
	public LibroDTO() {
		// TODO Auto-generated constructor stub
	}

	public LibroDTO(String titulo, String autor, int anio, String descripcion, String imagenPortada, String urlPdf) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.anio = anio;
		this.descripcion = descripcion;
		this.imagenPortada = imagenPortada;
		this.urlPdf = urlPdf;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anio, autor, descripcion, id, imagenPortada, titulo, urlPdf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibroDTO other = (LibroDTO) obj;
		return anio == other.anio && Objects.equals(autor, other.autor)
				&& Objects.equals(descripcion, other.descripcion) && Objects.equals(id, other.id)
				&& Objects.equals(imagenPortada, other.imagenPortada) && Objects.equals(titulo, other.titulo)
				&& Objects.equals(urlPdf, other.urlPdf);
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

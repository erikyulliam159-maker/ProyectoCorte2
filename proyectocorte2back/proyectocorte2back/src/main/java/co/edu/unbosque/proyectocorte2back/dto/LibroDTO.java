/**
 * Clase LibroDTO
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.dto
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.dto;

import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class LibroDTO.
 */
public class LibroDTO {

	/** The id. */
	private Long id;
	
	/** The titulo. */
	private String titulo;
	
	/** The autor. */
	private String autor;
	
	/** The anio. */
	private int anio;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The imagen portada. */
	private String imagenPortada;
	
	/** The pdf. */
	private String pdf;
	
	/** The urlpdf. */
	private String urlpdf;

	/**
	 * Instantiates a new libro DTO.
	 */
	public LibroDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new libro DTO.
	 *
	 * @param titulo the titulo
	 * @param autor the autor
	 * @param anio the anio
	 * @param descripcion the descripcion
	 * @param imagenPortada the imagen portada
	 * @param pdf the pdf
	 * @param urlpdf the urlpdf
	 */
	public LibroDTO(String titulo, String autor, int anio, String descripcion, String imagenPortada, String pdf,
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

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(anio, autor, descripcion, id, imagenPortada, pdf, titulo, urlpdf);
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
		LibroDTO other = (LibroDTO) obj;
		return anio == other.anio && Objects.equals(autor, other.autor)
				&& Objects.equals(descripcion, other.descripcion) && Objects.equals(id, other.id)
				&& Objects.equals(imagenPortada, other.imagenPortada) && Objects.equals(pdf, other.pdf)
				&& Objects.equals(titulo, other.titulo) && Objects.equals(urlpdf, other.urlpdf);
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
	 * Gets the autor.
	 *
	 * @return the autor
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * Sets the autor.
	 *
	 * @param autor the new autor
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * Gets the anio.
	 *
	 * @return the anio
	 */
	public int getAnio() {
		return anio;
	}

	/**
	 * Sets the anio.
	 *
	 * @param anio the new anio
	 */
	public void setAnio(int anio) {
		this.anio = anio;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the imagen portada.
	 *
	 * @return the imagen portada
	 */
	public String getImagenPortada() {
		return imagenPortada;
	}

	/**
	 * Sets the imagen portada.
	 *
	 * @param imagenPortada the new imagen portada
	 */
	public void setImagenPortada(String imagenPortada) {
		this.imagenPortada = imagenPortada;
	}

	/**
	 * Gets the pdf.
	 *
	 * @return the pdf
	 */
	public String getPdf() {
		return pdf;
	}

	/**
	 * Sets the pdf.
	 *
	 * @param pdf the new pdf
	 */
	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	/**
	 * Gets the urlpdf.
	 *
	 * @return the urlpdf
	 */
	public String getUrlpdf() {
		return urlpdf;
	}

	/**
	 * Sets the urlpdf.
	 *
	 * @param urlpdf the new urlpdf
	 */
	public void setUrlpdf(String urlpdf) {
		this.urlpdf = urlpdf;
	}


}

/**
 * Clase ProblemaDTO
 * Proyecto: proyectocorte2frontninos
 * Paquete: co.edu.unbosque.model
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.model;

import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class ProblemaDTO.
 */
public class ProblemaDTO {
	
	/** The id. */
	private Long id;
	
	/** The titulo. */
	private String titulo;
	
	/** The dificultad. */
	private int dificultad;
	
	/** The tema. */
	private String tema; 
	
	/** The juez. */
	private String juez;
	
	/** The url. */
	private String url;
	
	
	/**
	 * Instantiates a new problema DTO.
	 */
	public ProblemaDTO() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * Instantiates a new problema DTO.
	 *
	 * @param id the id
	 * @param titulo the titulo
	 * @param dificultad the dificultad
	 * @param tema the tema
	 * @param juez the juez
	 * @param url the url
	 */
	public ProblemaDTO(Long id, String titulo, int dificultad, String tema, String juez, String url) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.dificultad = dificultad;
		this.tema = tema;
		this.juez = juez;
		this.url = url;
	}


	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(dificultad, id, juez, tema, titulo, url);
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
		ProblemaDTO other = (ProblemaDTO) obj;
		return dificultad == other.dificultad && Objects.equals(id, other.id) && Objects.equals(juez, other.juez)
				&& Objects.equals(tema, other.tema) && Objects.equals(titulo, other.titulo)
				&& Objects.equals(url, other.url);
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
	 * Gets the dificultad.
	 *
	 * @return the dificultad
	 */
	public int getDificultad() {
		return dificultad;
	}


	/**
	 * Sets the dificultad.
	 *
	 * @param dificultad the new dificultad
	 */
	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}


	/**
	 * Gets the tema.
	 *
	 * @return the tema
	 */
	public String getTema() {
		return tema;
	}


	/**
	 * Sets the tema.
	 *
	 * @param tema the new tema
	 */
	public void setTema(String tema) {
		this.tema = tema;
	}


	/**
	 * Gets the juez.
	 *
	 * @return the juez
	 */
	public String getJuez() {
		return juez;
	}


	/**
	 * Sets the juez.
	 *
	 * @param juez the new juez
	 */
	public void setJuez(String juez) {
		this.juez = juez;
	}


	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * Sets the url.
	 *
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

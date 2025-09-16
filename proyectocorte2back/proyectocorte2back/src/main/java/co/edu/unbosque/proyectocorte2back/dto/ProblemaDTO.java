package co.edu.unbosque.proyectocorte2back.dto;

import java.util.Objects;

public class ProblemaDTO {
	private Long id;
	private String titulo;
	private int dificultad;
	private String tema; 
	private String juez;
	private String url;
	
	
	public ProblemaDTO() {
		// TODO Auto-generated constructor stub
	}


	public ProblemaDTO(Long id, String titulo, int dificultad, String tema, String juez, String url) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.dificultad = dificultad;
		this.tema = tema;
		this.juez = juez;
		this.url = url;
	}


	@Override
	public int hashCode() {
		return Objects.hash(dificultad, id, juez, tema, titulo, url);
	}


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


	public int getDificultad() {
		return dificultad;
	}


	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}


	public String getTema() {
		return tema;
	}


	public void setTema(String tema) {
		this.tema = tema;
	}


	public String getJuez() {
		return juez;
	}


	public void setJuez(String juez) {
		this.juez = juez;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

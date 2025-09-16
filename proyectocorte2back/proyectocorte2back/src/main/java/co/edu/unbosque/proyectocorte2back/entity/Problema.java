package co.edu.unbosque.proyectocorte2back.entity;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "problemas")
public class Problema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false)
	private int dificultad; 

	@Column(nullable = false)
	private String tema; 

	@Column(nullable = false)
	private String juez;

	@Column(nullable = false)
	private String url;

	public Problema() {
		// TODO Auto-generated constructor stub
	}

	public Problema(String titulo, int dificultad, String tema, String juez, String url) {
		super();
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
		Problema other = (Problema) obj;
		return dificultad == other.dificultad && Objects.equals(id, other.id) && Objects.equals(juez, other.juez)
				&& Objects.equals(tema, other.tema) && Objects.equals(titulo, other.titulo)
				&& Objects.equals(url, other.url);
	}

	// Getters y setters
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

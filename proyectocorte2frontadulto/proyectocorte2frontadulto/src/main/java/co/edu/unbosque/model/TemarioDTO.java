package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class TemarioDTO {
	
	private Long id;
	private String titulo;
	private List<SubtemaDTO> subtemas;
	
	public TemarioDTO() {
		// TODO Auto-generated constructor stub
		 subtemas = new ArrayList<>();
	}

	public TemarioDTO(String titulo, List<SubtemaDTO> subtemas) {
		super();
		this.titulo = titulo;
		this.subtemas = subtemas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TemarioDTO other = (TemarioDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo);
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

	public List<SubtemaDTO> getSubtemas() {
		return subtemas;
	}

	public void setSubtemas(List<SubtemaDTO> subtemas) {
		this.subtemas = subtemas;
	}

	
}

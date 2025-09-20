package co.edu.unbosque.proyectocorte2back.dto;

import java.time.LocalDate;

import java.util.Objects;

public class EventoDTO {

	private Long id;
	private String titulo;
	private String descripcion;
	private LocalDate fecha;
	private String url;

	public EventoDTO() {
		// TODO Auto-generated constructor stub
	}

	public EventoDTO(Long id, String titulo, String descripcion, LocalDate fecha, String url) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.url = url;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, fecha, id, titulo, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventoDTO other = (EventoDTO) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(fecha, other.fecha)
				&& Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo)
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

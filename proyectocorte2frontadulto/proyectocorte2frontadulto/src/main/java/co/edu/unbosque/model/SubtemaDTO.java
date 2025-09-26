package co.edu.unbosque.model;

import java.util.Objects;



public class SubtemaDTO {
	private Long id;
	private String nombre;
	private String tipo;

	private String temarioTitulo; // 🔹 se reemplaza temarioId por nombre de temario

	private DetalleSubtemaDTO detalle;

	public SubtemaDTO() {
		// TODO Auto-generated constructor stub
	}

	public SubtemaDTO(String nombre, String tipo, String temarioTitulo, DetalleSubtemaDTO detalle) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.temarioTitulo = temarioTitulo;
		this.detalle = detalle;
	}

	@Override
	public int hashCode() {
		return Objects.hash(detalle, id, nombre, temarioTitulo, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubtemaDTO other = (SubtemaDTO) obj;
		return Objects.equals(detalle, other.detalle) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(temarioTitulo, other.temarioTitulo)
				&& Objects.equals(tipo, other.tipo);
	}

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

	public String getTemarioTitulo() {
		return temarioTitulo;
	}

	public void setTemarioTitulo(String temarioTitulo) {
		this.temarioTitulo = temarioTitulo;
	}

	public DetalleSubtemaDTO getDetalle() {
		return detalle;
	}

	public void setDetalle(DetalleSubtemaDTO detalle) {
		this.detalle = detalle;
	}
	
	

}

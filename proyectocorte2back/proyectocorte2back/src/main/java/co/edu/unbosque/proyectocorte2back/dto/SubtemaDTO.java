package co.edu.unbosque.proyectocorte2back.dto;

import java.util.Objects;

import co.edu.unbosque.proyectocorte2back.entity.Temario;

public class SubtemaDTO {
	private Long id;
	private String nombre;
	private String tipo;
	private Temario temario;
	private DetalleSubtemaDTO detalle;
	
	 public SubtemaDTO() {
			// TODO Auto-generated constructor stub
		}

		public SubtemaDTO(String nombre, String tipo, Temario temario, DetalleSubtemaDTO detalle) {
			super();
			this.nombre = nombre;
			this.tipo = tipo;
			this.temario = temario;
			this.detalle = detalle;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(id, nombre, temario, tipo);
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
			return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
					&& Objects.equals(temario, other.temario) && Objects.equals(tipo, other.tipo);
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

		public Temario getTemario() {
			return temario;
		}

		public void setTemario(Temario temario) {
			this.temario = temario;
		}

		public DetalleSubtemaDTO getDetalle() {
			return detalle;
		}

		public void setDetalle(DetalleSubtemaDTO detalle) {
			this.detalle = detalle;
		}

		
		
		

}

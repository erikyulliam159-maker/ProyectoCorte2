package co.edu.unbosque.proyectocorte2back.dto;

import java.util.Objects;

public class DocenteDTO extends UsuarioDTO{
	private Long id;
	
	 public DocenteDTO() {
	        super();
	    }

	    public DocenteDTO(String username, String password, String nombreCompleto, String email, String fotoPerfil) {
	        super(username, password, nombreCompleto, email, fotoPerfil);
	   
	    }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + Objects.hash(id);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (getClass() != obj.getClass())
				return false;
			DocenteDTO other = (DocenteDTO) obj;
			return Objects.equals(id, other.id);
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

	    
}

package co.edu.unbosque.proyectocorte2back.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "administradores")
public class Administrador extends Usuario {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

	public Administrador() {
		super();
	}

	public Administrador(String username, String password, String nombreCompleto, String email, String fotoPerfil,
			String cargo) {
		super(username, password, nombreCompleto, email, fotoPerfil);

	}
	
	

	public Administrador(String username, String password) {
		super(username, password);
		
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
		Administrador other = (Administrador) obj;
		return Objects.equals(id, other.id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}

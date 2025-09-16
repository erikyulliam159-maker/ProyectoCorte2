package co.edu.unbosque.proyectocorte2back.entity;


import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estudiantes")
public class Estudiante extends Usuario {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    public Estudiante() {
        super();
    }

    public Estudiante(String username, String password, String nombreCompleto, String email, String fotoPerfil) {
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
		Estudiante other = (Estudiante) obj;
		return Objects.equals(id, other.id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    
    
  
}

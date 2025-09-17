package co.edu.unbosque.proyectocorte2back.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "temarios")
public class Temario {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

	@Column(nullable = false)
	private String titulo;
	   @OneToMany(mappedBy = "temario",
               cascade = CascadeType.ALL, // o {PERSIST, MERGE} si no quieres REMOVE
               orphanRemoval = true)
    private List<Subtema> subtemas;

	public Temario() {
		// TODO Auto-generated constructor stub
	}

	public Temario(String titulo, List<Subtema> subtemas) {
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
		Temario other = (Temario) obj;
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

	public List<Subtema> getSubtemas() {
		return subtemas;
	}

	public void setSubtemas(List<Subtema> subtemas) {
		this.subtemas = subtemas;
	}

}

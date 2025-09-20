package co.edu.unbosque.proyectocorte2back.entity;

import jakarta.persistence.*;
import java.util.Objects;



@Entity
@Table(name = "subtemas")
public class Subtema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   

    @Column(nullable = false)
    private String nombre; 

    @Column(nullable = false)
    private String tipo;   

    @ManyToOne
    @JoinColumn(name = "temario_id")
    private Temario temario;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "detalle_id")
    private DetalleSubtema detalle;


    public Subtema() {
		// TODO Auto-generated constructor stub
	}

	public Subtema(String nombre, String tipo, Temario temario, DetalleSubtema detalle) {
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
		Subtema other = (Subtema) obj;
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

	public DetalleSubtema getDetalle() {
		return detalle;
	}

	public void setDetalle(DetalleSubtema detalle) {
		this.detalle = detalle;
	}
	
	
    
}

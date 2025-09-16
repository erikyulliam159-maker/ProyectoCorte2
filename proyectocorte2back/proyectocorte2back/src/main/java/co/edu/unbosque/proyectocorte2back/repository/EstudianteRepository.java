package co.edu.unbosque.proyectocorte2back.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.unbosque.proyectocorte2back.entity.Estudiante;



public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {
	
	public Optional<Estudiante> findByNombreCompleto(String nombreCompleto);
	public Optional<Estudiante>findByEmail(String email);
	public Optional<Estudiante>findByUserName(String username);
	
	public void deleteByNombreCompleto(String nombre);
	public void deleteByEmail(String email);
	public void deleteByUserName(String username);

}

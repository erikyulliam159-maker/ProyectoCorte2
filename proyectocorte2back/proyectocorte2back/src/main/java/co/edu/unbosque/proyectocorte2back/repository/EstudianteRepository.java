/**
 * Clase EstudianteRepository
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.repository
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.unbosque.proyectocorte2back.entity.Estudiante;



// TODO: Auto-generated Javadoc
/**
 * The Interface EstudianteRepository.
 */
public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {
	
	/**
	 * Find by nombre completo.
	 *
	 * @param nombreCompleto the nombre completo
	 * @return the optional
	 */
	public Optional<Estudiante> findByNombreCompleto(String nombreCompleto);
	
	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	public Optional<Estudiante>findByEmail(String email);
	
	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the optional
	 */
	public Optional<Estudiante>findByUsername(String username);
	
	/**
	 * Delete by nombre completo.
	 *
	 * @param nombre the nombre
	 */
	public void deleteByNombreCompleto(String nombre);
	
	/**
	 * Delete by email.
	 *
	 * @param email the email
	 */
	public void deleteByEmail(String email);
	
	/**
	 * Delete by username.
	 *
	 * @param username the username
	 */
	public void deleteByUsername(String username);

}

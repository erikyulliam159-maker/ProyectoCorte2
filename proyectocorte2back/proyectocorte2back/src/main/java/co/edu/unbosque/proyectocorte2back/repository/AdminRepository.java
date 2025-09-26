/**
 * Clase AdminRepository
 * Proyecto: proyectocorte2back
 * Paquete: co.edu.unbosque.proyectocorte2back.repository
 *
 * Descripción: Documentación pendiente.
 */
package co.edu.unbosque.proyectocorte2back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.proyectocorte2back.entity.Administrador;


// TODO: Auto-generated Javadoc
/**
 * The Interface AdminRepository.
 */
public interface AdminRepository extends JpaRepository<Administrador, Long> {

	/**
	 * Find by nombre completo.
	 *
	 * @param nombreCompleto the nombre completo
	 * @return the optional
	 */
	public Optional<Administrador> findByNombreCompleto(String nombreCompleto);
	
	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	public Optional<Administrador>findByEmail(String email);
	
	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the optional
	 */
	public Optional<Administrador>findByUsername(String username);
	
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
